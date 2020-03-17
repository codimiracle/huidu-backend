package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.entity.dto.BookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BookMetadataDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ElectronicBookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.PaperBookDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.web.api.backend.ApiBackendElectronicBookController;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import com.codimiracle.application.platform.huidu.web.api.creator.ApiCreatorElectronicBookController;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/*
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
@SpringBootTest
class BookControllerTest {

    @Resource
    private BookController bookController;

    @Test
    void create() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setType(BookType.ElectronicBook.getType());
        bookDTO.setCategoryId("1");
        bookDTO.setCommodityId("1");
        bookDTO.setCover("http://example.com/example.png");
        bookDTO.setDescription("Hello this is a book");
        BookMetadataDTO metadataDTO = new BookMetadataDTO();
        metadataDTO.setAuthor("作者");
        metadataDTO.setCover("封面");
        metadataDTO.setName("书名");
        metadataDTO.setDescription("描述");
        bookDTO.setMetadata(metadataDTO);
        bookDTO.setPublishYear("2010");
        bookDTO.setStatus(BookStatus.Examining.getStatus());
        bookDTO.setTags(new String[] {"测试"});
        bookDTO.setTeller("测试者");
        bookDTO.setTitle("测试书籍");
        User user = new User();
        user.setId("1");
        bookController.create(user, bookDTO);
    }

    @Test
    void update() {

    }

}