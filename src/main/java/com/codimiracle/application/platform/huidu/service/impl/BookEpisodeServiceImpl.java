package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.mapper.BookEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
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

    @Override
    public BookEpisodeVO findByIdIntegrally(String id) {
        return bookEpisodeMapper.selectByIdIntegrally(id);
    }

    @Override
    public PageSlice<BookEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(bookEpisodeMapper.selectAllIntegrally(bookId, filter, sorter, page));
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
        return bookEpisodeMapper.selectLastUpdateEpisodeIntegrally(id);
    }

    @Override
    public BookEpisodeVO findLastEpisodeByBookId(String id) {
        return bookEpisodeMapper.selectLastEpisodeIntegrally(id);
    }

    @Override
    public Integer findLastEpisodeNumberByBookId(String id) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeMapper.selectLastEpisodeIntegrally(id);
        return Objects.nonNull(bookEpisodeVO) ? bookEpisodeVO.getEpisodeNumber() : 0;
    }

    @Override
    public BookEpisodeVO findByEpisodeNumberIntegrally(String bookId, Integer episodeNumber) {
        return bookEpisodeMapper.selectByEpisodeNumberIntegrally(bookId, episodeNumber);
    }

    @Override
    public List<Catalogs> findCatalogsByBookIdAndStatus(String id, ContentStatus status) {
        return bookEpisodeMapper.selectCatalogsByBookId(id, status);
    }

    @Override
    public BookEpisodeVO findLastPublishedEpisodeByBookId(String bookId) {
        return bookEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId);
    }
}
