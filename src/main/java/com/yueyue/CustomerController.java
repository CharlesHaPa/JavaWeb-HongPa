package com.yueyue;

import com.yueyue.dao.CustomerDao;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.AddCustomerJson;
import com.yueyue.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerDao customerDaoImpl;

    @RequestMapping(value = "/api/clients", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    String add(@RequestBody AddCustomerJson customer) {
        int clientID = customerDaoImpl.add(customer.name, customer.gender, customer.idNum, customer.phone);
        return "{\"clientID\":\"" + clientID + "\"}";
    }

    @RequestMapping(value = "/api/clients", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Customer> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "phone", required = false) String phone,
                          @RequestParam(value = "id_num", required = false) String idNum) {
        List<Customer> searchedCustomers = null;
        if (name != null && !name.equals("")) {
            searchedCustomers = customerDaoImpl.findByName(name);
        } else if (phone != null && !phone.equals("")) {
            searchedCustomers = customerDaoImpl.findByPhone(phone);
        } else if (idNum != null && !idNum.equals("")) {
            searchedCustomers = customerDaoImpl.findByIdNum(idNum);
        }
        if (searchedCustomers == null || searchedCustomers.isEmpty())
            throw new NotFoundException("Client Not Found!");
        else
            return searchedCustomers;
    }

    @RequestMapping(value = "/api/clients/{clientID}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable int clientID) {
        customerDaoImpl.update(clientID, customer.getName(), customer.getGender(), customer.getIdNum(), customer.getPhone());
    }
}
