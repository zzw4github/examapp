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
 */
@Repository
public class ExamDaoImpl implements ExamDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public  Exam findExam(long id){
        return this.sessionFactory.getCurrentSession().byId(Exam.class).load(id);
    }

    @Override
    @Transactional
    public Serializable save(Exam exam) {
        return  this.sessionFactory.getCurrentSession().save(exam);
    }

    @Override
    @Transactional
    public Exam findExamByExamIdandUserId(long eid, long uid) {
        return (Exam)this.sessionFactory.getCurrentSession().createQuery("select e from com.infosea.examApp.pojo. Exam e  left join fetch e.user where e.id=:eid and e.user.id=:uid").setParameter("eid",eid).setParameter("uid",uid).uniqueResult();
    }
    @Transactional
    @Override
    public List<Exam> findAllExam() {
      return   this.sessionFactory.getCurrentSession().createQuery("from com.infosea.examApp.pojo.Exam e").list();
    }

    @Override
    public void del(Exam exam) {
        this.sessionFactory.getCurrentSession().delete(exam);
    }

    @Override
    public void update(Exam exam) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(exam);
    }
}
