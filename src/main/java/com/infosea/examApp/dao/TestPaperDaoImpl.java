package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.TestPaper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
@Repository
public class TestPaperDaoImpl implements  TestPaperDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    @Transactional
    public TestPaper findTestPaperById(long id) {
        return this.sessionFactory.getCurrentSession().get(TestPaper.class,id);
    }

    @Override

    public List<TestPaper> findAllTestPaper() {
        return this.sessionFactory.getCurrentSession().createQuery("from com.infosea.examApp.pojo.TestPaper paper").list();
    }

    @Override

    public Serializable save(TestPaper testPaper) {
        return this.sessionFactory.getCurrentSession().save(testPaper);
    }

    @Override
    public TestPaper findTestPaperByTestPaperIdandUserId(long pid, long uid) {
        return (TestPaper) this.sessionFactory.getCurrentSession().createQuery("select e from com.infosea.examApp.pojo. TestPaper p  left join fetch p.user where p.id=:eid and p.user.id=:uid").setParameter("id",pid).setParameter("uid",uid).uniqueResult();
    }

    @Override

    public void del(TestPaper testPaper) {
        this.sessionFactory.getCurrentSession().delete(testPaper);

    }

    @Override

    public void update(TestPaper testPaper) {
        this.sessionFactory.getCurrentSession().update(testPaper);

    }
    @Override

    public void flush() {
        this.sessionFactory.getCurrentSession().flush();
    }
}
