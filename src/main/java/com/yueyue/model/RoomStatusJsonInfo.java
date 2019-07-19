package com.yueyue.model;

public class RoomStatusJsonInfo {

    private String[] ordered;
    private String[] reserved;
    private String[] free;

    public RoomStatusJsonInfo(String[] ordered, String[] reserved, String[] free) {
        this.ordered = ordered;
        this.reserved = reserved;
        this.free = free;
    }

    public String[] getOrdered() {
        return ordered;
    }

    public void setOrdered(String[] ordered) {
        this.ordered = ordered;
    }

    public String[] getReserved() {
        return reserved;
    }

    public void setReserved(String[] reserved) {
        this.reserved = reserved;
    }

    public String[] getFree() {
        return free;
    }

    public void setFree(String[] free) {
        this.free = free;
    }

}
