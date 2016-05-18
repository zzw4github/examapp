package com.infosea.examApp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
public interface CommonDao<T extends Object ,ID extends Serializable> {
    int findMaxId(String sql);
    void add(T t);

    /**
     * 按主键查找;
     */
    Object selectById(Class c, Serializable s);


    /**
     * 根据对象修改;
     */
    void update(T t);
    /**
     * 根据对象删除;
     * @param t
     */
    void delete(T t);

    /**
     * 根据主键删除;
     * @param c
     * @param id
     */
    void delete(Class c, ID id);

    /**
     * 查找全部;
     * @return
     */
    List selectAll(Class c);
    /**
     * 查找全部;
     * @return
     */
    List selectAll(String hql);

    /**
     * 分页查找;
     * @param hql
     * @param page
     * @param size
     * @param ts
     * @return
     */
    List pageQuery(String hql, Integer page, Integer size, T... ts);


    /**
     * 按条件删除,修改;
     * @return
     */
    boolean executeUpdate(String hql, T... ts);
}
