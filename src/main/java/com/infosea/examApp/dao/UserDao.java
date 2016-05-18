package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    List<User> findAllUser(int pageCount, int curPage, Map<String, String> map);

    User findUserByTmh(String tmh);

    void delUser(User user);

    long getCounts();
}
