package com.infosea.examApp.service;

import com.infosea.examApp.dao.NativeSQLDao;
import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.pojo.TestPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by infosea on 2016/4/25.
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    TestPaperDao testPaperDao;
    @Autowired
    NativeSQLDao nativeSQLDao;

    @Override
    @Transactional
    public TestPaper findById(long id) {
        return testPaperDao.findById(id);
    }

    @Override
    public List<TestPaper> findAll() {
        return testPaperDao.findAll();
    }

    @Override
    public Serializable save(TestPaper testPaper) {
        return testPaperDao.save(testPaper);
    }

    @Override
    public TestPaper findByIdAndUid(long eid, long uid) {
        return testPaperDao.findByIdAndUid(eid, uid);
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
