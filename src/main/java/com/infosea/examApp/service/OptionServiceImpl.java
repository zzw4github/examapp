package com.infosea.examApp.service;

import com.infosea.examApp.dao.OptionDao;
import com.infosea.examApp.pojo.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionDao optionDao;
    @Override
    public Option find(long id) {
        return optionDao.find(id);
    }

    @Override
    public long save(Option option) {
        return (long)optionDao.save(option);
    }

    @Override
    public List<Option> findAll() {
        return optionDao.findAll();
    }
}
