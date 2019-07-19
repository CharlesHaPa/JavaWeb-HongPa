package com.yueyue;

import com.yueyue.dao.OrderDao;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

@Controller
public class TestingController {
    @Autowired
    private OrderDao orderDaoImpl;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public
    @ResponseBody
    String findAnOrder(@RequestParam(value = "date", required = true) String dateValue) {
        Date date = Date.valueOf(dateValue);
        List<Order> orders;

        orders = orderDaoImpl.findByDate(date);
        if (orders.isEmpty()) {
            throw new NotFoundException("No Such Order");
        }
        String result = "Order List: \n";
        for (Order order : orders) {
            result += order.toString();
        }
        return result;
    }
}
