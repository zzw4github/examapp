package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

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





}
