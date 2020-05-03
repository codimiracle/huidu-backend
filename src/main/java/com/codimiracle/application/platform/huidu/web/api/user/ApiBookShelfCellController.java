package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/shelves/default/cells")
public class ApiBookShelfCellController {
    @Resource
    private BookShelfCellService bookShelfCellService;

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookShelfCellService.deleteById(id);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookShelfCell bookShelfCell = bookShelfCellService.findById(id);
        return RestfulUtil.success(bookShelfCell);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @ModelAttribute Page page) {
        Filter filter = new Filter();
        filter.put("ownerId", new String[]{user.getId()});
        Sorter sorter = new Sorter();
        sorter.setOrder("descend");
        sorter.setField("readTime");
        PageSlice<BookShelfCellVO> slice = bookShelfCellService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
