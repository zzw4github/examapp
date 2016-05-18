package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Option;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Repository
public class OptionDaoImpl implements  OptionDao{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override

    public Option find(long id) {
        return this.sessionFactory.getCurrentSession().byId(Option.class).load(id);
    }

    @Override

    public Serializable save(Option option) {
        return this.sessionFactory.getCurrentSession().save(option);
    }

    @Override
    public List<Option> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery(" from Option").list();
    }
}
