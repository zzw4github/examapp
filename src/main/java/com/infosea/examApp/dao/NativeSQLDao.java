package com.infosea.examApp.dao;


import org.hibernate.Query;

import java.util.List;

/**
 * Created by infosea on 2016/4/28.
 */
public interface NativeSQLDao {
    List<Integer> getIds(String sql);
    int getMaxId(String sql);
    Query createQuery(String sql);
}
