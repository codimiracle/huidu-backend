package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//content/article")
public class ApiContentArticleController {
    @Resource
    private ContentArticleService contentArticleService;

    @PostMapping
    public ApiResponse create(@RequestBody ContentArticle contentArticle) {
        contentArticleService.save(contentArticle);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        contentArticleService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody ContentArticle contentArticle) {
        contentArticleService.update(contentArticle);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ContentArticle contentArticle = contentArticleService.findById(id);
        return RestfulUtil.success(contentArticle);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ContentArticle> slice = contentArticleService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
