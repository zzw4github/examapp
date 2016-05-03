package com.infosea.examApp.dao;


import com.infosea.examApp.pojo.TestPaper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
public interface TestPaperDao {
    public TestPaper findTestPaperById(long id);
    public List<TestPaper> findAllTestPaper();
    public Serializable save(TestPaper testPaper);
    public TestPaper findTestPaperByTestPaperIdandUserId(long eid,long uid);
    public void del(TestPaper testPaper);
    public void update(TestPaper testPaper);
    void flush();
}
