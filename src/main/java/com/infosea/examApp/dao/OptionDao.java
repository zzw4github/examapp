package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Option;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface OptionDao {
    public Option find(long id);
    public Serializable save(Option option);
    public List<Option> findAll();

}
