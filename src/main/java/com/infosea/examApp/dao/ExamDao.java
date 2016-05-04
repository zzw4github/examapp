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
  Exam findExam(long id);
  List<Exam> findAllExam();
  Serializable save(Exam exam);
  Exam findExamByExamIdandUserId(long eid, long uid);
  void del(Exam exam);
  void update(Exam exam);
}
