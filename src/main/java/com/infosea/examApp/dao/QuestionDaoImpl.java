package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    @Transactional
    public Serializable save(Question querstion) {
        return this.sessionFactory.getCurrentSession().save(querstion);
    }

    @Override
    @Transactional
    public Question find(long id) {
        return this.sessionFactory.getCurrentSession().byId(Question.class).load(id);
    }

    @Override
    @Transactional
    public List<Question> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery(" from Question").list();
    }
    @Override
    @Transactional
    public List<Question> findByHQL(String hql){
        return  this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    @Transactional
    @Override
    public void update(Question question) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(question);
    }
}
