package com.infosea.examApp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by infosea on 2016/5/24.
 */
@Repository
public class CommonDaoImpl implements CommonDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    @Override
    public List<Integer> getIds(String sql) {
        return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }
    @Transactional
    @Override
    public int getCount(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).list().size();
    }
}
