package com.yueyue.model;

/**
 * Created by dkq on 2016/6/4.
 */
public class CustomerJsonInfo {
    private String name;
    private String gender;
    private String phone;
    private String idNum;

    public CustomerJsonInfo(String name, String gender, String phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public CustomerJsonInfo(String name, String gender, String phone, String idNum) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdNum() {
        return idNum;
    }
}