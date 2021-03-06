package com.infosea.examApp.service;

import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.pojo.TestPaperDefine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/18.
 */
public interface TestPaperDefineService {
    TestPaperDefine findById(long id);

    List<TestPaperDefine> findAll();

    Serializable save(TestPaperDefine testPaperDefine);

    void del(TestPaperDefine testPaperDefine);

    void del(long id);

    void update(TestPaperDefine testPaperDefine);
}
