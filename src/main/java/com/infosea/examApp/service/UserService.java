package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/4/19.
 */
@Service
public interface UserService {
        boolean login(User user);
        long save(User user);
        void saveOrUpdate(User user);
        void merge(User user);
        User find(long id);
        User  findUser(User user);


}
