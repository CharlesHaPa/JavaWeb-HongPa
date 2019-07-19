package com.yueyue;

import com.yueyue.dao.ReserveDao;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("ReserveDao")
public class ReserveDaoImpl implements ReserveDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(String customerName, String customerGender, String customerPhone, String roomId, Date reserveDate,
                    Date orderDate) {

        String SQL = "INSERT INTO reserves "
                + "(customer_name, customer_gender, customer_phone, room_id, reserve_date, order_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(SQL, customerName, customerGender, customerPhone, roomId, reserveDate, orderDate);
    }

    @Override
    public void delete(int id) {

        String SQL = "DELETE FROM reserves WHERE id = ?";
        this.jdbcTemplate.update(SQL, id);
    }

    @Override
    public void deleteByRoomID(String roomID) {

        String SQL = "DELETE FROM reserves WHERE room_id = ?";
        this.jdbcTemplate.update(SQL, roomID);
    }

    @Override
    public void update(int id, String customerName, String customerGender, String customerPhone, String roomId,
                       Date reserveDate, Date orderDate) {

        String SQL = "UPDATE reserves SET " + "customer_name = ?, " + "customer_gender = ?, " + "customer_phone = ?, "
                + "room_id = ?, " + "reserve_date = ?, " + "order_date = ? " + "WHERE id = ?";
        this.jdbcTemplate.update(SQL, customerName, customerGender, customerPhone, roomId, reserveDate, orderDate, id);
    }

    @Override
    public Reserve findById(int id) {

        try {
            String SQL = "SELECT * FROM reserves WHERE id = ?";
            Reserve reserve = this.jdbcTemplate.queryForObject(SQL, new Object[]{id}, new ReserveRowMapper());
            return reserve;
        } catch (DataAccessException e) {
            throw new NotFoundException("Reserve Not Found!");
        }
    }

    @Override
    public String getRoomId(int id) {

        try {
            String SQL = "SELECT * FROM reserves WHERE id = ?";
            Reserve reserve = this.jdbcTemplate.queryForObject(SQL, new Object[]{id}, new ReserveRowMapper());
            return reserve.getRoomId();
        } catch (DataAccessException e) {
            throw new NotFoundException("RoomID Not Found!");
        }
    }


    @Override
    public List<Reserve> findByCustomerName(String customerName) {
        String SQL = "SELECT * FROM reserves WHERE customer_name = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{customerName}, new ReserveRowMapper());
        return reserves;
    }

    @Override
    public List<Reserve> findByCustomerPhone(String customerPhone) {
        String SQL = "SELECT * FROM reserves WEHERE customer_phone = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{customerPhone}, new ReserveRowMapper());
        return reserves;
    }

    @Override
    public List<Reserve> findByRoomId(String roomId) {
        String SQL = "SELECT * FROM reserves WHERE room_id = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{roomId}, new ReserveRowMapper());
        return reserves;
    }

    @Override
    public List<Reserve> findByReserveDate(Date reserveDate) {
        String SQL = "SELECT * FROM reserves WHERE reserve_date = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{reserveDate}, new ReserveRowMapper());
        return reserves;
    }

    @Override
    public List<Reserve> findByOrderDate(Date orderDate) {
        String SQL = "SELECT * FROM reserves WHERE order_date = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{orderDate}, new ReserveRowMapper());
        return reserves;
    }

    @Override
    public List<Reserve> findByRoomIdAndOrderDate(String roomId, Date orderDate) {
        String SQL = "SELECT * FROM reserves WHERE room_id = ? AND order_date = ?";
        List<Reserve> reserves = this.jdbcTemplate.query(SQL, new Object[]{roomId, orderDate}, new ReserveRowMapper());
        return reserves;
    }

    private static final class ReserveRowMapper implements RowMapper<Reserve> {

        @Override
        public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reserve reserve = new Reserve();
            reserve.setId(rs.getInt("id"));
            reserve.setCustomerName(rs.getString("customer_name"));
            reserve.setCustomerGender(rs.getString("customer_gender"));
            reserve.setCustomerPhone(rs.getString("customer_phone"));
            reserve.setRoomId(rs.getString("room_id"));
            reserve.setReserveDate(rs.getDate("reserve_date"));
            reserve.setOrderDate(rs.getDate("order_date"));
            return reserve;
        }

    }

}
