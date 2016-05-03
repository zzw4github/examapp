package com.infosea.examApp.service;

import com.infosea.examApp.dao.UserDao;
import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by infosea on 2016/4/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao ;
    @Override

    public boolean login(User user) {
     User usr =userDao.queryUserByNameAndPwd(user.getName(),user.getPwd());
        return usr!=null;
    }

    @Override

    public long save(User user) {
        return (long)userDao.save(user);
    }

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public void merge(User user) {
        userDao.merge(user);
    }

    @Override
    public User find(long id) {
        return userDao.find(id);
    }

    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }
}
