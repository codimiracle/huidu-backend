package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.BookAudioEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.web.api.base.BookAudioEpisodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/audio-books/{book_id}/episodes")
public class ApiBackendBookAudioEpisodeController {
    @Autowired
    private BookAudioEpisodeController bookAudioEpisodeController;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        return bookAudioEpisodeController.create(user, bookId, bookAudioEpisodeDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        return bookAudioEpisodeController.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("id") String id, @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        return bookAudioEpisodeController.update(id, bookAudioEpisodeDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookAudioEpisodeController.entity(id);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookAudioEpisodeController.collection(bookId, filter, sorter, page);
    }
}
