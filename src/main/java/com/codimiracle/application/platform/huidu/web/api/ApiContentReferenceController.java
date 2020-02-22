package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.service.ContentReferenceService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//content/reference")
public class ApiContentReferenceController {
    @Resource
    private ContentReferenceService contentReferenceService;

    @PostMapping
    public ApiResponse create(@RequestBody ContentReference contentReference) {
        contentReferenceService.save(contentReference);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        contentReferenceService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody ContentReference contentReference) {
        contentReferenceService.update(contentReference);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ContentReference contentReference = contentReferenceService.findById(id);
        return RestfulUtil.success(contentReference);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ContentReference> slice = contentReferenceService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
