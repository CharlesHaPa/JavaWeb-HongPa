package com.yueyue.model;

import java.sql.Date;

public class Reserve {

    private int id;
    private String customerName;
    private String customerGender;
    private String customerPhone;
    private String roomId;
    private Date reserveDate;
    private Date orderDate;

    public Reserve() {

    }

    public Reserve(int id, String customerName, String customerGender, String customerPhone, String roomId,
                   Date reserveDate, Date orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerPhone = customerPhone;
        this.roomId = roomId;
        this.reserveDate = reserveDate;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
