package com.codimiracle.application.platform.huidu.contract;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
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
    public Object pagination(ProceedingJoinPoint proceedingJoinPoint, Page page) throws NoSuchMethodException {
        String methodName = proceedingJoinPoint.getSignature().getName();
        PageSlice slice = new PageSlice();

        try {
            if (methodName.endsWith("Integrally")) {
                //假定数据库表有 deleted 字段。
                //获取实体的类
                Class<?> mapperInterface = proceedingJoinPoint.getSignature().getDeclaringType();
                ParameterizedType parameterizedType = (ParameterizedType) mapperInterface.getGenericInterfaces()[0];
                Class<?> entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                long total = PageHelper.count(() -> {
                    try {
                        String validCountMapperMethodName = methodName.replaceAll("Integrally", "");
                        Method method = null;
                        if (ReflectionUtils.findField(entityClass, "deleted") != null) {
                            validCountMapperMethodName = "selectByCondition";
                            Condition condition = new Condition(entityClass);
                            Example.Criteria criteria = condition.createCriteria();
                            criteria.andEqualTo("deleted", 0);
                            method = ReflectionUtils.findMethod(mapperInterface, validCountMapperMethodName, Object.class);
                            method.invoke(proceedingJoinPoint.getTarget(), condition);
                        } else {
                            log.debug("deleted field not found in [$s].", entityClass);
                            method = ReflectionUtils.findMethod(mapperInterface, validCountMapperMethodName);
                            method.invoke(proceedingJoinPoint.getTarget());
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                List list = (List) proceedingJoinPoint.proceed();
                slice.setTotal(total);
                slice.setPage(page.getPage());
                slice.setLimit(page.getLimit());
                slice.setList(list);
            } else {
                PageHelper.startPage(page.getPage(), page.getLimit());
                List list = (List) proceedingJoinPoint.proceed();
                PageInfo info = new PageInfo(list);
                slice.setList(list);
                slice.setPage(info.getPages());
                slice.setLimit(info.getPageSize());
                slice.setTotal(info.getTotal());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("pagination failed:", throwable);
            throw new ServiceException("无法进行分页，原因：" + throwable.getMessage());
        }
        // 利用类型擦除，把 PageSlice 放进列表中返回
        return Collections.singletonList(slice);
    }
}
