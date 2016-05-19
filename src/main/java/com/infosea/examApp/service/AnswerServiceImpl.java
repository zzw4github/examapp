package com.infosea.examApp.service;

import com.infosea.examApp.dao.AnswerDao;
import com.infosea.examApp.pojo.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/5/19.
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerDao answerDao;

    @Override
    public long save(Answer answer) {
        return answerDao.save(answer);
    }
}
