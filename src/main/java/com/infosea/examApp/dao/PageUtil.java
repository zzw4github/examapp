package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/20.
 */
@Repository
public class PageUtil<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

@Transactional
    public List<T> findPageByQuery( int pageSize,int pageNo, String hql, Map<Object,Object> map) {
        List<T> result = null;
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                query.setParameter(key.toString(), map.get(key));
            }
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            result = query.list();
        } catch (RuntimeException re) {
            throw re;
        }
        return result;
    }

    public List<T> findPageByHql(int pageNo, int pageSize, String hql) {
        List<T> result = null;
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            result = query.list();
        } catch (RuntimeException re) {
            throw re;
        }
        return result;
    }


    public List<T> find1ByQuery(int pageNo, String hql, Map map) {
        return findPageByQuery(pageNo, 1, hql, map);
    }
}
