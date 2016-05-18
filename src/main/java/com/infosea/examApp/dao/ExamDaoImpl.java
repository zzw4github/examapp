package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 * Exam查询DAO
 *
 */
@Repository
public class ExamDaoImpl implements ExamDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据id查询出对应的Exam信息
     * @param id
     * @return
     */
    @Transactional
    public Exam findByID(long id) {
        return this.sessionFactory.getCurrentSession().byId(Exam.class).load(id);
    }

    /**
     * 保存Exam信息
     * @param exam
     * @return
     */
    @Override
    @Transactional
    public Serializable save(Exam exam) {
        return this.sessionFactory.getCurrentSession().save(exam);
    }

    @Override

    public Exam findByEidAndUid(long eid, long uid) {
        return (Exam) this.sessionFactory.getCurrentSession().createQuery("select e from com.infosea.examApp.pojo. Exam e  left join fetch e.user where e.id=:eid and e.user.id=:uid").setParameter("eid", eid).setParameter("uid", uid).uniqueResult();
    }

    @Override
    public List<Exam> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from com.infosea.examApp.pojo.Exam e").list();
    }

    /**
     * 删除Exam信息
     * @param exam
     */
    @Override
    public void del(Exam exam) {
        this.sessionFactory.getCurrentSession().delete(exam);
    }

    /**
     * 更新Exam信息
     * @param exam
     */
    @Override
    public void update(Exam exam) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(exam);
    }

    /**
     * 根据用户id查询Exam信息
     * @param uid
     * @return
     */
    @Override
    public List<Exam> findByUserId(long uid) {
        return this.sessionFactory.getCurrentSession().createQuery("select e from com.infosea.examApp.pojo.Exam e left join fetch e.user where e.user.id=:uid").setParameter("uid", uid).list();
    }

    /**
     * 查询所有Exam
     * @param hql
     * @return
     */
    @Override

    public List<Exam> findAll(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public void delByEid(long eid) {
         this.sessionFactory.getCurrentSession().createQuery("delete  com.infosea.examApp.pojo.Exam e where e.id=:id").setParameter("id",eid).executeUpdate();
    }

    @Override
    public void delByUid(long uid) {
        this.sessionFactory.getCurrentSession().createQuery("delete  com.infosea.examApp.pojo.Exam e where e.user.id=:uid").setParameter("uid",uid).executeUpdate();
    }

    @Override
    public void delByEidUid(long eid, long uid) {
        this.sessionFactory.getCurrentSession().createQuery("delete  com.infosea.examApp.pojo.Exam e where e.id=:id and e.user.id=:uid").setParameter("id",eid).setParameter("uid",uid).executeUpdate();
    }
}
