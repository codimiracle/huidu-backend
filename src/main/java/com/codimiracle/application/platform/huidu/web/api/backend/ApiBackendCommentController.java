package com.codimiracle.application.platform.huidu.web.api.backend;/*
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

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.application.platform.huidu.entity.vo.CommentVO;
import com.codimiracle.application.platform.huidu.entity.vt.Comment;
import com.codimiracle.application.platform.huidu.service.CommentService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/comments")
public class ApiBackendCommentController {
    @Resource
    private CommentService commentService;

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        commentService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        commentService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.from(commentDTO);
        Objects.requireNonNull(comment);
        comment.setId(id);
        commentService.update(comment);
        return RestfulUtil.entity(commentService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CommentVO commentVO = commentService.findByIdIntegrally(id);
        return RestfulUtil.success(commentVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CommentVO> slice = commentService.findAllIntegrallyWithTargetContent(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
