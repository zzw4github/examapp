package com.infosea.examApp.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
public interface CommonService {
    Object selectById(Class c, Serializable s);
    List selectAll(String hql);
    int findMaxId(String sql);

}
