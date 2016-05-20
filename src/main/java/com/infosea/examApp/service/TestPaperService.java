package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.Subject;
import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.pojo.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/25.
 */
public interface TestPaperService {
    TestPaper findById(long id);
    List<TestPaper> findAll();
    Serializable save(TestPaper testPaper);
    TestPaper findByIdAndUid(long eid, long uid);
    void del(TestPaper testPaper);
    void update(TestPaper testPaper);
    TestPaper produceTestPaper(User user , long testPaperDefineId,Exam exam);

}
