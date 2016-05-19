package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionDao {
    Serializable save(Question querstion);

    Question find(long id);

    List<Question> findAll();

    List<Question> findByHQL(String hql);

    void update(Question question);

    void delete(Question question);

    PageBean<Question> find(int pageNo, int pageSize,String hql);

    long getCounts();
    List<Question> findQuestion(int pageCount, int curPage, Map<String, String> map);
    PageBean<Question> find( int pageCount, int curPage,Map<String,String> map) ;
}
