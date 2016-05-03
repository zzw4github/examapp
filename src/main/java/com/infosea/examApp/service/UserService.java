package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/4/19.
 */
@Service
public interface UserService {
        public boolean login (User user);
        public long save(User user);
        public void saveOrUpdate(User user);
        public void merge(User user);
        public User find(long id);
        public User  findUser(User user);


}
