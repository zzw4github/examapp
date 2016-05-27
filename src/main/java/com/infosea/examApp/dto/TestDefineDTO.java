package com.infosea.examApp.dto;

import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.Type;

import java.util.List;

/**
 * Created by infosea on 2016/5/25.
 */
public class TestDefineDTO {
    private List<QuestionType> types;

    public List<QuestionType> getTypes() {
        return types;
    }

    public void setTypes(List<QuestionType> types) {
        this.types = types;
    }
}
