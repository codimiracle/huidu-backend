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
import com.codimiracle.application.platform.huidu.entity.dto.ReviewDTO;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vt.Review;
import com.codimiracle.application.platform.huidu.service.ReviewService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/community/reviews")
@Slf4j
public class ApiReviewController {

    @Resource
    private ReviewService reviewService;

    @PostMapping
    public ApiResponse create(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal User user) {
        Review review = Review.from(reviewDTO);
        review.setCreateTime(new Date());
        review.setUpdateTime(review.getCreateTime());
        reviewService.save(review);
        return RestfulUtil.entity(reviewService.findByIdIntegrally(review.getId()));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ReviewVO review = reviewService.findByIdIntegrally(id);
        return RestfulUtil.entity(review);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.findById(id);
        Review updatingReview = Review.from(reviewDTO);
        updatingReview.setId(id);
        Objects.requireNonNull(updatingReview);
        List<ContentReference> oldReferences = review.getReferenceList();
        List<ContentReference> newReferences = updatingReview.getReferenceList();
        List<ContentReference> needToDelete = new ArrayList<>();
        //映射已有对象
        Map<String, ContentReference> validatedMap = newReferences.stream().collect(Collectors.toMap(ContentReference::getRefId, (e) -> e));
        for (ContentReference reference : oldReferences) {
            if (validatedMap.containsKey(reference.getRefId())) {
                //不做任何操作
                validatedMap.remove(reference.getRefId());
            } else {
                //放入待删除列表
                needToDelete.add(reference);
            }
        }
        updatingReview.setReferenceList(new ArrayList<>(validatedMap.values()));
        reviewService.update(updatingReview, needToDelete);
        return RestfulUtil.entity(reviewService.findByIdIntegrally(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        reviewService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return RestfulUtil.list(reviewService.findAllIntegrally(filter, sorter, page));
    }
}
