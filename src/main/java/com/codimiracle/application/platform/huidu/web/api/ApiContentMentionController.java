package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ContentMention;
import com.codimiracle.application.platform.huidu.service.ContentMentionService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//content/mention")
public class ApiContentMentionController {
    @Resource
    private ContentMentionService contentMentionService;

    @PostMapping
    public ApiResponse create(@RequestBody ContentMention contentMention) {
        contentMentionService.save(contentMention);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        contentMentionService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody ContentMention contentMention) {
        contentMentionService.update(contentMention);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ContentMention contentMention = contentMentionService.findById(id);
        return RestfulUtil.success(contentMention);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ContentMention> slice = contentMentionService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
