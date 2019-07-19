package com.yueyue;

import com.yueyue.dao.CustomerDao;
import com.yueyue.dao.OrderDao;
import com.yueyue.dao.ReserveDao;
import com.yueyue.dao.RoomDao;
import com.yueyue.exceptions.ConflictException;
import com.yueyue.exceptions.ForbiddenException;
import com.yueyue.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private OrderDao orderDaoImpl;
    @Autowired
    private ReserveDao reserveDaoImpl;
    @Autowired
    private RoomDao roomDaoImpl;
    @Autowired
    private CustomerDao customerDaoImpl;

    @RequestMapping(value = "/api/days/{dateValue}/rooms", method = RequestMethod.GET)
    public
    @ResponseBody
    RoomStatusJsonInfo listRoomInADay(
            @PathVariable String dateValue) {

        Date date = Date.valueOf(dateValue);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(cal.getTime());
        Date systemDate = Date.valueOf(currentDate);
        if (date.compareTo(systemDate) < 0)
            throw new ForbiddenException("Past Day");

        List<Order> orders = orderDaoImpl.findByDate(date);
        List<Reserve> reserves = reserveDaoImpl.findByOrderDate(date);
        List<Room> rooms = roomDaoImpl.findAll();
        List<String> orderedList = new ArrayList<>();
        List<String> reservedList = new ArrayList<>();
        List<String> roomsList = new ArrayList<>();

        for (Order order : orders) {
            orderedList.add(order.getRoomId());
        }

        for (Reserve reserve : reserves) {
            reservedList.add(reserve.getRoomId());
        }

        for (Room room : rooms) {
            roomsList.add(room.getId());
        }

        for (String orderedRoom : orderedList) {
            reservedList.remove(orderedRoom);
        }

        for (String orderedRoom : orderedList) {
            roomsList.remove(orderedRoom);
        }

        for (String reservedRoom : reservedList) {
            roomsList.remove(reservedRoom);
        }

        String[] ordered = new String[orderedList.size()];
        String[] reserved = new String[reservedList.size()];
        String[] free = new String[roomsList.size()];
        ordered = orderedList.toArray(ordered);
        reserved = reservedList.toArray(reserved);
        free = roomsList.toArray(free);

        return new RoomStatusJsonInfo(ordered, reserved, free);
    }

    @RequestMapping(value = "/api/days/{day}/rooms/{room}", method = RequestMethod.GET)
    public
    @ResponseBody
    Object queryRoomInADay(@PathVariable String day, @PathVariable String room) {

        Date date = Date.valueOf(day);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(cal.getTime());
        Date systemDate = Date.valueOf(currentDate);
        if (date.compareTo(systemDate) < 0)
            throw new ForbiddenException("Past Day");

        List<Order> orders = orderDaoImpl.findByRoomIdAndDate(room, date);
        List<Reserve> reserves = reserveDaoImpl.findByRoomIdAndOrderDate(room, date);
        if (orders.isEmpty() && reserves.isEmpty())
            return "{\"status\": \"free\"}";
        if (orders.isEmpty() && reserves.size() == 1) {
            Reserve reserve = reserves.get(0);
            return new ReservedRoomJsonInfo("reserved", reserve.getId(), new CustomerJsonInfo(reserve.getCustomerName(), reserve.getCustomerGender(), reserve.getCustomerPhone()));
        }
        if (orders.size() == 1 && (reserves.isEmpty() || reserves.size() == 1)) {
            Order order = orders.get(0);
            Customer customer = customerDaoImpl.findById(order.getCustomerId());
            return new OrderedRoomJsonInfo("ordered", order.getId(), new CustomerJsonInfo(customer.getName(), customer.getGender(), customer.getPhone(), customer.getIdNum()));
        }
        throw new ConflictException("Conflicts");

    }

}
