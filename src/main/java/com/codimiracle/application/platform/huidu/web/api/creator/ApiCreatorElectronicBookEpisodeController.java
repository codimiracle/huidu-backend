package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.web.api.base.BookEpisodeController;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/creator/episodes")
public class ApiCreatorElectronicBookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @Autowired
    private BookEpisodeController bookEpisodeController;

    @GetMapping("/suggestion")
    public ApiResponse suggestion(@AuthenticationPrincipal User user, @RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("title", new String[] {keyword});
        filter.put("ownerId", new String[] {user.getId()});
        Page page = new Page();
        return bookEpisodeController.collection(null, filter, null, page);
    }

}
