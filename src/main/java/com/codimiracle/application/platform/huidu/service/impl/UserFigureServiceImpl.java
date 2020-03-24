package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.mapper.UserFigureMapper;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * Created by Codimiracle on 2020/03/17.
 */
@Service
@Transactional
public class UserFigureServiceImpl extends AbstractService<String, FigureTag> implements UserFigureService {
    @Resource
    private UserFigureMapper userFigureMapper;

    @Resource
    private TagService tagService;

    @Override
    public void save(FigureTag figureTag) {
        if (Objects.isNull(figureTag.getTagId())) {
            tagService.save(figureTag.getTag());
            figureTag.setTagId(figureTag.getTag().getId());
        }
        super.save(figureTag);
    }

    @Override
    public void save(List<FigureTag> models) {
        models.forEach((figureTag -> {
            if (Objects.isNull(figureTag.getTagId())) {
                tagService.save(figureTag.getTag());
                figureTag.setTagId(figureTag.getTag().getId());
            }
        }));
        super.save(models);
    }

    @Override
    public PageSlice<FigureTagVO> findAllTagIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(userFigureMapper.selectAllTagIntegrally(filter, sorter, page));
    }

    @Override
    public void deleteByIdIntegrally(String figureTagId) {
        userFigureMapper.deleteByIdIntegrally(figureTagId);
    }

    @Override
    public PageSlice<UserProtectedVO> findSimilarUserByUserIdProtectly(String userId, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(userFigureMapper.selectSimilarUserByUserIdProtectly(userId, filter, sorter, page));
    }

    @Override
    public List<CategoryVO> findSimilarCategoryByUserIdIntegrally(String userId) {
        return userFigureMapper.selectSimilarCategoryByUserIdIntegrally(userId);
    }

    @Override
    public List<CategoryVO> findSametasteCategoryByUserIdIntegrally(String userId) {
        return userFigureMapper.selectSametastCategoryByUserIdIntegrally(userId);
    }

    @Override
    public List<CategoryVO> findSimilarCategoryByAvgIntegrally() {
        return userFigureMapper.selectSimilarCategoryByAvgIntegrally();
    }

    @Override
    public List<CategoryVO> findSametasteCategoryByAvgIntegrally() {
        return userFigureMapper.selectSametasteCategoryByAvgIntegrally();
    }
}
