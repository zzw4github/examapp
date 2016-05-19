package com.infosea.examApp.service;

import com.infosea.examApp.dao.TypeDao;
import com.infosea.examApp.pojo.Type;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by infosea on 2016/5/19.
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeDao typeDao;
    @Override
    public Type findById(long id) {
        return typeDao.findById(id);
    }

    @Override
    public void delete(Type type) {
        typeDao.delete(type);
    }

    @Override
    public long save(Type type) {
        return  typeDao.save(type);
    }
}
