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


    /**
     * 删除对象
     * @param querstion
     * @return
     */
    @Override
    public Serializable save(Question querstion) {
        return this.sessionFactory.getCurrentSession().save(querstion);
    }

    /**
     * 查找对象
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Question find(long id) {
        return this.sessionFactory.getCurrentSession().byId(Question.class).load(id);
    }

    /**
     * 查找所有问题
     * @return
     */
    @Override

    public List<Question> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery(" from com.infosea.examApp.pojo.Question").list();
    }

    /**
     * 通过hql查找
     * @param hql
     * @return
     */
    @Override
    public List<Question> findByHQL(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    /**
     * 修改问题
     * @param question
     */
    @Override
    public void update(Question question) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(question);
    }

    /**
     * 删除问题
     * @param question
     */
    @Override
    public void delete(Question question) {
        question  = find(question.getId());
        this.sessionFactory.getCurrentSession().delete(question);
    }
}
