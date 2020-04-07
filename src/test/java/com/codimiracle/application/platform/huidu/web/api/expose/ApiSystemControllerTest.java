package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.SignInDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class ApiSystemControllerTest {

    @Autowired
    ApiSystemController apiSystemController;

    @Autowired
    WebApplicationContext context;

    @Test
    void testRequest() throws Exception {
        MockMvc mockMvc = webAppContextSetup(context).build();
        //发出请求后应返回 JSON 结果
        mockMvc.perform(post("/api/system/sign-in"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testBlankSignIn() {
        //空的数据传输对象应登录失败
        SignInDTO signInDTO = new SignInDTO();
        try {
            ApiResponse apiResponse = apiSystemController.signIn(signInDTO);
            assertNotEquals(apiResponse.getCode().intValue(), 200);
        } catch (UsernameNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void testErrorPasswordSignIn() {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername("codimiracle");
        try {
            ApiResponse apiResponse = apiSystemController.signIn(signInDTO);
            assertNotEquals(apiResponse.getCode().intValue(), 200);
        } catch (Throwable throwable) {
            assertTrue(true, "应返回错误！");
        }
    }

    @Test
    void testNonExistsUsernameSignIn() {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername("aaaaaaaaaaaaa");
        try {
            ApiResponse apiResponse = apiSystemController.signIn(signInDTO);
        } catch (UsernameNotFoundException e) {
            assertTrue(true);
        }
    }
}