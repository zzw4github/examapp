package com.infosea.examApp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
public interface BaseDao<T,ID extends Serializable> {
        public void add(T t);

        /**
         * 按主键查找;
         *
         */
        public Object selectById(Class c,Serializable s);


        /**
         * 根据对象修改;
         *
         */
        public void updateObj(Object obj);
        /**
         * 根据对象删除;
         * @param obj
         */
        public void delete(Object obj);

        /**
         * 根据主键删除;
         * @param c
         * @param s
         */
        public void delete(Class c,Serializable s);

        /**
         * 查找全部;
         * @return
         */
        public List selectAll(Class c);

        /**
         * 分页查找;
         * @param hql
         * @param page
         * @param size
         * @param objects
         * @return
         */
        public List pageQuery(String hql, Integer page, Integer size, Object...objects);


        /**
         * 按条件删除,修改;
         * @return
         */
        public boolean executeUpdate(String hql,Object...objects);
}
