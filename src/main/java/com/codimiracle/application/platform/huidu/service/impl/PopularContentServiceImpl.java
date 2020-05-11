package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.mapper.PopularMapper;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.PopularContentService;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.vo.ContentVO;
import com.codimiracle.web.middleware.content.service.ContentService;
import com.codimiracle.web.middleware.content.service.impl.ContentServiceImpl;
import com.codimiracle.web.mybatis.contract.PageSliceExtractor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Service
@Transactional
public class PopularContentServiceImpl extends PageSliceExtractor implements PopularContentService {
    @Resource
    private ContentService contentService;
    @Resource
    private PopularMapper popularMapper;
    @Resource
    private BookService bookService;

    private PageSlice<BookVO> mapping(PageSlice<ContentVO> slice) {
        PageSlice<BookVO> mappedSlice = new PageSlice<>();
        mappedSlice.setTotal(slice.getTotal());
        mappedSlice.setPage(slice.getPage());
        mappedSlice.setLimit(slice.getLimit());
        mappedSlice.setList(slice.getList().stream().map((contentVO -> {
            return bookService.findByContentIdIntegrally(contentVO.getId());
        })).collect(Collectors.toList()));
        return mappedSlice;
    }

    @Override
    public PageSlice<BookVO> findPopularBooksIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentVO> mutated = contentService.inflate(extractPageSlice(popularMapper.selectPopularBooksIntegrally(filter, sorter, page)));
        return mapping(mutated);
    }
}
