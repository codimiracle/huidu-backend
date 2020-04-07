package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.UserRoleDTO;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import com.codimiracle.application.platform.huidu.service.UserRoleService;
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
@RequestMapping("/api/backend/system/roles")
public class ApiBackendUserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        UserRole userRole = UserRole.from(userRoleDTO);
        userRoleService.save(userRole);
        return RestfulUtil.entity(userRole);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userRoleService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(String[] ids) {
        userRoleService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }


    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        UserRole userRole = UserRole.from(userRoleDTO);
        Objects.requireNonNull(userRole);
        userRole.setId(id);
        userRoleService.update(userRole);
        return RestfulUtil.entity(userRoleService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserRoleVO userRoleVO = userRoleService.findByIdIntegrally(id);
        return RestfulUtil.entity(userRoleVO);
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(String keyword, @ModelAttribute Page page) {
        Filter filter = new Filter();
        filter.put("name", new String[]{keyword});
        PageSlice<UserRoleVO> slice = userRoleService.findAllIntegrally(filter, null, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<UserRoleVO> slice = userRoleService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
