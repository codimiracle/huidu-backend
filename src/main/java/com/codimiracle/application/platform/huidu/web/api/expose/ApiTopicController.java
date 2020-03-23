package com.codimiracle.application.platform.huidu.web.api.expose;/*
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
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.entity.vt.Topic;
import com.codimiracle.application.platform.huidu.service.TopicService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/community/topics")
@Slf4j
public class ApiTopicController {

    @Resource
    TopicService topicService;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        TopicVO topic = topicService.findByIdIntegrally(id);
        return RestfulUtil.entity(topic);
    }

    @GetMapping("/most-likes")
    public ApiResponse mostLikeCollection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("likes");
        sorter.setOrder("descend");
        return collection(filter, sorter, page);
    }

    @GetMapping("/hots")
    public ApiResponse hotCollection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setOrder("descend");
        sorter.setField("hotDegree");
        return collection(filter, sorter, page);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("createTime");
        sorter.setOrder("descend");
        return RestfulUtil.list(topicService.findAllIntegrally(filter, sorter, page));
    }
}
