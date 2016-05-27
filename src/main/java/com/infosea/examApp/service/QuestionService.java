package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionService {
    long save(Question question);

    Question find(long id);

    List<Question> findAll();

    List<Question> findByHQL(String hql);

    void update(Question question);

    void saveQuestionFromFile(File file);

    List<Question> find(int pageCount, int curPage, Map<String,String> map);

    List<Question> find(int pageCount, int curPage, String qids);
}
