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
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void
    paddingAssociation(HistoryVO historyVO) {
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
    }

    @Override
    public HistoryVO findByUserIdAndBookIdIntegrally(String userId, String bookId) {
        HistoryVO historyVO = historyMapper.selectByUserIdAndBookIdIntegrally(userId, bookId);
        paddingAssociation(historyVO);
        return historyVO;
    }

    @Override
    public HistoryVO findByUserIdAndBookIdIntegrallyOrFirstEpisode(String userId, String bookId) {
        HistoryVO historyVO = findByUserIdAndBookIdIntegrally(userId, bookId);
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
    public History findByUserIdAndBookId(String userId, String bookId) {
        return historyMapper.selectByUserIdAndBookId(userId, bookId);
    }

    @Override
    public HistoryVO findByIdIntegrally(String historyId) {
        HistoryVO historyVO = historyMapper.selectByIdIntegrally(historyId);
        paddingAssociation(historyVO);
        return historyVO;
    }

    @Override
    public List<HistoryVO> findByUserIdIntegrally(String id) {
        return null;
    }

    @Override
    public List<HistoryVO> findByThatDayAndUserIdIntegrally(String userId, Date now) {
        List<HistoryVO> historyList = historyMapper.selectThatDayByUserIdIntegrally(userId, now);
        historyList.forEach((this::paddingAssociation));
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
}
