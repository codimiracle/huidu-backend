package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BookNotesDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookNotesVO;
import com.codimiracle.application.platform.huidu.entity.vt.BookNoteCollection;
import com.codimiracle.application.platform.huidu.service.BookNotesService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Codimiracle
 */

@CrossOrigin
@RestController
@RequestMapping("/api/user/book-notes")
public class ApiBookNotesController {
    @Resource
    private BookNotesService bookNotesService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @RequestBody BookNotesDTO bookNotesDTO) {
        if (bookNotesService.isDommarkExists(bookNotesDTO.getDommark())) {
            return RestfulUtil.fail("附近已有笔记标注，请删除后重试！");
        }
        BookNotes bookNotes = BookNotes.from(bookNotesDTO);
        bookNotes.setUserId(user.getId());
        bookNotes.setCreateTime(new Date());
        bookNotes.setUpdateTime(bookNotes.getCreateTime());
        bookNotesService.save(bookNotes);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{book_id}/notes/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookNotesService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{book_id}/notes/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody BookNotesDTO bookNotesDTO) {
        BookNotes bookNotes = BookNotes.from(bookNotesDTO);
        bookNotes.setUpdateTime(new Date());
        Assert.notNull(bookNotes, "数据转换后为空！");
        bookNotes.setId(id);
        bookNotesService.update(bookNotes);
        return RestfulUtil.success();
    }

    @GetMapping("/{book_id}")
    public ApiResponse bookNotesCollection(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId) {
        BookNoteCollection bookNoteCollection = bookNotesService.findBookNotesCollectionByBookIdAndUserId(bookId, user.getId());
        return RestfulUtil.entity(bookNoteCollection);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookNotesVO> slice = bookNotesService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
