package com.yueyue.dao;

import com.yueyue.model.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    public void add(int customerId, String roomId, Date date);

    public void delete(int id);

    public void update(int id, int customerId, String roomId, Date date);

    public Order findById(int id);

    public List<Order> findByCustomerId(int customerId);

    public List<Order> findByRoomId(String roomId);

    public List<Order> findByDate(Date date);

    void deleteByRoomID(String roomID);

    public List<Order> findByRoomIdAndDate(String room, Date date);

}
