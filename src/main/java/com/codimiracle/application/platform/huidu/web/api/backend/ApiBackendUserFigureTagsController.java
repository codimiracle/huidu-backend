package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.FigureTagsDTO;
import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/user-figures/{user_id}/tags")
public class ApiBackendUserFigureTagsController {
    @Resource
    private UserFigureService userFigureService;

    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse append(@PathVariable("user_id") String userId, @Valid @RequestBody FigureTagsDTO figureTagsDTO) {
        List<Tag> tagsList = TagUtil.mutateToPersistent(tagService, Arrays.asList(figureTagsDTO.getTags()));
        List<FigureTag> figureTagList = tagsList.stream().map((tag) -> {
            FigureTag figureTag = new FigureTag();
            figureTag.setUserId(userId);
            figureTag.setTagId(tag.getId());
            figureTag.setTag(tag);
            figureTag.setScore(BigDecimal.ZERO);
            return figureTag;
        }).collect(Collectors.toList());
        userFigureService.save(figureTagList);
        return RestfulUtil.entity(figureTagList);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable("id") String figureTagId) {
        userFigureService.deleteByIdIntegrally(figureTagId);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        userFigureService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @GetMapping("/most-interesting")
    public ApiResponse mostInteresting(@PathVariable("user_id") String userId) {
        Sorter sorter = new Sorter();
        sorter.setField("score");
        sorter.setOrder("descend");
        Page page = new Page();
        page.setPage(1);
        page.setLimit(10);
        return collection(userId, null, sorter, page);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("user_id") String userId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("userId", new String[]{userId});
        PageSlice<FigureTagVO> slice = userFigureService.findAllTagIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
