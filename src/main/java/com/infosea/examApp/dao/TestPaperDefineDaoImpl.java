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

    /**
     * 查询试卷
     * @param id
     * @return
     */
    @Transactional
    @Override
    public TestPaperDefine findById(long id) {
        return this.sessionFactory.getCurrentSession().get(TestPaperDefine.class,id);
    }

    /**
     * 查询所有试卷
     * @return
     */
    @Override
    public List<TestPaperDefine> findAll() {
        return  this.sessionFactory.getCurrentSession().createQuery(" from com.infosea.examApp.pojo.TestPaperDefine ").list();
    }

    /**
     * 增加试卷
     * @param testPaperDefine
     * @return
     */
    @Transactional
    @Override
    public Serializable save(TestPaperDefine testPaperDefine) {
        return this.sessionFactory.getCurrentSession().save(testPaperDefine);
    }



    /**
     * 删除试卷
     * @param testPaperDefine
     */
    @Override
    public void del(TestPaperDefine testPaperDefine) {

    }

    @Override
    public void del(long id) {
        TestPaperDefine testPaperDefine = findById(id);
        this.sessionFactory.getCurrentSession().delete(testPaperDefine);
    }

    /**
     * 更新试卷
     * @param testPaperDefine
     */
    @Override
    public void update(TestPaperDefine testPaperDefine) {

    }

    @Override
    public void flush() {

    }
}
