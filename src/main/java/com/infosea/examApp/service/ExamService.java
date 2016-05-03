package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Exam;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public interface ExamService {
    List<Exam> findAll();
    Exam findExam(long id);
    long save(Exam exam);
    public Exam findExamByExamIdandUserId(long eid, long uid);
    public void delete(long id);
    public void update(Exam exam);
}
