package com.codimiracle.application.platform.huidu.web.api.base;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BookDTO;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.*;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@Component
public class BookController {
    @Resource
    private BookService bookService;

    @Resource
    private BookEpisodeService bookEpisodeService;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @Resource
    private ContentService contentService;
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, BookDTO bookDTO) {
        Book book = Book.from(bookDTO);
        Content content = new Content();
        content.setOwnerId(user.getId());
        content.setCreateTime(new Date());
        content.setUpdateTime(content.getCreateTime());
        content.setType(ContentType.Book);
        book.setContent(content);
        Category category = book.getCategory();
        if (Objects.nonNull(category)) {
            category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(bookDTO.getCategory().getTags())));
        }
        if (Objects.nonNull(book.getTags())) {
            book.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(bookDTO.getTags())));
        }
        bookService.save(book);
        return RestfulUtil.entity(bookService.findByIdIntegrally(book.getType(), book.getId()));
    }

    public ApiResponse delete(@PathVariable String id) {
        bookService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    public ApiResponse delete(String[] ids) {
        bookService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    public ApiResponse update(String id, BookDTO bookDTO) {
        Book book = Book.from(bookDTO);
        //电子书更新元数据，有声书和纸质书不能更新元数据
        if (Objects.nonNull(book.getMetadata())) {
            book.getMetadata().setId(book.getMetadataId());
        }
        if (Objects.nonNull(book.getCommodity())) {
            book.getCommodity().setId(book.getCommodityId());
        }
        if (Objects.nonNull(book.getTags())) {
            book.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(bookDTO.getTags())));
        }
        book.setId(id);
        bookService.update(book);
        return RestfulUtil.entity(bookService.findByIdIntegrally(book.getType(), id));
    }

    public ApiResponse entity(BookType type, String id) {
        BookVO bookVO = bookService.findByIdIntegrally(type, id);
        return RestfulUtil.entity(bookVO);
    }

    public ApiResponse readsIncrement(String id) {
        bookService.readsIncrement(id);
        return RestfulUtil.success();
    }

    public ApiResponse playsIncrement(String id) {
        bookService.playsIncrement(id);
        return RestfulUtil.success();
    }

    public ApiResponse collection(BookType type, Filter filter, Sorter sorter, Page page) {
        PageSlice<BookVO> slice = bookService.findAllIntegrally(type, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
    
    public ApiResponse publishCollection(BookType type, Filter filter, Sorter sorter, Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Paused.toString(), BookStatus.Ended.toString()});
        return collection(type, filter, sorter, page);
    }

    public ApiResponse catalogs(String id) {
        List<Catalogs> list = bookEpisodeService.findCatalogsByBookIdAndStatus(id, null);
        return RestfulUtil.success(list);
    }

    public ApiResponse publishedCatalogs(String id) {
        List<Catalogs> list = bookEpisodeService.findCatalogsByBookIdAndStatus(id, ContentStatus.Publish);
        return RestfulUtil.success(list);
    }

    public ApiResponse audioCatalogs(String id) {
        List<AudioCatalogs> list = bookAudioEpisodeService.findCatalogsByBookIdAndStatus(id, null);
        return RestfulUtil.success(list);
    }

    public ApiResponse publishedAudioCatalogs(String id) {
        List<AudioCatalogs> list = bookAudioEpisodeService.findCatalogsByBookIdAndStatus(id, BookAudioEpisodeStatus.Publish);
        return RestfulUtil.success(list);
    }

    public ApiResponse publishYears(BookType bookType) {
        return RestfulUtil.success(bookService.retrivePublishYearsByType(bookType));
    }

    public ApiResponse hotCollection(BookType type, Filter filter, Sorter sorter, Page page) {
        return RestfulUtil.list(bookService.findAllHotIntegrally(type, filter, sorter, page));
    }
}
