package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.QuestionType;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionTypeDao {
    QuestionType find(long id);
    Serializable save(QuestionType questionType);
}
