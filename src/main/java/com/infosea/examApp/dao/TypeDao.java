package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Type;

/**
 * Created by infosea on 2016/5/19.
 */
public interface TypeDao {

    Type findById(long id);

    void delete(Type type);

    long save(Type type);
}
