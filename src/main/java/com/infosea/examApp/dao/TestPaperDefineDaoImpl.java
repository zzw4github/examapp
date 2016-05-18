package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.TestPaperDefine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/17.
 */
@Repository
public class TestPaperDefineDaoImpl implements TestPaperDefineDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public TestPaperDefine findTestPaperDefineById(long id) {
        return this.sessionFactory.getCurrentSession().get(TestPaperDefine.class,id);
    }

    @Override
    public List<TestPaperDefine> findAllTestPaperDefine() {
        return  this.sessionFactory.getCurrentSession().createQuery(" from com.infosea.examApp.pojo.TestPaperDefine ").list();
    }
    @Transactional
    @Override
    public Serializable save(TestPaperDefine testPaperDefine) {
        return this.sessionFactory.getCurrentSession().save(testPaperDefine);
    }

    @Override
    public TestPaperDefine findTestPaperByTestPaperDefineIdAndUserId(long eid, long uid) {
        return null;
    }

    @Override
    public void del(TestPaperDefine testPaperDefine) {

    }

    @Override
    public void update(TestPaperDefine testPaperDefine) {

    }

    @Override
    public void flush() {

    }
}
