package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.DiscoverVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.PopularService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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
    private PopularService popularService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserFigureService userFigureService;

    @GetMapping("/by-reads")
    public ApiResponse mostread(@RequestParam("type") String bookType, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("reads");
        sorter.setOrder("descend");
        PageSlice<BookVO> slice = bookService.findByTypeIntegrally(BookType.valueOfCode(bookType), filter, sorter, page);
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
    public ApiResponse recommendsByCategory(@AuthenticationPrincipal User user, @RequestParam("category_id") String categoryId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByCategoryId(categoryId, Objects.nonNull(user) ? user.getId() : null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-book")
    public ApiResponse recommendsByBook(@AuthenticationPrincipal User user, @RequestParam("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByBookId(bookId, Objects.nonNull(user) ? user.getId() : null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-book-type")
    public ApiResponse recommendsByBookType(@AuthenticationPrincipal User user, @RequestParam("type") String type, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByBookType(BookType.valueOfCode(type), Objects.nonNull(user) ? user.getId() : null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-tag")
    public ApiResponse recommendsByTag(@AuthenticationPrincipal User user, @RequestParam("tag_id") String tagId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByTagId(tagId, Objects.nonNull(user) ? user.getId() : null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/by-today")
    public ApiResponse recommendsByToday(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookVO> slice = bookService.findAllUsingUserFigureByHistoryToday(Objects.nonNull(user) ? user.getId() : null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/discover")
    public ApiResponse recommendsForDiscover(@AuthenticationPrincipal User user) {
        DiscoverVO discoverVO = new DiscoverVO();
        Page firstPage = new Page(1, 4);
        Sorter newCollection = new Sorter();
        newCollection.setField("id");
        newCollection.setOrder("descend");
        discoverVO.setCategories(categoryService.findAllIntegrally(CategoryType.Collection, null, newCollection, firstPage).getList());
        discoverVO.setHotBooks(popularService.findPopularBooksIntegrally(null, null, firstPage).getList());
        discoverVO.setMaybeLikes(bookService.findAllUsingUserFigureByAvgIntegrally(null, null, firstPage).getList());
        Filter thisYears = new Filter();
        thisYears.put("years", new String[]{DateFormatUtils.format(new Date(), "yyyy")});
        discoverVO.setNewBooks(bookService.findByTypeIntegrally(null, thisYears, null, firstPage).getList());
        Sorter sorter = new Sorter();
        sorter.setField("sales");
        sorter.setOrder("descend");
        discoverVO.setSalesBooks(bookService.findByTypeIntegrally(BookType.PaperBook, null, sorter, firstPage).getList());
        Filter fiveStar = new Filter();
        fiveStar.put("rate", new String[]{"4.0"});
        discoverVO.setStarsBooks(bookService.findByTypeIntegrally(null, fiveStar, sorter, firstPage).getList());
        discoverVO.setTodayBooks(bookService.findAllUsingUserFigureByHistoryToday(Objects.nonNull(user) ? user.getId() : null, null, null, firstPage).getList());
        return RestfulUtil.success(discoverVO);
    }
}
