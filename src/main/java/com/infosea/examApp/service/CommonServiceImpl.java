package com.infosea.examApp.service;

import com.infosea.examApp.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
@Repository
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonDao commonDao;

    @Transactional
    @Override
    public List<Integer> getIds(String sql) {
        return  commonDao.getIds(sql);
    }
}
