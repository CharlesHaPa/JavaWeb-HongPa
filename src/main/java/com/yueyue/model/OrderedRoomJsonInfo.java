package com.yueyue.model;

/**
 * Created by dkq on 2016/6/4.
 */
public class OrderedRoomJsonInfo {

    private String status;
    private int orderId;
    private CustomerJsonInfo client;

    public OrderedRoomJsonInfo(String status, int orderId, CustomerJsonInfo client) {

        this.status = status;
        this.orderId = orderId;
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public int getOrderId() {
        return orderId;
    }

    public CustomerJsonInfo getClient() {
        return client;
    }
}
