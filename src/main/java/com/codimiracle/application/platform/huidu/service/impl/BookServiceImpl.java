package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.*;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.mapper.BookMapper;
import com.codimiracle.application.platform.huidu.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookServiceImpl extends AbstractService<String, Book> implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookMetadataService bookMetadataService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private ContentService contentService;
    @Resource
    private BookTagsService bookTagsService;
    @Resource
    private TagService tagService;
    @Resource
    private UserCartService userCartService;
    @Resource
    private ContentExaminationService contentExaminationService;
    @Resource
    private BookShelfService bookShelfService;

    @Override
    public void save(Book model) {
        if (model.getType() == BookType.ElectronicBook || model.getType() == BookType.PaperBook) {
            bookMetadataService.save(model.getMetadata());
            model.setMetadataId(model.getMetadata().getId());
        }
        if (model.getType() == BookType.PaperBook) {
            commodityService.save(model.getCommodity());
            model.setCommodityId(model.getCommodity().getId());
        }
        Optional.ofNullable(model.getCategory()).ifPresent((category) -> {
            categoryService.save(category);
            model.setCategoryId(category.getId());
        });
        Content content = model.getContent();
        contentService.save(content);
        model.setContentId(content.getId());
        super.save(model);
        if (Objects.nonNull(model.getTags())) {
            persistentTags(model);
        }
    }

    private void examinate(String id, String reason, String userId, BookStatus status) {
        Book book = findByContentId(id);
        if (!Objects.equals(book.getStatus(), BookStatus.Examining)) {
            throw new ServiceException("无法审查该内容！");
        }
        ContentExamination examination = new ContentExamination();
        examination.setFromStatus(book.getStatus().toString());
        examination.setToStatus(status.getStatus());
        examination.setTargetContentId(id);
        examination.setReason(reason);
        examination.setUserId(userId);
        examination.setExamineTime(new Date());
        contentExaminationService.save(examination);
    }

    private Book findByContentId(String id) {
        return findBy("contentId", id);
    }

    @Override
    public void passExamination(String id, String reason, String userId) {
        examinate(id, reason, userId, BookStatus.Serializing);
    }

    @Override
    public void rejectExamination(String id, String reason, String userId) {
        examinate(id, reason, userId, BookStatus.Rejected);
    }

    @Override
    public void update(Book model) {
        Optional.ofNullable(model.getCategory()).ifPresent((category -> {
            categoryService.save(category);
            model.setCategoryId(category.getId());
        }));
        Optional.ofNullable(model.getMetadata()).ifPresent((bookMetadata) -> {
            bookMetadataService.update(bookMetadata);
        });
        Optional.ofNullable(model.getCommodity()).ifPresent((commodity -> {
            commodityService.update(commodity);
        }));

        if (Objects.nonNull(model.getTags())) {
            persistentTags(model);
        }
        Content content = new Content();
        content.setId(model.getId());
        content.setUpdateTime(new Date());
        contentService.update(content);
        super.update(model);
    }

    private void persistentTags(Book model) {
        List<BookTags> allBookTags = bookTagsService.findByBookId(model.getId());
        List<Tag> newTags = model.getTags().stream().filter((t) -> Objects.isNull(t.getId())).collect(Collectors.toList());
        newTags.forEach(tagService::save);
        Map<String, Tag> tagsMap = model.getTags().stream().collect(Collectors.toMap(Tag::getId, (tag) -> tag));
        allBookTags.forEach((bookTags -> {
            if (tagsMap.containsKey(bookTags.getTagId())) {
                bookTags.setDeleted(false);
            } else {
                bookTags.setDeleted(true);
            }
            tagsMap.remove(bookTags.getTagId());
        }));
        allBookTags.forEach(bookTagsService::update);
        List<BookTags> bookTagsList = tagsMap.values().stream().map((t) -> BookTags.valueOf(model, t)).collect(Collectors.toList());
        bookTagsList.forEach(bookTagsService::save);
    }

    private void mutate(BookVO bookVO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(bookVO)) {
            if (Objects.nonNull(bookVO.getCategoryId())) {
                bookVO.setCategory(categoryService.findByIdIntegrally(bookVO.getCategoryId()));
            }
            if (Objects.nonNull(bookVO.getCommodityId())) {
                bookVO.setCommodity(commodityService.findByIdIntegrally(bookVO.getCommodityId()));
            }
            if (principal instanceof User) {
                User user = (User) principal;
                if (Objects.nonNull(bookVO.getCommodityId())) {
                    bookVO.setJoinedCart(userCartService.isJoined(user.getId(), bookVO.getCommodityId()));
                }
                bookVO.setJoinedShelf(bookShelfService.isJoined(user.getId(), bookVO.getId()));
            }
            bookVO.setExamination(contentExaminationService.findLastExaminationByContentId(bookVO.getContentId()));
            bookVO.setReviewRate(avgReviewStars(bookVO.getId()));
            bookVO.setTags(bookTagsService.findByBookIdItegrally(bookVO.getId()));
        }
    }

    @Override
    public BookVO findPublishByIdIntegrally(BookType type, String id) {
        BookVO bookVO = findByIdIntegrally(type, id);
        if (Objects.equals(bookVO.getStatus(), BookStatus.Serializing.getStatus()) || Objects.equals(bookVO.getStatus(), BookStatus.Ended.getStatus()) || Objects.equals(bookVO.getStatus(), BookStatus.Paused.getStatus())) {
            return bookVO;
        }
        return null;
    }

    @Override
    public BookVO findByIdIntegrally(BookType type, String id) {
        BookVO bookVO = bookMapper.selectByIdIntegrally(type, id);
        mutate(bookVO);
        return bookVO;
    }

    @Override
    public BookVO findByIdIntegrally(String id) {
        return findByIdIntegrally(null, id);
    }

    @Override
    public PageSlice<BookVO> findAllIntegrally(BookType type, Filter filter, Sorter sorter, Page page) {
        PageSlice<BookVO> slice = extractPageSlice(bookMapper.selectAllIntegrally(type, filter, sorter, page));
        List<BookVO> list = slice.getList();
        list.forEach(this::mutate);
        return slice;
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        bookMapper.deleteByIdsLogically(ids);
    }

    @Override
    public PageSlice<BookVO> findByCategoryIdIntegrally(String categoryId, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(bookMapper.selectByCategoryIdIntegrally(categoryId, filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findByCollectionIdIntegrally(String categoryId, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(bookMapper.selectByCollectionIdIntegrally(categoryId, filter, sorter, page));
    }

    @Override
    public List<String> retrivePublishYearsByType(BookType bookType) {
        return bookMapper.selectPublishYearsByType(bookType);
    }

    @Override
    public void readsIncrement(String id) {
        bookMapper.readsIncrement(id);
    }

    @Override
    public void playsIncrement(String id) {
        bookMapper.playsIncrement(id);
    }

    @Override
    public BookVO findByContentIdIntegrally(String contentId) {
        return bookMapper.selectByContentIdIntegrally(contentId);
    }

    private Filter ensurePublish(Filter filter) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Paused.toString(), BookStatus.Ended.toString()});
        return filter;
    }

    private Sorter orderByHotDegree(Sorter sorter) {
        sorter = Objects.isNull(sorter) ? new Sorter() : sorter;
        sorter.setField("hotDegree");
        sorter.setOrder("descend");
        return sorter;
    }

    @Override
    public PageSlice<BookVO> findAllHotIntegrally(BookType type, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        sorter = orderByHotDegree(sorter);
        return findAllIntegrally(type, filter, sorter, page);
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByUserIdIntegrally(String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByUserIdIntegrally(userId, filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByAvgIntegrally(Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByAvgIntegrally(filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByTagId(String tagId, String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByTagId(tagId, userId, filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByCategoryId(String categoryId, String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByCategoryId(categoryId, userId, filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByBookId(String bookId, String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByBookId(bookId, userId, filter, sorter, page));
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByHistoryToday(String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByHistoryToday(userId, filter, sorter, page));
    }

    @Override
    public Book findByCommodityId(String commodityId) {
        return findBy("commodityId", commodityId);
    }

    @Override
    public PageSlice<BookVO> findAllUsingUserFigureByBookType(BookType type, String userId, Filter filter, Sorter sorter, Page page) {
        filter = ensurePublish(filter);
        return extractPageSlice(bookMapper.selectAllUsingUserFigureByBookType(type, userId, filter, sorter, page));
    }

    @Override
    public Float avgReviewStars(String bookId) {
        return bookMapper.avgReviewRate(bookId);
    }

}
