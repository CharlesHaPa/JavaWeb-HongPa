package com.yueyue.model;

/**
 * Created by dkq on 2016/6/4.
 */
public class ReservedRoomJsonInfo {

    private String status;
    private int reserveId;
    private CustomerJsonInfo client;

    public ReservedRoomJsonInfo(String status, int reserveId, CustomerJsonInfo client) {
        this.status = status;
        this.reserveId = reserveId;
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public int getReserveId() {
        return reserveId;
    }

    public CustomerJsonInfo getClient() {
        return client;
    }
}