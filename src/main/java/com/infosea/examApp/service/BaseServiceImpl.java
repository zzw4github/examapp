package com.infosea.examApp.service;

import com.infosea.examApp.dao.NativeSQLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/4/28.
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    NativeSQLDao nativeSQLDao;
    @Override
    public int findMaxId(String sql) {
        return nativeSQLDao.getMaxId(sql);
    }
}
