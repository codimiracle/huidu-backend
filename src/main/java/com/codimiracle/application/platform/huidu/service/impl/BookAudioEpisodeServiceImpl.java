package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.application.platform.huidu.mapper.BookAudioEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookAudioEpisodeServiceImpl extends AbstractService<String, BookAudioEpisode> implements BookAudioEpisodeService {
    @Resource
    private BookAudioEpisodeMapper bookAudioEpisodeMapper;
    @Resource
    private BookEpisodeService bookEpisodeService;
    @Resource
    private BookService bookService;
    @Resource
    private CommodityService commodityService;

    private BookAudioEpisodeVO mutate(BookAudioEpisodeVO bookAudioEpisodeVO) {
        if (Objects.nonNull(bookAudioEpisodeVO)) {
            bookAudioEpisodeVO.setBook(bookService.findByIdIntegrally(bookAudioEpisodeVO.getBookId()));
            bookAudioEpisodeVO.setCommodity(commodityService.findByIdIntegrally(bookAudioEpisodeVO.getCommodityId()));
            bookAudioEpisodeVO.setEpisode(bookEpisodeService.findByIdIntegrally(bookAudioEpisodeVO.getEpisodeId()));
            bookAudioEpisodeVO.setNext(bookAudioEpisodeMapper.selectAudioEpisodeIdByMediaNumber(bookAudioEpisodeVO.getBookId(), bookAudioEpisodeVO.getMediaNumber() + 1));
        }
        return bookAudioEpisodeVO;
    }

    @Override
    public PageSlice<BookAudioEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        PageSlice<BookAudioEpisodeVO> bookAudioEpisodeVOPageSlice = extractPageSlice(bookAudioEpisodeMapper.selectAllIntegrally(bookId, filter, sorter, page));
        bookAudioEpisodeVOPageSlice.getList().forEach((this::mutate));
        return bookAudioEpisodeVOPageSlice;
    }

    @Override
    public BookAudioEpisodeVO findByIdIntegrally(String id) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectByIdIntegrally(id);
        mutate(bookAudioEpisodeVO);
        return bookAudioEpisodeVO;
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookAudioEpisodeMapper.deleteByIdLogically(id);
    }

    @Override
    public BookAudioEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String bookId) {
        return bookAudioEpisodeMapper.selectLastUpdateEpisodeByBookId(bookId);
    }

    @Override
    public BookAudioEpisodeVO findByMediaNumberIntegrally(String bookId, Integer mediaNumber) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectByMediaNumberIntegrally(bookId, mediaNumber);
        mutate(bookAudioEpisodeVO);
        return bookAudioEpisodeVO;
    }

    @Override
    public List<AudioCatalogs> findCatalogsByBookIdAndStatus(String id, BookAudioEpisodeStatus status) {
        return bookAudioEpisodeMapper.selectCatalogsByBookIdAndStatus(id, status);
    }

    @Override
    public BookAudioEpisodeVO findLastPublishedEpisodeByBookId(String bookId) {
        return bookAudioEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId);
    }
}
