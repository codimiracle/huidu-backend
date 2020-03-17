package com.codimiracle.application.platform.huidu.contract;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 分页器
 *
 * @author Codimiracle
 */
@Slf4j
@Aspect
@Component
public class Paginator {
    @Pointcut(value = "execution(public * com.codimiracle.application.platform.huidu.mapper.*Mapper.*(.., Page)) && args(.., page)")
    public void paginatableMethods(Page page) {
    }

    @Around(value = "paginatableMethods(page)", argNames = "proceedingJoinPoint,page")
    public Object pagination(ProceedingJoinPoint proceedingJoinPoint, Page page) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        try {
            List list = (List) proceedingJoinPoint.proceed();
            PageInfo info = new PageInfo(list);
            PageSlice slice = new PageSlice();
            slice.setList(list);
            slice.setPage(info.getPageNum());
            slice.setLimit(info.getPageSize());
            slice.setTotal(info.getTotal());
            // 利用类型擦除，把 PageSlice 放进列表中返回
            return Collections.singletonList(slice);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("pagination failed:", throwable);
            throw new ServiceException("无法进行分页，原因：" + throwable.getMessage());
        } finally {
            PageHelper.clearPage();
        }
    }
}
