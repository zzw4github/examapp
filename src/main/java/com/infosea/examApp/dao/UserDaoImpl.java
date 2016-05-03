package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by infosea on 2016/4/19.
 */
@Repository
public class UserDaoImpl implements  UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public User queryUserByNameAndPwd(String name,String pwd ) {
        return (User)this.sessionFactory.getCurrentSession()
                .createQuery("from com.infosea.examApp.pojo.User user where user.name=? and user.pwd=?")
                .setParameter(0,name)
                .setParameter(1,pwd).uniqueResult();
//        return (User)this.sessionFactory.getCurrentSession()
//                .createSQLQuery("select * from user where name=:name and pwd=:pwd")
//                .setParameter("name",name)
//                .setParameter("pwd",pwd).uniqueResult();

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
       return  this.sessionFactory.getCurrentSession().byId(User.class).load(id);
    }
    @Transactional
    @Override
    public User findUser(User user) {
      return   (User)this.sessionFactory.getCurrentSession()
                .createQuery("from com.infosea.examApp.pojo.User user where user.name=? and user.pwd=?")
                .setParameter(0,user.getName())
                .setParameter(1,user.getPwd()).uniqueResult();
    }
}

