package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.embedded.CommunityFocus;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.ReferenceVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.ContentReferenceMapper;
import com.codimiracle.application.platform.huidu.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentReferenceServiceImpl extends AbstractService<String, ContentReference> implements ContentReferenceService {
    @Resource
    private ContentReferenceMapper contentReferenceMapper;
    @Resource
    private TopicService topicService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private BookService bookService;
    @Resource
    private CommentService commentService;

    @Override
    public void deleteByContentId(String id) {
        contentReferenceMapper.deleteByContentId(id);
    }

    @Override
    public void deleteByContentIds(String ids) {
        contentReferenceMapper.deleteByContentIds(ids);
    }

    @Override
    public List<ContentReference> findByContentId(String contentId) {
        return contentReferenceMapper.findByContentId(contentId);
    }

    @Override
    public List<ReferenceVO> findByContentIdIntegrally(String contentId) {
        List<ContentReference> references = contentReferenceMapper.findByContentId(contentId);
        List<ReferenceVO> referenceVOList = new ArrayList<>();
        for (ContentReference reference: references) {
            ReferenceVO<Object> referenceVO = new ReferenceVO<>();
            BeanUtils.copyProperties(reference, referenceVO);
            if (Objects.equals(reference.getType(), ContentType.Book)) {
                referenceVO.setRef(bookService.findByIdIntegrally(null, reference.getRefId()));
            } else if (Objects.equals(reference.getType(), ContentType.Comment)) {
                referenceVO.setRef(commentService.findByIdIntegrally(reference.getRefId()));
            } else if (Objects.equals(reference.getType(), ContentType.Review)) {
                referenceVO.setRef(reviewService.findByIdIntegrally(reference.getRefId()));
            } else if (Objects.equals(reference.getType(), ContentType.Topic)) {
                referenceVO.setRef(topicService.findByIdIntegrally(reference.getRefId()));
            }
            referenceVOList.add(referenceVO);
        }
        return referenceVOList;
    }

    private void mutate(CommunityFocus communityFocus) {
        communityFocus.setBook(bookService.findByIdIntegrally(communityFocus.getBookId()));
        communityFocus.setTopics(topicService.findFocusTopicByReferenceIdIntegrally(communityFocus.getBookId(), null, null, new Page()).getList());
    }

    @Override
    public PageSlice<CommunityFocus> findCommunityFocusIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<CommunityFocus> slice = extractPageSlice(contentReferenceMapper.selectCommunityFocus(filter, sorter, page));
        slice.getList().forEach(this::mutate);
        return slice;
    }
}
