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
import java.util.Set;

/**
 * Created by infosea on 2016/4/19.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User queryUserByTmhAndPwd(String tmh, String pwd) {
        return  (User)this.sessionFactory.getCurrentSession()
                .createQuery("select user from User user where user.tmh=:tmh and user.pwd=:pwd")
                .setParameter("tmh", tmh)
                .setParameter("pwd", pwd).uniqueResult();

    }

    @Override
    @Transactional
    public Serializable save(User user) {
        return this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void merge(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    @Transactional
    public User find(long id) {
        return this.sessionFactory.getCurrentSession().byId(User.class).load(id);
    }

    @Override
    @Transactional
    public User findUser(User user) {
        return (User) this.sessionFactory.getCurrentSession()
                .createQuery("from com.infosea.examApp.pojo.User user where user.tmh=? and user.pwd=?")
                .setParameter(0, user.getTmh())
                .setParameter(1, user.getPwd()).uniqueResult();
    }

    @Override
    @Transactional
    public User findUserByTmh(String tmh) {
        return (User) this.sessionFactory.getCurrentSession().createQuery("from com.infosea.examApp.pojo. User user where user.tmh=:tmh")
                .setParameter("tmh", tmh).uniqueResult();
    }

    @Override
    @Transactional
    public void delUser(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
    }

    public List<User> findUserByHQL(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<User> find(int curPage, int pageSize , Map<String , Object> map) {
        StringBuffer sb = new StringBuffer("select user from User user where");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            sb.append(" user.").append(key).append("=:").append(key).append(" and ");
        }
        String hql = sb.toString();
        hql = hql.substring(0, hql.length() - 4);
        System.out.println(hql);
        List<User> users = null;
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            it = map.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                Object value = map.get(key);
                System.out.println((String)value);
                query.setParameter(key.toString(), (String)value);
            }
            query.setFirstResult((curPage - 1) * pageSize);
            query.setMaxResults(pageSize);
            users = query.list();
            System.out.println(users);
        } catch (RuntimeException re) {
            throw re;
        }
        return users;
    }

    @Override
    public long getCounts() {
        return (long) this.sessionFactory.getCurrentSession().createSQLQuery("selct count(id) from user ").uniqueResult();
    }

    @Override
    public long getCounts(String sql) {
        return (long) this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
    }

    @Override
    public long getCounts(Map<String, Object> params) {

        long count = 0L;
        StringBuffer sb = new StringBuffer("select count(id) from User where ");
        Iterator<String> iter = params.keySet().iterator();

        while (iter.hasNext()) {
            String key = iter.next();
            Object value = params.get(key);
            sb.append(key).append("=:").append(key).append(" and ");
        }
        String hql = sb.toString();
        hql = hql.substring(0, hql.length() - 4);
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            iter = params.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                query.setParameter(key.toString(), params.get(key));
            }
            count = (Long)query.uniqueResult();
        } catch (RuntimeException re) {
            throw re;
        }
        return count;
    }
}

