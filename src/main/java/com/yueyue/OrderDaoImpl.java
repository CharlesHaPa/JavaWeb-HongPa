package com.yueyue;

import com.yueyue.dao.OrderDao;
import com.yueyue.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(int customerId, String roomId, Date date) {

        String SQL = "INSERT INTO orders (customer_id, room_id, date) VALUES (?, ? ,?)";
        this.jdbcTemplate.update(SQL, customerId, roomId, date);
    }

    @Override
    public void delete(int id) {

        String SQL = "DELETE FROM orders EHERE id = ?";
        this.jdbcTemplate.update(SQL, id);
    }

    @Override
    public void deleteByRoomID(String roomID) {

        String SQL = "DELETE FROM orders WHERE room_id = ?";
        this.jdbcTemplate.update(SQL, roomID);
    }

    @Override
    public void update(int id, int customerId, String roomId, Date date) {

        String SQL = "UPDATE orders SET customerId = ?, roomId = ?, date = ? wHERE id = ?";
        this.jdbcTemplate.update(SQL, customerId, roomId, date, id);
    }

    @Override
    public Order findById(int id) {

        String SQL = "SELECT * FROM orders WHERE id = ?";
        Order order = this.jdbcTemplate.queryForObject(SQL, new Object[]{id}, new OrderRowMapper());
        return order;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        String SQL = "SELECT * FROM orders WHERE customer_id = ?";
        List<Order> orders = this.jdbcTemplate.query(SQL, new Object[]{customerId}, new OrderRowMapper());
        return orders;
    }

    @Override
    public List<Order> findByRoomId(String roomId) {
        String SQL = "SELECT * FROM orders WHERE room_id = ?";
        List<Order> orders = this.jdbcTemplate.query(SQL, new Object[]{roomId}, new OrderRowMapper());
        return orders;
    }

    @Override
    public List<Order> findByDate(Date date) {
        String SQL = "SELECT * FROM orders WHERE date = ?";
        List<Order> orders = this.jdbcTemplate.query(SQL, new Object[]{date}, new OrderRowMapper());
        return orders;
    }

    @Override
    public List<Order> findByRoomIdAndDate(String roomId, Date date) {
        String SQL = "SELECT * FROM orders WHERE room_id = ? AND date = ?";
        List<Order> orders = this.jdbcTemplate.query(SQL, new Object[]{roomId, date}, new OrderRowMapper());
        return orders;
    }

    private static final class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customer_id"));
            order.setRoomId(rs.getString("room_id"));
            order.setDate(rs.getDate("date"));

            return order;
        }

    }
}
