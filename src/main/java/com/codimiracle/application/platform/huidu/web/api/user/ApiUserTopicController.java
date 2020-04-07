package com.codimiracle.application.platform.huidu.web.api.user;/*
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

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.TopicDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.entity.vt.Topic;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.TopicService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/user/community/topics")
@Slf4j
public class ApiUserTopicController {

    @Resource
    TopicService topicService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @Valid @RequestBody TopicDTO topicDTO) {
        Topic topic = Topic.from(topicDTO);
        topic.setOwnerId(user.getId());
        topic.setCreateTime(new Date());
        topic.setUpdateTime(topic.getCreateTime());
        topic.setStatus(ContentStatus.Draft);
        topicService.save(topic);
        return RestfulUtil.entity(topicService.findByIdIntegrally(topic.getId()));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody TopicDTO topicDTO) {
        Topic topic = topicService.findById(id);
        if (Objects.isNull(topic) || !Objects.equals(topic.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足!");
        }
        Topic updatingTopic = Topic.from(topicDTO);
        Objects.requireNonNull(updatingTopic);
        updatingTopic.setId(id);
        updatingTopic.setContentId(id);
        //不更新内容类型
        updatingTopic.setType(null);
        updatingTopic.setUpdateTime(new Date());
        topicService.update(updatingTopic);
        return RestfulUtil.entity(topicService.findByIdIntegrally(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, String id) {
        Topic topic = topicService.findById(id);
        if (Objects.isNull(topic) || !Objects.equals(topic.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足！");
        }
        topicService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("id") String id) {
        TopicVO topicVO = topicService.findByIdIntegrally(id);
        if (Objects.isNull(topicVO) || !Objects.equals(topicVO.getOwner().getId(), user.getId())) {
            return RestfulUtil.fail("找不到对应的话题！");
        }
        return RestfulUtil.entity(topicVO);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("ownerId", new String[]{user.getId()});
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("createTime");
        sorter.setOrder("descend");
        return RestfulUtil.list(topicService.findAllIntegrally(filter, sorter, page));
    }
}
