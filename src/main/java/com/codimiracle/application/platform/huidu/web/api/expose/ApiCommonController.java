package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookService;
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
@RequestMapping("/api/common")
public class ApiCommonController {

    @Resource
    private BookService bookService;

    @GetMapping("/most-read")
    public ApiResponse mostread(@RequestParam("type") String bookType, @ModelAttribute Page page) {
        Filter filter = new Filter();
        filter.put("status", new String[] {BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        Sorter sorter = new Sorter();
        sorter.setField("reads");
        sorter.setOrder("descend");
        PageSlice<BookVO> slice = bookService.findAllIntegrally(BookType.valueOfCode(bookType), filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/recommends")
    public ApiResponse recommends(@AuthenticationPrincipal User user, @ModelAttribute Page page) {
        if (Objects.isNull(user)) {

        }
        Filter filter = new Filter();
        filter.put("status", new String[] {BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        Sorter sorter = new Sorter();
        sorter.setField("reads");
        sorter.setOrder("descend");
        PageSlice<BookVO> slice = bookService.findAllIntegrally(BookType.ElectronicBook, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
