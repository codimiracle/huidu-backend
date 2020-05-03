package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.dto.CartItemDTO;
import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import com.codimiracle.application.platform.huidu.service.UserCartService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.StringifizationUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/user/cart")
public class ApiUserCartController {
    @Resource
    private UserCartService userCartService;

    @PostMapping("/join")
    public ApiResponse join(@AuthenticationPrincipal User user, @Valid @RequestBody CartItemDTO cartItemDTO) {
        CartItem cartItem = CartItem.from(cartItemDTO);
        cartItem.setUserId(user.getId());
        cartItem.setJoinTime(new Date());
        userCartService.save(cartItem);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userCartService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@RequestParam String ids) {
        userCartService.deleteByIdsLogically(StringifizationUtil.toList(ids));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody CartItemDTO cartItemDTO) {
        CartItem updatingCartItem = CartItem.from(cartItemDTO);
        updatingCartItem.setId(id);
        userCartService.update(updatingCartItem);
        return RestfulUtil.entity(userCartService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable String id) {
        CartItemVO cartItemVO = userCartService.findByIdIntegrally(id);
        if (Objects.isNull(cartItemVO) || !Objects.equals(user.getId(), cartItemVO.getUserId())) {
            return RestfulUtil.fail("没有找到对应的购物车项目！");
        }
        return RestfulUtil.success(cartItemVO);
    }

    @GetMapping("/total-count")
    public ApiResponse totalCount(@AuthenticationPrincipal User user) {
        return RestfulUtil.success(userCartService.countByUserId(user.getId()));
    }

    @GetMapping("/by-ids")
    public ApiResponse collectionByIds(String ids) {
        List<CartItemVO> items = userCartService.findByIdsIntegrally(StringifizationUtil.toList(ids));
        return RestfulUtil.list(new PageSlice<>(items));
    }
    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("userId", new String[] {user.getId()});
        PageSlice<CartItemVO> slice = userCartService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
