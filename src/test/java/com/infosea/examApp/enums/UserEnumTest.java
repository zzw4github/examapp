package com.infosea.examApp.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by infosea on 2016/5/18.
 */

public class UserEnumTest  {


    @Test
    public void equals(){
       System.out.println(UserEnum.ADMIN.getRole());
   }

}