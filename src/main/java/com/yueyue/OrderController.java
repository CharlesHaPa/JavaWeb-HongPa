package com.yueyue;

import com.yueyue.dao.CustomerDao;
import com.yueyue.dao.OrderDao;
import com.yueyue.dao.ReserveDao;
import com.yueyue.dao.RoomDao;
import com.yueyue.exceptions.ConflictException;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.ClientIDJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.Calendar;

@Controller
public class OrderController {

    @Autowired
    private ReserveDao reserveDaoImpl;
    @Autowired
    private RoomDao roomDaoImpl;
    @Autowired
    private CustomerDao customerDaoImpl;
    @Autowired
    private OrderDao orderDaoImpl;


    @RequestMapping(value = "/api/reserves/{reserve}/rooms/order", method = RequestMethod.POST, consumes = "application/json")
    public void orderAReservedRoom(@RequestBody ClientIDJson clientID, @PathVariable int reserve) {
        String room_id = reserveDaoImpl.getRoomId(reserve);
        if (reserveDaoImpl.findById(reserve) == null)
            throw new NotFoundException("Reserve Not Found!");
        if (!(orderDaoImpl.findByRoomIdAndDate(room_id, new Date(Calendar.getInstance().getTimeInMillis())).isEmpty())) {
            throw new ConflictException("Room Token!");
        }
        reserveDaoImpl.delete(reserve);
        orderDaoImpl.add(clientID.getClientID(), room_id, new Date(Calendar.getInstance().getTimeInMillis()));
    }


    @RequestMapping(value = "/api/rooms/{room}/order", method = RequestMethod.POST,
            consumes = "application/json")
    public void order(@RequestBody ClientIDJson clientID, @PathVariable String room) {
        if (!(reserveDaoImpl.findByRoomIdAndOrderDate
                (room, new Date(Calendar.getInstance().getTimeInMillis())).isEmpty()
                && orderDaoImpl.findByRoomIdAndDate
                (room, new Date(Calendar.getInstance().getTimeInMillis())).isEmpty()))
            throw new ConflictException("Room Token!");

        if (roomDaoImpl.findById(room) == null)
            throw new NotFoundException("Room Not Found!");

        if (customerDaoImpl.findById(clientID.getClientID()) == null)
            throw new NotFoundException("Customer Not Found!");

        orderDaoImpl.add(clientID.getClientID(), room, new Date(Calendar.getInstance().getTimeInMillis()));

    }

    @RequestMapping(value = "/api/orders/{roomID}", method = RequestMethod.DELETE)

    public void cancelAnOrder(@PathVariable String roomID) {

        if (orderDaoImpl.findByRoomId(roomID).isEmpty())
            throw new NotFoundException("Order Not Found!");

        orderDaoImpl.deleteByRoomID(roomID);

    }

}