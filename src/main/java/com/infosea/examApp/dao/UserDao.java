package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/19.
 */
@Repository
public interface UserDao {
    public User queryUserByNameAndPwd(String name,String pwd );
    public Serializable save(User user);
    public void saveOrUpdate(User user);
    public void merge(User user);
    public User find(long id);
    public User  findUser(User user);
}
