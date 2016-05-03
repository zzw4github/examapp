package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Question;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionService {
    public long save(Question question);
    public Question find(long id);
    public List<Question> findAll();
    public List<Question> findByHQL(String hql);
    public void update(Question question);
}
