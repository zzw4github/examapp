package com.infosea.examApp.dao;

import java.util.List;

/**
 * Created by infosea on 2016/5/24.
 */
public interface CommonDao {
    public List<Integer> getIds(String sql);
    public int getCount(String hql);
}
