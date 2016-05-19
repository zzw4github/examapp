package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
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
 * Exam查询DAO
 *
 */
@Repository
public class ExamDaoImpl implements ExamDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    PageUtil<Exam> pageUtil;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据id查询出对应的Exam信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Exam findByID(long id) {
        return this.sessionFactory.getCurrentSession().load(Exam.class,id);
//        return this.sessionFactory.getCurrentSession().get(Exam.class,id);
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
    @Transactional
    public Exam findByEidAndUid(long eid, long uid) {
        return (Exam) this.sessionFactory.getCurrentSession().createQuery("select e from com.infosea.examApp.pojo. Exam e  left join fetch e.user where e.id=:eid and e.user.id=:uid").setParameter("eid", eid).setParameter("uid", uid).uniqueResult();
    }

    @Override
    @Transactional
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
    @Transactional
    public void update(Exam exam) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(exam);
    }

    /**
     * 根据用户id查询Exam信息
     * @param uid
     * @return
     */
    @Override
    @Transactional
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

    @Override
    public PageBean<Exam> find(int pageCount, int curPage, Map<String,String> map) {
        List<Exam> exams =  findExam(pageCount,curPage,map);
        long totalCount = getCounts();
        PageBean<Exam> pageBean = new PageBean((int)totalCount);
        pageBean.setObjects(exams);
        return pageBean;
    }
    @Override
    public List<Exam> findExam( int pageSize, int curPage,Map<String,String> map) {
        StringBuffer sb  =new StringBuffer("select exam from Exam exam where ");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            sb.append(key.toString()).append("=:").append(key.toString()).append(" and ");
        }
        String hql = sb.toString();
        hql = hql.substring(0,hql.length()-4);
        List<Exam> exams = pageUtil.findPageByQuery(pageSize,curPage,hql,map);

        return exams;
    }
    @Override
    @Transactional
    public long getCounts() {
        return (long)this.sessionFactory.getCurrentSession().createSQLQuery("selct count(id) from Exam ").uniqueResult();
    }
}
