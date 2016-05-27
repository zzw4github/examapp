package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Answer;

/**
 * Created by infosea on 2016/5/19.
 */
public interface AnswerDao {
    long save(Answer answer);
    Answer query(long userId,long testPaperId,long questionId);
}
