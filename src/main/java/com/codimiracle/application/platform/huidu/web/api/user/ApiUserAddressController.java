package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.AddressDTO;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;
import com.codimiracle.application.platform.huidu.service.AddressService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/addresses")
public class ApiUserAddressController {
    @Resource
    private AddressService addressService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @RequestBody AddressDTO addressDTO) {
        Address address = Address.from(addressDTO);
        address.setUserId(user.getId());
        addressService.save(address);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        Address original = addressService.findById(id);
        if (Objects.isNull(original) || !Objects.equals(original.getUserId(), user.getId())) {
            return RestfulUtil.fail("权限不足！");
        } else {
            addressService.deleteByIdLogically(id);
            return RestfulUtil.success();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable("id") String id, @RequestBody AddressDTO addressDTO) {
        Address original = addressService.findById(id);
        if (Objects.isNull(original) || !Objects.equals(original.getUserId(), user.getId())) {
            return RestfulUtil.fail("权限不足！");
        }
        Address address = Address.from(addressDTO);
        address.setId(id);
        addressService.update(address);
        return RestfulUtil.entity(addressService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        AddressVO addressVO = addressService.findByIdIntegrally(id);
        return RestfulUtil.entity(addressVO);
    }

    @PostMapping("/default")
    public ApiResponse makeDefaultAddress(@AuthenticationPrincipal User user, @RequestBody Map<String, String> parameters) {
        addressService.defaultAddress(user.getId(), parameters.get("addressId"));
        return RestfulUtil.success();
    }

    @GetMapping("/default")
    public ApiResponse defaultAddress(@AuthenticationPrincipal User user) {
        AddressVO addressVO = addressService.findDefaultByUserIdIntegrally(user.getId());
        return RestfulUtil.entity(addressVO);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("userId", new String[]{user.getId()});
        PageSlice<AddressVO> slice = addressService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
