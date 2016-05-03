package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by infosea on 2016/4/25.
 */
@Entity
public class TestPaper {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;
    @Column
    private String name;

    @Column
    private String sglc_ids;
    @Column
    private  String mulc_ids;

    @Column
    private String tof_ids;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSglc_ids() {
        return sglc_ids;
    }

    public void setSglc_ids(String sglc_ids) {
        this.sglc_ids = sglc_ids;
    }

    public String getMulc_ids() {
        return mulc_ids;
    }

    public void setMulc_ids(String mulc_ids) {
        this.mulc_ids = mulc_ids;
    }

    public String getTof_ids() {
        return tof_ids;
    }

    public void setTof_ids(String tof_ids) {
        this.tof_ids = tof_ids;
    }
}
