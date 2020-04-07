package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.mapper.HistoryMapper;
import com.codimiracle.application.platform.huidu.service.*;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class HistoryServiceImpl extends AbstractService<String, History> implements HistoryService {
    Interner<String> historyAccessingLock = Interners.newWeakInterner();
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;
    @Resource
    private BookEpisodeService bookEpisodeService;
    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    private HistoryVO mutate(HistoryVO historyVO) {
        if (Objects.nonNull(historyVO)) {
            historyVO.setBook(bookService.findByIdIntegrally(null, historyVO.getBookId()));
            if (Objects.nonNull(historyVO.getEpisodeId())) {
                historyVO.setEpisode(bookEpisodeService.findByIdIntegrally(historyVO.getEpisodeId()));
            }
            if (Objects.nonNull(historyVO.getAudioEpisodeId())) {
                historyVO.setAudioEpisode(bookAudioEpisodeService.findByIdIntegrally(historyVO.getAudioEpisodeId()));
            }
            historyVO.setUser(userService.findByIdProtectly(historyVO.getUserId()));
        }
        return historyVO;
    }

    @Override
    public HistoryVO findLastReadByUserIdAndBookIdIntegrally(String userId, String bookId) {
        HistoryVO historyVO = historyMapper.selectLastReadByUserIdAndBookIdIntegrally(userId, bookId);
        mutate(historyVO);
        return historyVO;
    }

    @Override
    public HistoryVO findLastReadByUserIdAndBookIdIntegrallyOrFirstEpisode(String userId, String bookId) {
        HistoryVO historyVO = findLastReadByUserIdAndBookIdIntegrally(userId, bookId);
        if (Objects.nonNull(historyVO)) {
            return historyVO;
        }
        historyVO = new HistoryVO();
        historyVO.setBookId(bookId);
        BookVO bookVO = bookService.findByIdIntegrally(null, bookId);
        historyVO.setBook(bookVO);
        BookEpisodeVO bookEpisodeVO = null;
        BookAudioEpisodeVO bookAudioEpisodeVO = null;
        //返回第一章
        if (Objects.equals(bookVO.getType(), BookType.ElectronicBook.getType())) {
            bookEpisodeVO = bookEpisodeService.findByEpisodeNumberIntegrally(bookId, 1);
        } else {
            bookAudioEpisodeVO = bookAudioEpisodeService.findByMediaNumberIntegrally(bookId, 1);
        }
        historyVO.setReadTime(null);
        historyVO.setEpisode(bookEpisodeVO);
        historyVO.setAudioEpisode(bookAudioEpisodeVO);
        return historyVO;
    }

    @Override
    public List<History> findByUserIdAndBookId(String userId, String bookId) {
        Condition condition = Conditioner.conditionOf(History.class);
        condition.createCriteria().andEqualTo("userId", userId).andEqualTo("bookId", bookId).andEqualTo("deleted", false);
        return historyMapper.selectByCondition(condition);
    }

    @Override
    public List<History> findByUserIdAndBookIdIntegrally(String userId, String bookId) {
        return historyMapper.selectByUserIdAndBookIdIntegrally(userId, bookId);
    }

    @Override
    public Integer countByUserIdAndBookId(String userId, String bookId) {
        Condition condition = Conditioner.conditionOf(History.class);
        condition.createCriteria().andEqualTo("userId", userId).andEqualTo("bookId", bookId);
        return historyMapper.selectCountByCondition(condition);
    }

    @Override
    public HistoryVO findByIdIntegrally(String historyId) {
        HistoryVO historyVO = historyMapper.selectByIdIntegrally(historyId);
        mutate(historyVO);
        return historyVO;
    }

    @Override
    public List<HistoryVO> findByUserIdIntegrally(String id) {
        return null;
    }

    @Override
    public List<HistoryVO> findByThatDayAndUserIdIntegrally(String userId, Date now) {
        List<HistoryVO> historyList = historyMapper.selectThatDayByUserIdIntegrally(userId, now);
        historyList.forEach((this::mutate));
        return historyList;
    }

    @Override
    public void deleteByIdLogically(String id) {
        historyMapper.deleteByIdLogically(id);
    }

    @Override
    public PageSlice<HistoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(historyMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public History findByUserIdAndBookIdAndEpisodeId(String userId, String bookId, String episodeId) {
        Condition condition = Conditioner.conditionOf(History.class);
        condition.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("bookId", bookId)
                .andEqualTo("episodeId", episodeId);
        List<History> histories = historyMapper.selectByCondition(condition);
        if (histories.size() == 1) {
            return histories.get(0);
        } else if (histories.size() == 0) {
            return null;
        } else {
            throw new TooManyResultsException();
        }
    }

    @Override
    public History findByUserIdAndBookIdAndAudioEpisodeId(String userId, String bookId, String audioEpisodeId) {
        Condition condition = Conditioner.conditionOf(History.class);
        condition.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("bookId", bookId)
                .andEqualTo("audioEpisodeId", audioEpisodeId);
        List<History> histories = historyMapper.selectByCondition(condition);
        if (histories.size() == 1) {
            return histories.get(0);
        } else if (histories.size() == 0) {
            return null;
        } else {
            throw new TooManyResultsException();
        }
    }

    @Override
    public void accessEpisode(String userId, String bookId, String episodeId, Float progress) {
        String lock = historyAccessingLock.intern(String.format("user-%s-book-episode-%s", userId, episodeId));
        synchronized (lock) {
            History history = findByUserIdAndBookIdAndEpisodeId(userId, bookId, episodeId);
            if (Objects.nonNull(history)) {
                History updatingHistory = new History();
                updatingHistory.setId(history.getId());
                updatingHistory.setReadTime(new Date());
                updatingHistory.setProgress(progress);
                update(updatingHistory);
            } else {
                history = new History();
                history.setUserId(userId);
                history.setBookId(bookId);
                history.setEpisodeId(episodeId);
                history.setProgress(progress);
                history.setReadTime(new Date());
                save(history);
            }
        }
    }

    @Override
    public void accessAudioEpisode(String userId, String bookId, String audioEpisdoeId, Float progress) {
        String lock = historyAccessingLock.intern(String.format("user-%s-audio-episode-%s", userId, audioEpisdoeId));
        synchronized (lock) {
            History history = findByUserIdAndBookIdAndAudioEpisodeId(userId, bookId, audioEpisdoeId);
            if (Objects.nonNull(history)) {
                History updatingHistory = new History();
                updatingHistory.setId(history.getId());
                updatingHistory.setReadTime(new Date());
                updatingHistory.setProgress(progress);
                update(updatingHistory);
            } else {
                history = new History();
                history.setUserId(userId);
                history.setBookId(bookId);
                history.setAudioEpisodeId(audioEpisdoeId);
                history.setProgress(progress);
                history.setReadTime(new Date());
                save(history);
            }
        }
    }

    @Override
    public HistoryVO findLastReadByUserIdAndBookIdAndEpisodeIdIntegrally(String userId, String bookId, String episodeId) {
        return mutate(historyMapper.selectLastReadByUserIdAndBookIdAndEpisodeIdIntegrally(userId, bookId, episodeId));
    }

    @Override
    public HistoryVO findByUserIdAndBookIdAndEpisodeIdOrNewIntegrally(String userId, String bookId, String episodeId) {
        HistoryVO historyVO = findLastReadByUserIdAndBookIdAndEpisodeIdIntegrally(userId, bookId, episodeId);
        if (Objects.isNull(historyVO)) {
            BookEpisodeVO episodeVO = bookEpisodeService.findByIdIntegrally(episodeId);
            if (Objects.isNull(episodeVO)) {
                return null;
            }
            historyVO = new HistoryVO();
            historyVO.setEpisode(episodeVO);
            historyVO.setEpisodeId(episodeId);
            return historyVO;
        }
        return historyVO;
    }

    @Override
    public HistoryVO findByUserIdAndBookIdAndAudioEpisodeIdOrNewIntegrally(String userId, String bookId, String audioEpisodeId) {
        HistoryVO historyVO = findLastReadByUserIdAndBookIdAndAudioEpisodeIdIntegrally(userId, bookId, audioEpisodeId);
        if (Objects.isNull(historyVO)) {
            BookAudioEpisodeVO audioEpisodeVO = bookAudioEpisodeService.findByIdIntegrally(audioEpisodeId);
            if (Objects.isNull(audioEpisodeVO)) {
                return null;
            }
            historyVO = new HistoryVO();
            historyVO.setAudioEpisode(audioEpisodeVO);
            historyVO.setAudioEpisodeId(audioEpisodeId);
            return historyVO;
        }
        return historyVO;
    }

    private HistoryVO findLastReadByUserIdAndBookIdAndAudioEpisodeIdIntegrally(String userId, String bookId, String audioEpisodeId) {
        return mutate(historyMapper.selectLastReadByUserIdAndBookIdAndAudioEpisodeIdIntegrally(userId, bookId, audioEpisodeId));
    }
}
