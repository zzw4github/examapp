package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.QuestionType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/20.
 */
@Repository
public class QuestionTypeDaoImpl implements QuestionTypeDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    @Transactional
    public QuestionType find(long id) {
        return this.sessionFactory.getCurrentSession().byId(QuestionType.class).load(id);
    }

    @Override
    @Transactional
    public Serializable save(QuestionType questionType) {
        return this.sessionFactory.getCurrentSession().save(questionType);
    }
}
