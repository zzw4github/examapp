package com.infosea.examApp.pojo;

/**
 * Created by infosea on 2016/5/23.
 */
public class TestObj {
    private String name ;
    private int age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
