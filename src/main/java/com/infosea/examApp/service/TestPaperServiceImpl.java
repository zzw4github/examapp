package com.infosea.examApp.service;

import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.pojo.TestPaper;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    TestPaperDao testPaperDao;
    @Override
    public TestPaper findTestPaperById(long id) {
        return testPaperDao.findTestPaperById(id);
    }

    @Override
    public List<TestPaper> findAllTestPaper() {
        return testPaperDao.findAllTestPaper();
    }

    @Override
    public Serializable save(TestPaper testPaper) {
        return testPaperDao.save(testPaper);
    }

    @Override
    public TestPaper findTestPaperByTestPaperIdandUserId(long eid, long uid) {
        return testPaperDao.findTestPaperByTestPaperIdandUserId(eid,uid);
    }

    @Override
    public void del(TestPaper testPaper) {
         testPaperDao.del(testPaper);
    }

    @Override
    public void update(TestPaper testPaper) {
        testPaperDao.update(testPaper);
    }
}
