package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.contract.Conditioner;
import com.codimiracle.application.platform.huidu.entity.po.BookTags;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.application.platform.huidu.mapper.BookTagsMapper;
import com.codimiracle.application.platform.huidu.service.BookTagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BookTagsServiceImpl extends AbstractService<String, BookTags> implements BookTagsService {
    @Resource
    private BookTagsMapper bookTagsMapper;

    @Override
    public void deleteByIdLogically(String id) {
        bookTagsMapper.deleteByLogically(id);
    }

    @Override
    public List<TagVO> findByBookIdItegrally(String id) {
        return bookTagsMapper.selectByBookIdIntegrally(id);
    }

    @Override
    public List<BookTags> findByBookId(String bookId) {
        Condition condition = Conditioner.conditionOf(BookTags.class);
        condition.createCriteria().andEqualTo("bookId", bookId);
        return bookTagsMapper.selectByCondition(condition);
    }
}
