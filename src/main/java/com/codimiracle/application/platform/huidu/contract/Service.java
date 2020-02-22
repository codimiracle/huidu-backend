package com.codimiracle.application.platform.huidu.contract;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<K, T> {
    /**
     * @param entity
     */
    void save(T entity);

    /**
     * @param entities
     */
    void save(List<T> entities);//批量持久化

    /**
     * @param id
     */
    void deleteById(K id);//通过主鍵刪除

    /**
     * @param ids
     */
    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”

    /**
     * @param entity
     */
    void update(T entity);//更新

    /**
     * @param id
     * @return
     */
    T findById(K id);//通过ID查找

    /**
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

    /**
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”

    /**
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);//根据条件查找

    /**
     * @return
     */
    List<T> findAll();//获取所有

    PageSlice<T> findAll(Page page);
}
