package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.util.PageResults;
import com.infosea.examApp.util.RowMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * Created by infosea on 2016/5/19.
 */
public class ExamDaoImpl1 extends BaseDaoImpl<Exam,Long> {

    public ExamDaoImpl1() {
        super();
    }

    @Override
    protected Class getEntityClass() {
        return super.getEntityClass();
    }

    @Override
    public void save(Exam exam) {
        super.save(exam);
    }

    @Override
    public void saveOrUpdate(Exam exam) {
        super.saveOrUpdate(exam);
    }

    @Override
    public Exam load(Long aLong) {
        return super.load(aLong);
    }

    @Override
    public Exam get(Long aLong) {
        return super.get(aLong);
    }

    @Override
    public boolean contains(Exam exam) {
        return super.contains(exam);
    }

    @Override
    public void delete(Exam exam) {
        super.delete(exam);
    }

    @Override
    public boolean deleteById(Long Id) {
        return super.deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<Exam> entities) {
        super.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        super.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        super.querySql(sqlString, values);
    }

    @Override
    public Exam getByHQL(String hqlString, Object... values) {
        return super.getByHQL(hqlString, values);
    }

    @Override
    public Exam getBySQL(String sqlString, Object... values) {
        return super.getBySQL(sqlString, values);
    }

    @Override
    public List<Exam> getListByHQL(String hqlString, Object... values) {
        return super.getListByHQL(hqlString, values);
    }

    @Override
    public List<Exam> getListBySQL(String sqlString, Object... values) {
        return super.getListBySQL(sqlString, values);
    }

    @Override
    public List findListBySql(String sql, RowMapper map, Object... values) {
        return super.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(Exam exam) {
        super.refresh(exam);
    }

    @Override
    public void update(Exam exam) {
        super.update(exam);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return super.countByHql(hql, values);
    }

    @Override
    public PageResults<Exam> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return super.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }

    @Override
    public SessionFactory getSessionFactory() {
        return super.getSessionFactory();
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Session getSession() {
        return super.getSession();
    }
}
