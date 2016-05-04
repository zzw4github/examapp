package com.infosea.examApp.service;

import com.infosea.examApp.pojo.TestPaper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
public interface TestPaperService {
    TestPaper findTestPaperById(long id);
    List<TestPaper> findAllTestPaper();
    Serializable save(TestPaper testPaper);
    TestPaper findTestPaperByTestPaperIdandUserId(long eid, long uid);
    void del(TestPaper testPaper);
    void update(TestPaper testPaper);
}
