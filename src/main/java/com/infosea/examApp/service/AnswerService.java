package com.infosea.examApp.service;

import com.infosea.examApp.dao.AnswerDao;
import com.infosea.examApp.pojo.Answer;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/5/19.
 */

public interface AnswerService {
    long save(Answer answer);
}
