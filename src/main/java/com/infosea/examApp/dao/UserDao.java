package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/19.
 */
@Repository
public interface UserDao {
    User queryUserByNameAndPwd(String name, String pwd);
    Serializable save(User user);
    void saveOrUpdate(User user);
    void merge(User user);
    User find(long id);
    User  findUser(User user);
}
