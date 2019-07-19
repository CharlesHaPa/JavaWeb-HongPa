package com.yueyue.model;

public class Customer {

    private int id;
    private String name;
    private String gender;
    private String idNum;
    private String phone;

    public Customer() {

    }

    public Customer(int id, String name, String gender, String idNum, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.idNum = idNum;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
