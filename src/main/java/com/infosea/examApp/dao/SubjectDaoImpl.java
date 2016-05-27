package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Subject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by infosea on 2016/5/25.
 */
@Repository
public class SubjectDaoImpl implements SubjectDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public long save(Subject subject) {
        return (Long)this.sessionFactory.getCurrentSession().save(subject);
    }
}
