package com.infosea.examApp.service;

import com.infosea.examApp.dao.UserDao;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao ;

    /**
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
     User usr =userDao.queryUserByTmhAndPwd(user.getTmh(),user.getPwd());
        return usr;
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

    @Override
    public User findUserByTmh(String tmh) {
        return userDao.findUserByTmh(tmh);
    }

    @Override
    public boolean deleteUser(String tmh) {
        User user = userDao.findUserByTmh(tmh);
        if(user != null) {
            return false;
        }else {
            userDao.delUser(user);
            return  true;
        }
    }

    @Override
    public PageBean<User> find(int pageCount, int curPage, Map<Object, Object> map) {
        PageBean<User> users = userDao.find(pageCount, curPage, map);
        return users;
    }
}
