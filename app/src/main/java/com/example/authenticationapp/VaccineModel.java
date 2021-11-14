package com.example.authenticationapp;

import java.util.Date;

public class VaccineModel {

    String id, name, desc, got,date;

    public VaccineModel(String id, String name, String desc, String got, String date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.got = got;
        this.date = date;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGot() {
        return got;
    }

    public void setGot(String got) {
        this.got = got;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
