package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.ContentVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/search")
public class ApiSearchController {
    @Resource
    private BookService bookService;

    @Resource
    private ContentService contentService;

    @GetMapping
    public ApiResponse collection(@RequestParam("kw") String keyword, @RequestParam("type") String type, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        if (Objects.equals(type, "electronic-book") || Objects.equals(type, "audio-book") || Objects.equals(type, "paper-book")) {
            filter = Objects.isNull(filter) ? new Filter() : filter;
            if (Objects.equals(type, "audio-book")) {
                filter.put("title", new String[] {keyword});
            } else {
                filter.put("metadataName", new String[]{keyword});
            }
            PageSlice<BookVO> slice = bookService.findAllIntegrally(BookType.valueOfCode(type), filter, sorter, page);
            return RestfulUtil.list(slice);
        } else {
            filter = Objects.isNull(filter) ? new Filter() : filter;
            filter.put("title", new String[]{keyword});
            filter.put("type", new String[]{ContentType.Topic.getType(), ContentType.Review.getType()});
            sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
            sorter.setField("createTime");
            sorter.setOrder("descend");
            PageSlice<ContentVO> slice = contentService.findAllIntegrally(filter, sorter, page);
            return RestfulUtil.list(slice);
        }
    }
}
