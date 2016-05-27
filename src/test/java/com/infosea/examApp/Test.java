package com.infosea.examApp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by infosea on 2016/5/23.
 */

public class Test {

    @org.junit.Test
    public void testMap(){
        Map<Object , Object> map = new HashMap<>();
        map.put("age" ,28);
        map.put("name","zhaoziwen");
        Iterator it = map.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            Object key = it.next();
            sb.append(":").append(key).append(" and ");
            System.out.println(sb.toString());
        }
    }
}
