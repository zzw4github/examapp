package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Option;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface OptionDao {
    Option find(long id);
    Serializable save(Option option);
    List<Option> findAll();

}
