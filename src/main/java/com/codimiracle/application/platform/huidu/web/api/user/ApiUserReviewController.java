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
import com.codimiracle.application.platform.huidu.entity.dto.ReviewDTO;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vt.Review;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.*;
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
@RequestMapping("/api/user/community/reviews")
@Slf4j
public class ApiUserReviewController {

    @Resource
    private ReviewService reviewService;

    @Resource
    private BookService bookService;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;
    @Resource
    private BookEpisodeService bookEpisodeService;

    @Resource
    private HistoryService historyService;

    private boolean checkBookIsRead(String userId, String bookId) {
        Book book = bookService.findById(bookId);
        if (book.getStatus() != BookStatus.Paused || book.getStatus() != BookStatus.Ended) {
            return false;
        }
        if (book.getType() == BookType.ElectronicBook) {
            BookEpisodeVO lastEpisode = bookEpisodeService.findLastPublishedEpisodeByBookId(bookId);
            History history = historyService.findByUserIdAndBookIdAndEpisodeId(userId, bookId, lastEpisode.getId());
            int progress = Math.round(history.getProgress() * 100);
            return Objects.equals(history.getEpisodeId(), lastEpisode.getId()) && progress == 100;
        } else if (book.getType() == BookType.AudioBook) {
            BookAudioEpisodeVO lastEpisode = bookAudioEpisodeService.findLastPublishedEpisodeByBookId(bookId);
            History history = historyService.findByUserIdAndBookIdAndAudioEpisodeId(userId, bookId, lastEpisode.getId());
            int progress = Math.round(history.getProgress() * 100);
            return Objects.equals(history.getAudioEpisodeId(), lastEpisode.getId()) && progress == 100;
        } else {
            return false;
        }
    }

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @Valid @RequestBody ReviewDTO reviewDTO) {
        Review review = Review.from(reviewDTO);
        review.setOwnerId(user.getId());
        review.setCreateTime(new Date());
        review.setUpdateTime(review.getCreateTime());
        if (review.getReferenceList().size() != 1) {
            return RestfulUtil.fail("该点评引用不合法！");
        }
        if (!checkBookIsRead(user.getId(), reviewDTO.getReferences()[0])) {
            return RestfulUtil.fail("没有达到点评条件！");
        }
        review.setStatus(ContentStatus.Draft);
        reviewService.save(review);
        return RestfulUtil.entity(reviewService.findByIdIntegrally(review.getId()));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.findById(id);
        if (Objects.isNull(review) || !Objects.equals(review.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足");
        }
        Review updatingReview = Review.from(reviewDTO);
        Objects.requireNonNull(updatingReview);
        updatingReview.setId(id);
        updatingReview.setContentId(id);
        updatingReview.setType(null);
        updatingReview.setUpdateTime(new Date());
        reviewService.update(updatingReview);
        return RestfulUtil.entity(reviewService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("id") String id) {
        ReviewVO reviewVO = reviewService.findByIdIntegrally(id);
        if (Objects.isNull(reviewVO) || Objects.equals(reviewVO.getOwner().getId(), user.getId())) {
            return RestfulUtil.fail("找不到对应的话题！");
        }
        return RestfulUtil.entity(reviewVO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        Review review = reviewService.findById(id);
        if (Objects.isNull(review) || !Objects.equals(review.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足");
        }
        reviewService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("ownerId", new String[] {user.getId()});
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("createTime");
        sorter.setOrder("descend");
        return RestfulUtil.list(reviewService.findAllIntegrally(filter, sorter, page));
    }
}
