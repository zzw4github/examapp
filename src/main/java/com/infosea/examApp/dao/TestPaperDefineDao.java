package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.TestPaperDefine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/17.
 */
public interface TestPaperDefineDao {
    TestPaperDefine findTestPaperDefineById(long id);
    List<TestPaperDefine> findAllTestPaperDefine();
    Serializable save(TestPaperDefine testPaper);
    TestPaperDefine findTestPaperByTestPaperDefineIdAndUserId(long eid, long uid);
    void del(TestPaperDefine testPaper);
    void update(TestPaperDefine testPaper);
    void flush();
}
