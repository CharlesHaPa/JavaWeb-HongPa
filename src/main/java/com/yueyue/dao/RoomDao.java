package com.yueyue.dao;

import com.yueyue.model.Room;

import java.util.List;

public interface RoomDao {

    public void add(String id);

    public void delete(String id);

    public List<Room> findAll();

    public Room findById(String id);

}
