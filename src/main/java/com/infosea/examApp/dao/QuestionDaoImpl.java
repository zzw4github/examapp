package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Question> find(int pageNo, int pageSize, String hql) {
        List<Question> questions = null;
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            questions = query.list();
        } catch (RuntimeException re) {
            throw re;
        }

        return questions;
    }
    @Transactional
    @Override
    public List<Question> findQuestion( int pageSize, int curPage,Map<String,String> map) {
        StringBuffer sb  =new StringBuffer("select question from Question question where");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            sb.append(":").append(key.toString()).append("? and ");
        }
        String hql = sb.toString();
        hql = hql.substring(0,hql.length()-4);
        List<Question> questions = null;
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
             it = map.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                query.setParameter(key.toString(), map.get(key));
            }
            query.setFirstResult((curPage - 1) * pageSize);
            query.setMaxResults(pageSize);
            questions = query.list();
        } catch (RuntimeException re) {
            throw re;
        }

        return questions;
    }

    @Transactional
    @Override
    public long getCounts() {
        return (long)this.sessionFactory.getCurrentSession().createSQLQuery("selct count(id) from question ").uniqueResult();
    }

    @Transactional
    @Override
    public List<Question> find( int pageCount, int curPage,Map<String,String> map) {
        List<Question> questions =  findQuestion(pageCount,curPage,map);
        return questions;
    }
}
