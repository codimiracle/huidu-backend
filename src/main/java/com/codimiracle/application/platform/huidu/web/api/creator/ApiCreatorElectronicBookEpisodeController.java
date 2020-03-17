package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookEpisodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

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
