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

import com.codimiracle.application.platform.huidu.converter.CommentConverter;
import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.helper.CommentExaminator;
import com.codimiracle.application.platform.huidu.service.SettingsService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import com.codimiracle.web.middleware.content.pojo.po.Comment;
import com.codimiracle.web.middleware.content.pojo.vo.CommentVO;
import com.codimiracle.web.middleware.content.service.CommentService;
import com.codimiracle.web.middleware.content.service.ContentService;
import com.codimiracle.web.middleware.content.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

import static com.codimiracle.application.platform.huidu.enumeration.Settings.COMMENT_EXAMINATION;

@CrossOrigin
@RestController
@RequestMapping("/api/contents/{content_id}/comments")
public class ApiCommentController {
    @Value("${huidu.notification.system-notifier}")
    private String systemNotifierId;

    @Resource
    private CommentService commentService;

    @Resource
    private ContentService contentService;

    @Resource
    private SettingsService settingsService;

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private CommentExaminator commentExaminator;

    @Autowired
    private CommentConverter commentConverter;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user,
                              @PathVariable("content_id") String contentId,
                              @Valid @RequestBody CommentDTO commentDTO) {
        // 创建评论
        Comment comment = commentConverter.convert(commentDTO);
        comment.setTargetContentId(contentId);
        comment.setStatus(ContentStatus.Examining.toString());
        comment.setOwnerId(user.getId());
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(comment.getCreatedAt());
        // 自动评审
        commentService.save(comment);
        boolean isAutoExamination = Objects.equals(settingsService.retrieve(COMMENT_EXAMINATION), "auto");
        if (isAutoExamination && commentExaminator.isApproval(comment)) {
            examinationService.accept(comment.getId(), systemNotifierId, "自动评审通过");
            comment.setStatus(ContentStatus.Publish.toString());
        } else {
            examinationService.reject(comment.getId(), systemNotifierId, "自动评审不通过");
        }
        //保存评论
        return RestfulUtil.entity(commentService.findByIdIntegrally(comment.getId()));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CommentVO commentVO = commentService.findByIdIntegrally(id);
        return RestfulUtil.success(commentVO);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("content_id") String contentId, @ModelAttribute Page page) {
        Filter filter = new Filter();
        filter.put("targetContentId", new String[]{contentId});
        Sorter sorter = new Sorter();
        sorter.setField("createTime");
        sorter.setOrder("descend");
        PageSlice<CommentVO> slice = commentService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
