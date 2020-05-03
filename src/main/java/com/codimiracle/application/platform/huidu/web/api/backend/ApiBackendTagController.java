package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.TagDTO;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/classification/tags")
public class ApiBackendTagController {
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody TagDTO tagDTO) {
        Tag tag = tagService.findByTagName(tagDTO.getName());
        if (Objects.nonNull(tag)) {
            if (tag.isDeleted()) {
                tag.setDeleted(false);
                tagService.update(tag);

            } else {
                return RestfulUtil.fail("标签已存在！");
            }
        } else {
            tag = new Tag();
            tag.setName(tagDTO.getName());
            tagService.save(tag);
        }
        return RestfulUtil.entity(tagService.findByIdIntegrally(tag.getId()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        tagService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        tagService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody TagDTO tagDTO) {
        Tag tag = tagService.findByTagName(tagDTO.getName());
        if (Objects.nonNull(tag)) {
            if (tag.isDeleted()) {
                tag.setDeleted(false);
                tagService.update(tag);
            } else {
                return RestfulUtil.fail("标签名称已经存在！");
            }
        } else {
            tag = new Tag(tagDTO.getName());
            tag.setId(id);
            tagService.update(tag);
        }
        return RestfulUtil.entity(tagService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        TagVO tagVO = tagService.findByIdIntegrally(id);
        return RestfulUtil.entity(tagVO);
    }

    @GetMapping("/exists")
    public ApiResponse exists(@RequestParam("name") String name) {
        Tag tag = tagService.findByTagName(name);
        if (Objects.nonNull(tag)) {
            return RestfulUtil.success();
        } else {
            return RestfulUtil.fontFound();
        }
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(@RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("name", new String[]{keyword});
        Sorter sorter = null;
        Page page = new Page();
        page.setPage(1);
        page.setLimit(10);
        PageSlice<TagVO> slice = tagService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<TagVO> slice = tagService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
