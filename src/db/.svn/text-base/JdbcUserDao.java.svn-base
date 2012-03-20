/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.*;
import java.util.ArrayList;
import obj.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yasuhiro-i
 */
public class JdbcUserDao implements UserDao {

    private Connection conn = null;

    JdbcUserDao(Connection connection) {
        this.conn = connection;
    }

    public boolean addUser(Customer user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("INSERT into user (name, point, mid, type, nickname) values(?, ?, ?, ?, ?);");
            stmt.setString(1, user.getName());
            //user.getName().co
            stmt.setInt(2, user.getCost());
            stmt.setString(3, user.getFelicaId());
            stmt.setString(4, user.getType());
            stmt.setString(5, user.getNickName());
            stmt.executeUpdate();
            //isExist = rs.next();
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public ArrayList<Customer> selectUserByName(String name) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        ArrayList<Customer> users = new ArrayList<Customer>();
        try {
            stmt = conn.prepareStatement("select name, point, mID, allpoint, nickname from user where name like" + " concat('%', ? , '%') ");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer user = new Customer();
                user.setName(rs.getString("name"));
                user.setFelicaId(rs.getString("mID"));
                user.setCost(rs.getInt("point"));
                user.setAllConsumedPoint(rs.getDouble("allpoint"));
                user.setNickName(rs.getString("nickname"));
                users.add(user);
            }
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(String felicaid, int point, double allp) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("update user set point = ?, allpoint = ? where mID = ?;");
            stmt.setInt(1, point);
            stmt.setString(3, felicaid);
            stmt.setDouble(2, allp);
            stmt.executeUpdate();

            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Customer selectUser(String felicaid) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        Customer user = new Customer();
        try {
            stmt = conn.prepareStatement("select name, point, mID, type, allpoint, nickname  from user where mID = ? ");
            stmt.setString(1, felicaid);
            rs = stmt.executeQuery();
            while (rs.next()) {

                user.setName(rs.getString("name"));
                user.setFelicaId(rs.getString("mID"));
                user.setCost(rs.getInt("point"));
                user.setType(rs.getString("type"));
                user.setAllConsumedPoint(rs.getDouble("allpoint"));
                user.setNickName(rs.getString("nickname"));
            }
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    public ArrayList<Customer> selectUserLanking() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        ArrayList<Customer> users = new ArrayList<Customer>();
        try {
            stmt = conn.prepareStatement("select * from user order by allpoint desc");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer user = new Customer();
                user.setName(rs.getString("name"));
                user.setFelicaId(rs.getString("mid"));
                user.setCost(rs.getInt("point"));
                user.setAllConsumedPoint(rs.getDouble("allpoint"));
                user.setNickName(rs.getString("nickname"));
                users.add(user);
            }
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
