package com.yueyue;

import com.yueyue.dao.CustomerDao;
import com.yueyue.exceptions.ConflictException;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("CustomerDao")
public class CustomerDaoImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(String name, String gender, String id_num, String phone) {

        List<Customer> foundCustomers = this.findByIdNum(id_num);
        if (!foundCustomers.isEmpty())
            throw new ConflictException("Exiting Customer!");
        final String sql = "insert into customers (name, gender, id_num, phone) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,
                        new String[]{"id"});
                ps.setString(1, name);
                ps.setString(2, gender);
                ps.setString(3, id_num);
                ps.setString(4, phone);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Override
    public void delete(int id) {

        String SQL = "DELETE FROM customers WHERE id = ?";
        this.jdbcTemplate.update(SQL, id);
    }

    @Override
    public void update(int id, String name, String gender, String idNum, String phone) {

        String SQL = "UPDATE customers SET name = ?, gender = ?, id_num = ?, phone = ? WHERE id = ?";
        this.jdbcTemplate.update(SQL, name, gender, idNum, phone, id);
    }

    @Override
    public Customer findById(int id) {

        try {
            String SQL = "SELECT * FROM customers WHERE id = ?";
            Customer customer = this.jdbcTemplate.queryForObject(SQL, new Object[]{id}, new CustomerRowMapper());
            return customer;

        } catch (DataAccessException e) {
            throw new NotFoundException("Customer Not Found!");
        }
    }

    @Override
    public List<Customer> findByName(String name) {
        String SQL = "SELECT * FROM customers WHERE name = ?";
        List<Customer> customers = this.jdbcTemplate.query(SQL, new Object[]{name}, new CustomerRowMapper());
        return customers;
    }

    @Override
    public List<Customer> findByIdNum(String idNum) {
        String SQL = "SELECT * FROM customers WHERE id_num = ?";
        List<Customer> customers = this.jdbcTemplate.query(SQL, new Object[]{idNum}, new CustomerRowMapper());
        return customers;
    }

    @Override
    public List<Customer> findByPhone(String phone) {
        String SQL = "SELECT * FROM customers WHERE phone = ?";
        List<Customer> customers = this.jdbcTemplate.query(SQL, new Object[]{phone}, new CustomerRowMapper());
        return customers;
    }

    private static final class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setGender(rs.getString("gender"));
            customer.setIdNum(rs.getString("id_num"));
            customer.setPhone(rs.getString("phone"));
            return customer;
        }

    }

}
