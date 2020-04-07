package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.mapper.BookEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.CategoryService;
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
public class BookEpisodeServiceImpl extends AbstractService<String, BookEpisode> implements BookEpisodeService {
    @Resource
    private BookEpisodeMapper bookEpisodeMapper;

    @Resource
    private BookService bookService;

    @Resource
    private CommodityService commodityService;
    @Resource
    private CategoryService categoryService;

    private BookEpisodeVO mutate(BookEpisodeVO episodeVO) {
        if (Objects.nonNull(episodeVO)) {
            episodeVO.setBook(bookService.findByIdIntegrally(episodeVO.getBookId()));
            episodeVO.setCommodity(commodityService.findByIdIntegrally(episodeVO.getCommodityId()));
            episodeVO.setNext(bookEpisodeMapper.selectEpisodeIdByEpisodeNumber(episodeVO.getBookId(), episodeVO.getEpisodeNumber() + 1));
        }
        return episodeVO;
    }

    private PageSlice<BookEpisodeVO> mutate(PageSlice<BookEpisodeVO> slice) {
        slice.getList().forEach(this::mutate);
        return slice;
    }

    @Override
    public BookEpisodeVO findByIdIntegrally(String id) {
        return mutate(bookEpisodeMapper.selectByIdIntegrally(id));
    }

    @Override
    public PageSlice<BookEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        return mutate(extractPageSlice(bookEpisodeMapper.selectAllIntegrally(bookId, filter, sorter, page)));
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookEpisodeMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        bookEpisodeMapper.deleteByIdsLogically(ids);
    }

    @Override
    public BookEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String id) {
        return mutate(bookEpisodeMapper.selectLastUpdateEpisodeIntegrally(id));
    }

    @Override
    public BookEpisodeVO findLastEpisodeByBookId(String id) {
        return mutate(bookEpisodeMapper.selectLastEpisodeIntegrally(id));
    }

    @Override
    public Integer findLastEpisodeNumberByBookId(String bookId) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeMapper.selectLastEpisodeIntegrally(bookId);
        return Objects.nonNull(bookEpisodeVO) ? bookEpisodeVO.getEpisodeNumber() : 0;
    }

    @Override
    public BookEpisodeVO findByEpisodeNumberIntegrally(String bookId, Integer episodeNumber) {
        return mutate(bookEpisodeMapper.selectByEpisodeNumberIntegrally(bookId, episodeNumber));
    }

    @Override
    public List<Catalogs> findCatalogsByBookIdAndStatus(String id, ContentStatus status) {
        return bookEpisodeMapper.selectCatalogsByBookId(id, status);
    }

    @Override
    public BookEpisodeVO findLastPublishedEpisodeByBookId(String bookId) {
        return mutate(bookEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId));
    }
}
