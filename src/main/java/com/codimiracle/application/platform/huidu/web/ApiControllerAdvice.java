package com.codimiracle.application.platform.huidu.web;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.ServiceException;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@Slf4j
@RestControllerAdvice(basePackages = "com.codimiracle.application.platform.huidu.web.api")
public class ApiControllerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ApiResponse handleServiceException(ServiceException e) {
        log.error("service layer throws exception:", e);
        return RestfulUtil.fail(e.getMessage());
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ApiResponse handleDatabaseAccessException(Exception e) {
        log.error("database layer throws exception:", e);
        return RestfulUtil.fail("数据访问失败！");
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ApiResponse handleDatabaseIntegrityException(Exception e) {
        log.error("database layer throws exception:", e);
        return RestfulUtil.fail("数据约束失败！");
    }

    @ExceptionHandler({SQLSyntaxErrorException.class})
    public ApiResponse handleDatabaseSQLSyntaxException(Exception e) {
        log.error("database layer throws exception:", e);
        return RestfulUtil.fail("预设数据语句方式调用失败！");
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse handleArgumentTypMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("argument type mismatch:", e);
        return RestfulUtil.fail("参数无效！");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse handleRequestBobyMissingException(HttpMessageNotReadableException e) {
        log.error("missing essential data:", e);
        return RestfulUtil.fail("缺少参数！");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(Exception e) {
        log.error("arguments invalid:", e);
        return RestfulUtil.fail("参数无效！");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse handleUsernameNotFound(Exception e) {
        log.error("user service:", e);
        return RestfulUtil.fail("用户名或密码不正确！");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse handleAuthenticationException(Exception e) {
        log.error("security: ", e);
        return RestfulUtil.fail("权限访问失败！");
    }
}
