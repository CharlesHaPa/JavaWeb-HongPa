package com.yueyue;

import com.yueyue.dao.RoomDao;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("RoomDao")
public class RoomDaoImpl implements RoomDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(String id) {

        String SQL = "INSERT INTO rooms (id) VALUES (?)";
        this.jdbcTemplate.update(SQL, id);
    }

    @Override
    public Room findById(String id) {

        try {
            String SQL = "SELECT * FROM rooms WHERE id = ?";
            Room room = this.jdbcTemplate.queryForObject(SQL, new Object[]{id}, new RoomRowMapper());
            return room;
        } catch (DataAccessException e) {
            throw new NotFoundException("Room Not Found!");
        }

    }

    @Override
    public void delete(String id) {

        String SQL = "DELETE FROM rooms WHERE id = ?";
        this.jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<Room> findAll() {
        String SQL = "SELECT * FROM rooms";
        List<Room> rooms = this.jdbcTemplate.query(SQL, new RowMapper<Room>() {

            @Override
            public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
                Room room = new Room(rs.getString("id"));
                return room;
            }

        });
        return rooms;
    }

    private static final class RoomRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rownum) throws SQLException {
            Room room = new Room();
            room.setId(rs.getString("id"));
            return room;
        }
    }

}
