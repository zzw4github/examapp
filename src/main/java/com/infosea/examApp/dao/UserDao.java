package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by infosea on 2016/4/19.
 */
@Repository
public interface UserDao {
    User queryUserByTmhAndPwd(String tmh, String pwd);

    Serializable save(User user);

    void saveOrUpdate(User user);

    void merge(User user);

    User find(long id);

    User findUser(User user);

    User findUserByTmh(String tmh);

    void delUser(User user);

    long getCounts();

    List<User> findUser(int pageCount, int curPage, Map<Object, Object> map);

    PageBean<User> find(int pageCount, int curPage, Map<Object, Object> map);
}
