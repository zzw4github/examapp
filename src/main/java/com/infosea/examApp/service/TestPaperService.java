package com.infosea.examApp.service;

import com.infosea.examApp.pojo.TestPaper;

import java.io.Serializable;
import java.util.List;

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
}
