package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Question;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionService {
    long save(Question question);
    Question find(long id);
    List<Question> findAll();
    List<Question> findByHQL(String hql);
    void update(Question question);
}
