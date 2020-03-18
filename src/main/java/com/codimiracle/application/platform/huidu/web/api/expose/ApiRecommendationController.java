package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/recommendation")
public class ApiRecommendationController {

    @Resource
    private BookService bookService;

    @Resource
    private UserFigureService userFigureService;

    @GetMapping("/by-reads")
    public ApiResponse mostread(@RequestParam("type") String bookType, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("reads");
        sorter.setOrder("descend");
        PageSlice<BookVO> slice = bookService.findAllIntegrally(BookType.valueOfCode(bookType), filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-user")
    public ApiResponse recommendsByUser(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        //匿名用户推荐
        PageSlice<BookVO> slice;
        if (Objects.isNull(user)) {
            slice = bookService.findAllUsingUserFigureByAvgIntegrally(filter, sorter, page);
        } else {
            slice = bookService.findAllUsingUserFigureByUserIdIntegrally(user.getId(), filter, sorter, page);
        }
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-category")
    public ApiResponse recommendsByCategory(@RequestParam("category_id") String categoryId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByCategoryId(categoryId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-tag")
    public ApiResponse recommendsByTag(@RequestParam("tag_id") String tagId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByTagId(tagId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
