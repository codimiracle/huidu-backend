package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.AudioBookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/creator/audio-books")
public class ApiCreatorAudioBookController {
    @Autowired
    private BookController bookController;

    @Resource
    private ContentService contentService;
    @Resource
    private BookService bookService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @Valid @RequestBody AudioBookDTO audioBookDTO) {
        audioBookDTO.setPublishYear(DateFormatUtils.format(new Date(), "yyyy"));
        audioBookDTO.setTeller(user.getNickname());
        audioBookDTO.setStatus(BookAudioEpisodeStatus.Examining.toString());
        return bookController.create(user, audioBookDTO.toBookDTO());
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        Book book = bookService.findById(id);
        Content content = contentService.findById(book.getContentId());
        if (Objects.equals(user.getId(), content.getOwnerId())) {
            return bookController.delete(id);
        } else {
            return RestfulUtil.fail("权限不足！");
        }
    }

    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        bookController.deleteBulk(bulkDeletionDTO);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody AudioBookDTO audioBookDTO) {
        Book book = bookService.findById(id);
        Content content = contentService.findById(book.getContentId());
        if (Objects.equals(user.getId(), content.getOwnerId())) {
            return bookController.update(id, audioBookDTO.toBookDTO());
        } else {
            return RestfulUtil.fail("权限不足!");
        }
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookController.entity(BookType.AudioBook, id);
    }

    @GetMapping("/{id}/catalogs")
    public ApiResponse catalogs(@PathVariable("id") String id) {
        return bookController.audioCatalogs(id);
    }


    @GetMapping("/search")
    public ApiResponse search(@AuthenticationPrincipal User user, @RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("title", new String[]{keyword});
        Page page = new Page();
        page.setLimit(10);
        page.setPage(1);
        return collection(user, filter, null, page);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("ownerId", new String[]{user.getId()});
        return bookController.collection(BookType.AudioBook, filter, sorter, page);
    }
}
