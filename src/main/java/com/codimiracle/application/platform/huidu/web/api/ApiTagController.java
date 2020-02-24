package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.TagDTO;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("/api/tags")
public class ApiTagController {
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@RequestBody TagDTO tagDTO) {
        Tag tag = new Tag(tagDTO.getName());
        tagService.save(tag);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        tagService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse delete(String[] ids) {
        tagService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody TagDTO tagDTO) {
        Tag tag = new Tag(tagDTO.getName());
        tag.setId(id);
        tagService.update(tag);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        TagVO tagVO = tagService.findByIdIntegrally(id);
        return RestfulUtil.success(tagVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<TagVO> slice = tagService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
