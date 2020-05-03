package com.codimiracle.application.platform.huidu.web.api.expose;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.codimiracle.application.platform.huidu.entity.dto.ComprehensivePageDTO;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.SettingsService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.StringifizationUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.PageSlice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.codimiracle.application.platform.huidu.enumeration.Settings.COMPREHENSIVE_PAGE_CATEGORIES;
import static com.codimiracle.application.platform.huidu.enumeration.Settings.COMPREHENSIVE_PAGE_COLLECTIONS;

@CrossOrigin
@RestController
public class ApiComprehensivePageController {


    @Resource
    private CategoryService categoryService;
    @Resource
    private SettingsService settingsService;
    @PostMapping("/api/backend/comprehensive-page")
    public ApiResponse comprehensivePage(@Valid @RequestBody ComprehensivePageDTO comprehensivePageDTO) {
        settingsService.save(COMPREHENSIVE_PAGE_CATEGORIES, StringifizationUtil.toString(Arrays.asList(comprehensivePageDTO.getCategoryIds())));
        settingsService.save(COMPREHENSIVE_PAGE_COLLECTIONS, StringifizationUtil.toString(Arrays.asList(comprehensivePageDTO.getCollectionIds())));
        return RestfulUtil.success();
    }

    @GetMapping("/api/comprehensive-page/categories")
    public ApiResponse categories() {
        String ids = settingsService.retrieve(COMPREHENSIVE_PAGE_CATEGORIES);
        List<CategoryVO> categories = categoryService.findByIdsIntegrally(StringifizationUtil.toList(ids));
        PageSlice<CategoryVO> slice = new PageSlice<>();
        slice.setList(categories);
        return RestfulUtil.list(slice);
    }
    @GetMapping("/api/comprehensive-page/collections")
    public ApiResponse collections() {
        String ids = settingsService.retrieve(COMPREHENSIVE_PAGE_COLLECTIONS);
        List<CategoryVO> categories = categoryService.findByIdsIntegrally(StringifizationUtil.toList(ids));
        PageSlice<CategoryVO> slice = new PageSlice<>();
        slice.setList(categories);
        return RestfulUtil.list(slice);
    }
}
