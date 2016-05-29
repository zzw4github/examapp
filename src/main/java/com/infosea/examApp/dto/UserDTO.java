package com.infosea.examApp.dto;

/**
 * Created by infosea on 2016/5/24.
 */
public class UserDTO {
    private String name;
    private String tmh;
    private long id;

    public UserDTO() {
    }

    public UserDTO(String name, String tmh,long id) {
        this.name = name;
        this.tmh = tmh;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", tmh='" + tmh + '\'' +
                ", id=" + id +
                '}';
    }
}
