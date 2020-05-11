package com.codimiracle.application.platform.huidu.adapter;

import com.codimiracle.web.middleware.content.pojo.eo.*;
import com.codimiracle.web.middleware.content.pojo.vo.CommentVO;
import com.codimiracle.web.middleware.content.pojo.vo.ContentExaminationVO;
import com.codimiracle.web.middleware.content.pojo.vo.ContentRateVO;
import com.codimiracle.web.middleware.content.pojo.vo.ContentReferenceVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class CommentAdapter {
    @JsonIgnore
    private CommentVO commentVO;

    public CommentAdapter(CommentVO commentVO) {
        this.commentVO = commentVO;
    }

    public ArticleSource getContent() {
        return commentVO.getArticle();
    }

    public String getContentId() {
        return commentVO.getContentId();
    }

    public String getTargetContentId() {
        return commentVO.getTargetContentId();
    }

    public TargetContent getTargetContent() {
        return commentVO.getTargetContent();
    }

    public String getTitle() {
        return commentVO.getTitle();
    }

    public Long getWords() {
        return commentVO.getWords();
    }

    public ArticleSource getArticle() {
        return commentVO.getArticle();
    }

    public String getStatus() {
        return commentVO.getStatus();
    }

    public List<MentionUser> getMentionList() {
        return commentVO.getMentionList();
    }

    public List<ContentReferenceVO> getReferenceList() {
        return commentVO.getReferenceList();
    }

    public ContentExaminationVO getExamination() {
        return commentVO.getExamination();
    }

    public void setContentId(String contentId) {
        commentVO.setContentId(contentId);
    }

    public void setTargetContentId(String targetContentId) {
        commentVO.setTargetContentId(targetContentId);
    }

    public void setTargetContent(TargetContent targetContent) {
        commentVO.setTargetContent(targetContent);
    }

    public void setTitle(String title) {
        commentVO.setTitle(title);
    }

    public void setWords(Long words) {
        commentVO.setWords(words);
    }

    public void setArticle(ArticleSource article) {
        commentVO.setArticle(article);
    }

    public void setStatus(String status) {
        commentVO.setStatus(status);
    }

    public void setMentionList(List<MentionUser> mentionList) {
        commentVO.setMentionList(mentionList);
    }

    public void setReferenceList(List<ContentReferenceVO> referenceList) {
        commentVO.setReferenceList(referenceList);
    }

    public void setExamination(ContentExaminationVO examination) {
        commentVO.setExamination(examination);
    }

    @Override
    public String toString() {
        return commentVO.toString();
    }

    @Override
    public boolean equals(Object o) {
        return commentVO.equals(o);
    }

    @Override
    public int hashCode() {
        return commentVO.hashCode();
    }

    public String getReferenceTargetId() {
        return commentVO.getReferenceTargetId();
    }

    public void setReferenceTargetId(String referenceTargetId) {
        commentVO.setReferenceTargetId(referenceTargetId);
    }

    public String getId() {
        return commentVO.getId();
    }

    public String getType() {
        return commentVO.getType();
    }

    public String getOwnerId() {
        return commentVO.getOwnerId();
    }

    public Long getComments() {
        return commentVO.getComments();
    }

    public Long getLikes() {
        return commentVO.getLikes();
    }

    public Long getDislikes() {
        return commentVO.getDislikes();
    }

    public Long getReposts() {
        return commentVO.getReposts();
    }

    public Boolean getLiked() {
        return commentVO.getLiked();
    }

    public Boolean getDisliked() {
        return commentVO.getDisliked();
    }

    public SocialUser getOwner() {
        return commentVO.getOwner();
    }

    public ContentRateVO getRate() {
        return commentVO.getRate();
    }

    public List<ContentRateVO> getRateList() {
        return commentVO.getRateList();
    }

    public List<Tag> getTagList() {
        return commentVO.getTagList();
    }

    public Date getCreatedAt() {
        return commentVO.getCreatedAt();
    }

    public Date getUpdatedAt() {
        return commentVO.getUpdatedAt();
    }

    public void setId(String id) {
        commentVO.setId(id);
    }

    public void setType(String type) {
        commentVO.setType(type);
    }

    public void setOwnerId(String ownerId) {
        commentVO.setOwnerId(ownerId);
    }

    public void setComments(Long comments) {
        commentVO.setComments(comments);
    }

    public void setLikes(Long likes) {
        commentVO.setLikes(likes);
    }

    public void setDislikes(Long dislikes) {
        commentVO.setDislikes(dislikes);
    }

    public void setReposts(Long reposts) {
        commentVO.setReposts(reposts);
    }

    public void setLiked(Boolean liked) {
        commentVO.setLiked(liked);
    }

    public void setDisliked(Boolean disliked) {
        commentVO.setDisliked(disliked);
    }

    public void setOwner(SocialUser owner) {
        commentVO.setOwner(owner);
    }

    public void setRate(ContentRateVO rate) {
        commentVO.setRate(rate);
    }

    public void setRateList(List<ContentRateVO> rateList) {
        commentVO.setRateList(rateList);
    }

    public void setTagList(List<Tag> tagList) {
        commentVO.setTagList(tagList);
    }

    public void setCreatedAt(Date createdAt) {
        commentVO.setCreatedAt(createdAt);
    }

    public void setUpdatedAt(Date updatedAt) {
        commentVO.setUpdatedAt(updatedAt);
    }
}
