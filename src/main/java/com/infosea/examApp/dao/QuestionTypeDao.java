package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.QuestionType;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionTypeDao {
    public QuestionType find(long id);
    public Serializable save(QuestionType questionType);
}
