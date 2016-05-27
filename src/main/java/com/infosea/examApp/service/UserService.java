package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/19.
 */
@Service
public interface UserService {
    User login(User user);

    long save(User user);

    void saveOrUpdate(User user);

    void merge(User user);

    User find(long id);

    User findUser(User user);

    User findUserByTmh(String tmh);

    boolean deleteUser(String tmh);

    boolean deleteById(Long id);
    List<User> findList( int pageCount, int curPage,Map<String,Object> map);

    long getCount(Map<String,Object> params);

    PageBean<User> findPageBean(int pageNo, int pageSize ,Map<String,Object> params);

}
