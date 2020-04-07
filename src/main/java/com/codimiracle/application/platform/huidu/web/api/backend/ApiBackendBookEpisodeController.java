package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.web.api.base.BookEpisodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/electronic-books/{book_id}/episodes")
public class ApiBackendBookEpisodeController {
    @Autowired
    private BookEpisodeController bookEpisodeController;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        return bookEpisodeController.create(user, bookId, bookEpisodeDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        return bookEpisodeController.delete(id);
    }

    @DeleteMapping
    public ApiResponse delete(String[] ids) {
        return bookEpisodeController.delete(ids);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        return bookEpisodeController.update(id, bookEpisodeDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookEpisodeController.entity(id);
    }

    @GetMapping("/last-update-episode")
    public ApiResponse lastUpdateEpisode(@PathVariable("book_id") String bookId) {
        return bookEpisodeController.lastUpdateEpisode(bookId);
    }

    @GetMapping("/last-episode-number")
    public ApiResponse lastEpisodeNumber(@PathVariable("book_id") String bookId) {
        return bookEpisodeController.lastEpisodeNumber(bookId);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookEpisodeController.collection(bookId, filter, sorter, page);
    }
}
