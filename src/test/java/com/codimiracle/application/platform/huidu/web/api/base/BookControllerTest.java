package com.codimiracle.application.platform.huidu.web.api.base;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.AudioBookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ElectronicBookDTO;
import com.codimiracle.application.platform.huidu.entity.dto.PaperBookDTO;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

class BookControllerTest {

    @Autowired
    BookController bookController;

    @Autowired
    WebApplicationContext context;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Test
    void testRequest() throws Exception {
        MockMvc mockMvc = webAppContextSetup(context).build();
        //发出请求后应返回 JSON 结果
        mockMvc.perform(post("/api/system/sign-in"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createElectronicBook() {
        User user = userService.findById("2");
        // 电子书的 Null 测试
        ElectronicBookDTO electronicBookDTO = new ElectronicBookDTO();
        ApiResponse apiResponse = bookController.create(user, electronicBookDTO.toBookDTO());
        //假设插入成功
        assertEquals(apiResponse.getCode().intValue(), 200);
        //返回结果不应为空
        assertNotNull(apiResponse.getData());
        //检查数据
        BookVO bookVO = (BookVO) apiResponse.getData();
        Book book = bookService.findById(bookVO.getId());
        assertNotNull(book);
        assertEquals(book.getType(), BookType.ElectronicBook);
        //图书对应内容ID不应为空
        assertNotNull(book.getContentId());
    }

    @Test
    void createAudioBook() {
        User user = userService.findById("2");
        // 有声书的 Null 测试
        AudioBookDTO audioBookDTO = new AudioBookDTO();
        ApiResponse apiResponse = bookController.create(user, audioBookDTO.toBookDTO());
        //假设插入成功
        assertEquals(apiResponse.getCode().intValue(), 200);
        //返回结果不应为空
        assertNotNull(apiResponse.getData());
        //检查数据
        BookVO bookVO = (BookVO) apiResponse.getData();
        Book book = bookService.findById(bookVO.getId());
        assertNotNull(book);
        assertEquals(book.getType(), BookType.AudioBook);
        //图书对应内容ID不应为空
        assertNotNull(book.getContentId());
    }

    @Test
    void createPaperBook() {
        User user = userService.findById("2");
        // 纸质书的 Null 测试
        PaperBookDTO paperBookDTO = new PaperBookDTO();
        ApiResponse apiResponse = bookController.create(user, paperBookDTO.toBookDTO());
        //假设插入成功
        assertEquals(apiResponse.getCode().intValue(), 200);
        //返回结果不应为空
        assertNotNull(apiResponse.getData());
        //检查数据
        BookVO bookVO = (BookVO) apiResponse.getData();
        Book book = bookService.findById(bookVO.getId());
        assertNotNull(book);
        assertEquals(book.getType(), BookType.PaperBook);
        //图书对应内容ID不应为空
        assertNotNull(book.getContentId());
    }
}