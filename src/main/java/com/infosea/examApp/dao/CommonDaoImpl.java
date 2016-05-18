package com.infosea.examApp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
@Repository
public class CommonDaoImpl implements  CommonDao{

    @Autowired
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    
    public void add(Object o) {

    }

    @Override
    
    public Object selectById(Class c, Serializable s) {
        return this.sessionFactory.getCurrentSession().byId(c).load(s);
    }

    @Override
    
    public void update(Object o) {

    }

    @Override
    
    public void delete(Object o) {

    }

    @Override
    
    public void delete(Class c, Serializable serializable) {

    }

    @Override
    
    public List selectAll(Class c) {

       return null;
    }

    @Override
    
    public List selectAll(String hql) {

          return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override

    public List pageQuery(String hql, Integer page, Integer size, Object[] objects) {
        return null;
    }

    @Override

    public boolean executeUpdate(String hql, Object[] objects) {
        return false;
    }

    @Override
    public int findMaxId(String sql) {
        return (int )this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
    }
}
