package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.mapper.BookMapper;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.po.Content;
import com.codimiracle.web.middleware.content.service.ContentService;
import com.codimiracle.web.middleware.content.service.ContentTagsService;
import com.codimiracle.web.middleware.content.service.ExaminationService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.beans.BeanUtils;
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
public class BookServiceImpl extends AbstractService<String, Book, BookVO> implements BookService {
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
    private TagService tagService;
    @Resource
    private ContentTagsService contentTagsService;
    @Resource
    private UserCartService userCartService;
    @Resource
    private ExaminationService examinationService;
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

        List<Tag> newTags = model.getTags().stream().filter(tag -> Objects.isNull(tag.getId())).collect(Collectors.toList());
        if (!newTags.isEmpty()) {
            tagService.save(newTags);
        }
        Content content = model.getContent();
        content.setTagList(new ArrayList<>(model.getTags()));

        contentService.save(content);
        model.setContentId(content.getId());
        super.save(model);
    }

    private Book findByContentId(String id) {
        return findBy("contentId", id);
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

        Content content = new Content();
        BeanUtils.copyProperties(model, content);
        if (Objects.nonNull(model.getTags()) && !model.getTags().isEmpty()) {
            List<Tag> newTags = model.getTags().stream().filter(tag -> Objects.isNull(tag.getId())).collect(Collectors.toList());
            if (!newTags.isEmpty()) {
                tagService.save(newTags);
            }
            content.setTagList(new ArrayList<>(model.getTags()));
        }
        content.setId(findContentIdByBookId(model.getId()));
        content.setUpdatedAt(new Date());
        contentService.update(content);
        super.update(model);
    }

    @Override
    protected BookVO mutate(BookVO bookVO) {
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
            bookVO.setExamination(examinationService.findLastByContentIdIntegrally(bookVO.getContentId()));
            bookVO.setReviewRate(avgReviewStars(bookVO.getId()));
            bookVO.setTagList(contentTagsService.findTagByContentId(bookVO.getContentId()));
        }
        return bookVO;
    }

    @Override
    public BookVO findPublishByIdIntegrally(BookType type, String id) {
        BookVO bookVO = findByIdWithTypeIntegrally(type, id);
        if (Objects.equals(bookVO.getStatus(), BookStatus.Serializing.getStatus()) || Objects.equals(bookVO.getStatus(), BookStatus.Ended.getStatus()) || Objects.equals(bookVO.getStatus(), BookStatus.Paused.getStatus())) {
            return bookVO;
        }
        return null;
    }

    @Override
    public BookVO findByIdWithTypeIntegrally(BookType type, String id) {
        BookVO bookVO = bookMapper.selectByIdWithTypeIntegrally(type, id);
        mutate(bookVO);
        return bookVO;
    }

    @Override
    public PageSlice<BookVO> findByTypeIntegrally(BookType type, Filter filter, Sorter sorter, Page page) {
        PageSlice<BookVO> slice = extractPageSlice(bookMapper.selectByTypeIntegrally(type, filter, sorter, page));
        List<BookVO> list = slice.getList();
        list.forEach(this::mutate);
        return slice;
    }

    @Override
    public void deleteByIdLogically(String id) {
        Book book = findById(id);
        if (Objects.nonNull(book)) {
            // 同时删除响应的内容
            contentService.deleteByIdLogically(book.getContentId());
            super.deleteByIdLogically(id);
        }
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
        return mutate(bookMapper.selectByContentIdIntegrally(contentId));
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

    @Override
    public String findMetadataIdByBookId(String bookId) {
        return bookMapper.selectMetadataIdByBookId(bookId);
    }

    @Override
    public void incrementEpisodes(String bookId) {
        bookMapper.incrementEpisodes(bookId);
    }

    @Override
    public void decrementEpisodes(String bookId) {
        bookMapper.incrementEpisodes(bookId);
    }

    @Override
    public String findContentIdByBookId(String bookId) {
        return bookMapper.selectContentIdByBookId(bookId);
    }
}
