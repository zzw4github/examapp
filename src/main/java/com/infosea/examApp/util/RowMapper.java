package com.infosea.examApp.util;

/**
 * Created by infosea on 2016/5/18.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper
 * @author yanzhou
 * @version v1.0
 */
public interface RowMapper
{
    public Object mapRow(ResultSet rs, int index)
            throws SQLException;
}