package com.yueyue.dao;

import com.yueyue.model.Customer;

import java.util.List;

public interface CustomerDao {

    public int add(String name, String gender, String idNum, String phone);

    public void delete(int id);

    public void update(int id, String name, String gender, String idNum, String phone);

    public Customer findById(int id);

    public List<Customer> findByName(String name);

    public List<Customer> findByIdNum(String idNum);

    public List<Customer> findByPhone(String phone);


}
