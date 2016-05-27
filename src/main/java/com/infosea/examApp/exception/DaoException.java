package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/23.
 */
public class DaoException extends  ExamException {
    private static final String DAO_EXCEPTION = "DAO_EXCEPTION";

    public DaoException(String message, Throwable cause) {
        super(DAO_EXCEPTION, cause);
    }
}
