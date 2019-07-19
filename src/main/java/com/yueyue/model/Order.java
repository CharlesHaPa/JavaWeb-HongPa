package com.yueyue.model;

import java.sql.Date;

public class Order {

    private int id;
    private int customerId;
    private String roomId;
    private Date date;

    public Order() {

    }

    public Order(int id, int customerId, String roomId, Date date) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" + "customerId: " + customerId + "\n" + "roomId: " + roomId + "\n" + "date: " + date
                + "\n";
    }
}
