package com.yueyue.dao;

import com.yueyue.model.Reserve;

import java.sql.Date;
import java.util.List;

public interface ReserveDao {

    public void add(String customerName, String customerGender, String customerPhone, String roomId, Date reserveDate,
                    Date orderDate);

    public void delete(int id);

    public void update(int id, String customerName, String customerGender, String customerPhone, String roomId,
                       Date reserveDate, Date orderDate);

    public Reserve findById(int id);

    public List<Reserve> findByCustomerName(String customerName);

    public List<Reserve> findByCustomerPhone(String customerPhone);

    public List<Reserve> findByRoomId(String roomId);

    public List<Reserve> findByReserveDate(Date reserveDate);

    public List<Reserve> findByOrderDate(Date orderDate);

    void deleteByRoomID(String roomID);

    String getRoomId(int id);

    public List<Reserve> findByRoomIdAndOrderDate(String room, Date date);


}
