package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/20.
 */
@Repository
public interface ExamDao {

    Serializable save(Exam exam);

    Exam findByID(long id);

    List<Exam> findAll();

    Exam findByEidAndUid(long eid, long uid);

    List<Exam> findByUserId(long uid);

    List<Exam> findAll(String hql);

    void del(Exam exam);

    void delByEid(long eid);

    void delByUid(long uid);

    void delByEidUid(long eid,long uid);

    void update(Exam exam);

    PageBean<Exam> find(int pageNo , int pageSize,Map<String,String> map);

    long getCounts();

    List<Exam> findExam( int pageSize, int curPage,Map<String,String> map);

}
