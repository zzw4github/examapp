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
  public  Exam findExam(long id);
  public  List<Exam> findAllExam();
  public  Serializable save(Exam exam);
  public Exam findExamByExamIdandUserId(long eid,long uid);
  public void del(Exam exam);
  public void update(Exam exam);
}
