package com.codimiracle.application.platform.huidu.web.api.backend;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, Publish, distribute, sublicense, and/or sell
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

import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ExaminationDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.service.TopicService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.service.ExaminationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/topics")
@Slf4j
public class ApiBackendTopicController {

    @Resource
    private TopicService topicService;

    @Resource
    private ExaminationService examinationService;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        TopicVO topic = topicService.findByIdIntegrally(id);
        return RestfulUtil.entity(topic);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        topicService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        topicService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @PostMapping("/{id}/accept")
    public ApiResponse accept(@AuthenticationPrincipal User user, @PathVariable("id") String topicId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        examinationService.accept(topicId, user.getId(), examinationDTO.getReason());
        return RestfulUtil.success();
    }

    @PostMapping("/{id}/reject")
    public ApiResponse reject(@AuthenticationPrincipal User user, @PathVariable("id") String topicId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        examinationService.reject(topicId, user.getId(),  examinationDTO.getReason());
        return RestfulUtil.success();
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return RestfulUtil.list(topicService.findAllIntegrally(filter, sorter, page));
    }
}
