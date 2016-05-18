package com.infosea.examApp.dao;

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
 * Created by infosea on 2016/4/19.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    PageUtil pageUtil;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User queryUserByTmhAndPwd(String tmh, String pwd) {
        return (User) this.sessionFactory.getCurrentSession()
                .createSQLQuery("select * from user where tmh=:tmh and pwd=:pwd")
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
                .createQuery("from com.infosea.examApp.pojo.User user where user.name=? and user.pwd=?")
                .setParameter(0, user.getName())
                .setParameter(1, user.getPwd()).uniqueResult();
    }

    @Override
    @Transactional
    public User findUserByTmh(String tmh) {
        return (User) this.sessionFactory.getCurrentSession().createQuery("from User user where user.tmh=:tmh")
                .setParameter("tmh", tmh).uniqueResult();
    }

    @Override
    @Transactional
    public void delUser(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
    }

    public List<User> findUserByHQL(String hql){
        return  this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<User> findAllUser( int pageSize, int curPage,Map<String,String> map) {
        StringBuffer sb  =new StringBuffer("select user from User user where");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
           sb.append(":").append(key.toString()).append("? and ");
        }
        String hql = sb.toString();
        hql = hql.substring(0,hql.length()-4);
        List<User> users = pageUtil.findPageByQuery(pageSize,curPage,hql,map);

        return users;
    }

    @Override
    public long getCounts() {
        return (long)this.sessionFactory.getCurrentSession().createSQLQuery("selct count(id) from user ").uniqueResult();
    }
}

