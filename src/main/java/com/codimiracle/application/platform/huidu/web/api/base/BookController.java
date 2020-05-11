package com.codimiracle.application.platform.huidu.web.api.base;

import com.codimiracle.application.platform.huidu.entity.dto.BookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.*;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.application.platform.huidu.util.FilterUtil;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
import com.codimiracle.web.basic.contract.*;
import com.codimiracle.web.middleware.content.pojo.eo.Tag;
import com.codimiracle.web.middleware.content.pojo.po.Content;
import com.codimiracle.web.middleware.content.service.ContentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Codimiracle
 */
@Component
public class BookController {
    @Resource
    private BookService bookService;

    @Resource
    private PopularService popularService;

    @Resource
    private BookEpisodeService bookEpisodeService;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @Resource
    private BuryingPointService buryingPointService;
    @Resource
    private ContentService contentService;
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, BookDTO bookDTO) {
        Book book = Book.from(bookDTO);

        Category category = book.getCategory();
        if (Objects.nonNull(category)) {
            if (Objects.isNull(bookDTO.getCategory().getTags())) {
                category.setTags(Collections.emptyList());
            } else {
                category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(bookDTO.getCategory().getTags())));
            }
        }
        if (Objects.nonNull(bookDTO.getTags())) {
            List<String> tagNames = new ArrayList<>(bookDTO.getTags().length + 1);
            if (Arrays.stream(bookDTO.getTags()).noneMatch((tagName) -> Objects.equals(book.getType().getName(), tagName))) {
                tagNames.add(book.getType().getName());
            }
            tagNames.addAll(Arrays.asList(bookDTO.getTags()));
            book.setTags(TagUtil.mutateToPersistent(tagService, tagNames));
        }

        Content content = new Content();
        content.setOwnerId(user.getId());
        content.setCreatedAt(new Date());
        content.setUpdatedAt(content.getCreatedAt());
        content.setType(ContentType.Book.toString());
        book.setContent(content);

        bookService.save(book);
        return RestfulUtil.entity(bookService.findByIdWithTypeIntegrally(book.getType(), book.getId()));
    }

    public ApiResponse delete(@PathVariable String id) {
        bookService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    public ApiResponse deleteBulk(BulkDeletionDTO bulkDeletionDTO) {
        bookService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
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
        if (Objects.nonNull(bookDTO.getTags())) {
            List<String> tagNames = new ArrayList<>(bookDTO.getTags().length + 1);
            if (Arrays.stream(bookDTO.getTags()).noneMatch((tagName) -> Objects.equals(book.getType().getName(), tagName))) {
                tagNames.add(book.getType().getName());
            }
            tagNames.addAll(Arrays.asList(bookDTO.getTags()));
            book.setTags(TagUtil.mutateToPersistent(tagService, tagNames));
        }
        book.setId(id);
        bookService.update(book);
        return RestfulUtil.entity(bookService.findByIdWithTypeIntegrally(book.getType(), id));
    }

    public ApiResponse entity(User user, BookType type, String id, boolean details) {
        if (Objects.nonNull(user) && details) {
            buryingPointService.forBookDetails(user.getId(), id);
        }
        return entity(type, id);
    }

    public ApiResponse entity(BookType type, String id) {
        BookVO bookVO = bookService.findByIdWithTypeIntegrally(type, id);
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
        PageSlice<BookVO> slice = bookService.findByTypeIntegrally(type, filter, sorter, page);
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
        filter = FilterUtil.ensureNotNull(filter);
        filter.put("type", new String[] {type.getType()});
        return RestfulUtil.list(popularService.findPopularBooksIntegrally(filter, sorter, page));
    }

    public ApiResponse reviewStars(String bookId) {
        return RestfulUtil.success(bookService.avgReviewStars(bookId));
    }
}
