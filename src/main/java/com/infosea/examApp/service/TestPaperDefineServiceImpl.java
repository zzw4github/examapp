package com.infosea.examApp.service;

import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.pojo.TestPaperDefine;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/18.
 */
@Service
public class TestPaperDefineServiceImpl implements TestPaperDefineService {
    @Autowired
    TestPaperDefineDao testPaperDefineDao;
    @Override
    public TestPaperDefine findById(long id) {
        return testPaperDefineDao.findById(id);
    }

    @Override
    public List<TestPaperDefine> findAll() {
        return testPaperDefineDao.findAll();
    }

    @Override
    public Serializable save(TestPaperDefine testPaperDefine) {
        return testPaperDefineDao.save(testPaperDefine);
    }


    @Override
    public void del(TestPaperDefine testPaperDefine) {
        testPaperDefineDao.del(testPaperDefine);
    }

    @Override
    public void del(long id) {
        testPaperDefineDao.del(id);
    }

    @Override
    public void update(TestPaperDefine testPaperDefine) {
        testPaperDefineDao.update(testPaperDefine);
    }
}
