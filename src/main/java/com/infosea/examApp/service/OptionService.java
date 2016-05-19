package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Option;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface OptionService {
    Option find(long id);

    long save(Option option);

    List<Option> findAll();
}
