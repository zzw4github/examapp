package com.infosea.examApp.service;

import com.infosea.examApp.dao.UserDao;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public List<User> findList(int pageCount, int curPage, Map<String, Object> map) {
        List<User> users = userDao.find(pageCount, curPage, map);
        return users;
    }

    @Override
    public boolean deleteById(Long id) {
        User user = this.find(id);
        userDao.delUser(user);
        return true;
    }

    @Override
    public long getCount(Map<String, Object> params) {
        return userDao.getCounts(params);
    }
    @Transactional
    @Override
    public PageBean<User> findPageBean(int pageNo, int pageSize ,Map<String, Object> params) {
        long rows = getCount(params);
        List<User> users = findList(pageNo , pageSize , params);
        PageBean<User> pageBean = new PageBean((int)rows);
        pageBean.setObjects(users);
        pageBean.setRowsCount((int)rows);
        pageBean.setCurPage(pageNo);
        pageBean.setPageSize(pageSize);
        System.out.println(pageBean.getObjects().get(0).getId());
        return pageBean;
    }
}
