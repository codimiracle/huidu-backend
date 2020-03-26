package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.entity.vo.BookNotesVO;
import com.codimiracle.application.platform.huidu.entity.vt.BookNoteCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookNotesMapper extends Mapper<BookNotes> {
    List<BookNotesVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    BookNotesVO selectByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> id);

    List<BookNoteCollection> selectAllBookNotesCollectionByUserId(@Param("userId") String userId);
    BookNoteCollection selectBookNotesCollectionByBookIdAndUserId(@Param("bookId") String bookId, @Param("userId") String userId);

    Boolean existsWithMainDommarkPath(@Param("mainStartPath") String mainStartPath, @Param("mainEndPath") String mainEndPath);

    List<BookNoteCollection> selectAllCollectionIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}