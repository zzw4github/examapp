package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Type;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by infosea on 2016/5/19.
 */
@Repository
public class TypeDaoImpl implements TypeDao{
    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Type findById(long id) {
        return this.sessionFactory.getCurrentSession().byId(Type.class).load(id);
    }

    @Override
    public void delete(Type type) {
         this.sessionFactory.getCurrentSession().delete(type);
    }

    @Override
    public long save(Type type) {
        return (Long)this.sessionFactory.getCurrentSession().save(type);
    }
}
