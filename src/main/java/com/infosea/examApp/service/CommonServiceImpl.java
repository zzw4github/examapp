package com.infosea.examApp.service;

import com.infosea.examApp.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
@Repository
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonDao commonDao;
    @Override
    public Object selectById(Class c, Serializable s) {
        return commonDao.selectById(c,s);
    }

    @Override
    public List selectAll(String hql) {
        return commonDao.selectAll(hql);
    }

    @Override
    public int findMaxId(String sql) {
        return commonDao.findMaxId(sql);
    }
}
