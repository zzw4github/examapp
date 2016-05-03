package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionDao {
    public Serializable save(Question querstion);
    public Question find(long id);
    public List<Question> findAll();
    public List<Question> findByHQL(String hql);
    public void update(Question question);
}
