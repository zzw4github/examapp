package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Option;
import com.infosea.examApp.util.PageResults;
import com.infosea.examApp.util.RowMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * Created by infosea on 2016/5/19.
 */
public class OptionDaoImpl2 extends BaseDaoImpl<Option,Long> {
    public OptionDaoImpl2() {
        super();
    }

    @Override
    protected Class getEntityClass() {
        return super.getEntityClass();
    }

    @Override
    public void save(Option option) {
        super.save(option);
    }

    @Override
    public void saveOrUpdate(Option option) {
        super.saveOrUpdate(option);
    }

    @Override
    public Option load(Long id) {
        return super.load(id);
    }

    @Override
    public Option get(Long id) {
        return super.get(id);
    }

    @Override
    public boolean contains(Option option) {
        return super.contains(option);
    }

    @Override
    public void delete(Option option) {
        super.delete(option);
    }

    @Override
    public boolean deleteById(Long Id) {
        return super.deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<Option> entities) {
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
    public Option getByHQL(String hqlString, Object... values) {
        return super.getByHQL(hqlString, values);
    }

    @Override
    public Option getBySQL(String sqlString, Object... values) {
        return super.getBySQL(sqlString, values);
    }

    @Override
    public List<Option> getListByHQL(String hqlString, Object... values) {
        return super.getListByHQL(hqlString, values);
    }

    @Override
    public List<Option> getListBySQL(String sqlString, Object... values) {
        return super.getListBySQL(sqlString, values);
    }

    @Override
    public List findListBySql(String sql, RowMapper map, Object... values) {
        return super.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(Option option) {
        super.refresh(option);
    }

    @Override
    public void update(Option option) {
        super.update(option);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return super.countByHql(hql, values);
    }

    @Override
    public PageResults<Option> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
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
