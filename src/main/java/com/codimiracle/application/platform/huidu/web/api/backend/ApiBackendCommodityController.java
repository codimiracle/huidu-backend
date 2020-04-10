package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.CommodityDTO;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.entity.vo.CommodityVO;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
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
@RequestMapping("/api/backend/shopping/commodities")
public class ApiBackendCommodityController {
    @Resource
    private CommodityService commodityService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody CommodityDTO commodityDTO) {
        Commodity commodity = Commodity.from(commodityDTO);
        commodityService.save(commodity);
        return RestfulUtil.entity(commodityService.findByIdIntegrally(commodity.getId()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        commodityService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        commodityService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody CommodityDTO commodityDTO) {
        Commodity commodity = Commodity.from(commodityDTO);
        Objects.requireNonNull(commodity);
        commodity.setId(id);
        commodityService.update(commodity);
        return RestfulUtil.entity(commodityService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CommodityVO commodityVO = commodityService.findByIdIntegrally(id);
        return RestfulUtil.success(commodityVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CommodityVO> slice = commodityService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
