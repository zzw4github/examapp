package com.infosea.examApp.service;

import com.infosea.examApp.pojo.Option;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface OptionService {
    public Option find(long id);
    public long save(Option option);
    public List<Option> findAll();
}
