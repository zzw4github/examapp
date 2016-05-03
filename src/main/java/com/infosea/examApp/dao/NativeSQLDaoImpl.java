package com.infosea.examApp.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

/**
 * Created by infosea on 2016/4/28.
 */
@Repository
public class NativeSQLDaoImpl implements NativeSQLDao {
    @Autowired
   private  SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    @Transactional
    public List<Integer> getIds(String sql) {
        return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }

    @Override
    @Transactional
    public int getMaxId(String sql) {
        return  (Integer)this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
    }

    @Override
    @Transactional
    public Query createQuery(String sql) {
        return  this.sessionFactory.getCurrentSession().createQuery(sql);
    }
}
