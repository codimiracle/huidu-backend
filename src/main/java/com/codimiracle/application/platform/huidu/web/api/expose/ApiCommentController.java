package com.codimiracle.application.platform.huidu.web.api.expose;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.CommentVO;
import com.codimiracle.application.platform.huidu.entity.vt.Comment;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.helper.CommentExaminator;
import com.codimiracle.application.platform.huidu.service.CommentService;
import com.codimiracle.application.platform.huidu.service.SettingsService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

import static com.codimiracle.application.platform.huidu.enumeration.Settings.COMMENT_EXAMINATION;

@CrossOrigin
@RestController
@RequestMapping("/api/contents/{content_id}/comments")
public class ApiCommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private SettingsService settingsService;

    @Autowired
    private CommentExaminator commentExaminator;

    @PreAuthorize("hasAuthority('User')")
    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("content_id") String contentId, @RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.from(commentDTO);
        comment.setTargetContentId(contentId);
        comment.setStatus(ContentStatus.Examining);
        comment.setOwnerId(user.getId());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(comment.getCreateTime());
        boolean isAutoExamination = Objects.equals(settingsService.retrive(COMMENT_EXAMINATION), "auto");
        if (isAutoExamination && commentExaminator.isApproval(comment)) {
            comment.setStatus(ContentStatus.Publish);
        } else {
            comment.setStatus(ContentStatus.Rejected);
        }
        commentService.save(comment);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        commentService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CommentVO commentVO = commentService.findByIdIntegrally(id);
        return RestfulUtil.success(commentVO);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @PathVariable("content_id") String contentId, @ModelAttribute Page page) {
        Filter filter = new Filter();
        filter.put("targetContentId", new String[]{contentId});
        PageSlice<CommentVO> slice = commentService.findAllIntegrally(Objects.nonNull(user) ? user.getId() : null, filter, null, page);
        return RestfulUtil.list(slice);
    }
}
