package com.yueyue;

import com.yueyue.dao.OrderDao;
import com.yueyue.dao.ReserveDao;
import com.yueyue.dao.RoomDao;
import com.yueyue.exceptions.BadRequestException;
import com.yueyue.exceptions.ConflictException;
import com.yueyue.exceptions.ForbiddenException;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.ReserveJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class ReserveController {

    @Autowired
    private ReserveDao reserveDaoImpl;
    @Autowired
    private RoomDao roomDaoImpl;
    @Autowired
    private OrderDao orderDaoImpl;

    //mapping:make a reserve

    @RequestMapping(value = "/api/days/{day}/rooms/{room}/reserve", method = RequestMethod.POST,
            consumes = "application/json")
    public void add_reserve(@RequestBody ReserveJson customer, @PathVariable String day, @PathVariable String room) {
        Date date = Date.valueOf(day);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(cal.getTime());
        Date systemDate = Date.valueOf(currentDate);
        if (date.compareTo(systemDate) < 0)
            throw new ForbiddenException("Past Day");

        if (!(reserveDaoImpl.findByRoomIdAndOrderDate(room, date).isEmpty()
                && orderDaoImpl.findByRoomIdAndDate(room, date).isEmpty())) {
            throw new ConflictException("Room Token!");
        }

        if (roomDaoImpl.findById(room) == null)
            throw new NotFoundException("Room Not Found!");

        reserveDaoImpl.add(customer.name, customer.gender, customer.phone, room,
                systemDate, date);
    }

    //mapping:cancel a reserve

    @RequestMapping(value = "/api/reserves/{roomID}", method = RequestMethod.DELETE)

    public void cancelAReserve(@PathVariable String roomID) {

        if (reserveDaoImpl.findByRoomId(roomID).isEmpty())
            throw new NotFoundException("Reserve Not Found!");

        reserveDaoImpl.deleteByRoomID(roomID);

    }

}