/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.Item;
import obj.Customer;

/**
 *
 * @author yasu
 */
public class JdbcItemDao implements ItemDao {

    private Connection conn = null;

    public JdbcItemDao(Connection conn) {
        this.conn = conn;
    }

    public Item selectByBarCode(String barcode) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item = new Item();
        try {
            stmt = conn.prepareStatement(
                    "select name, barcode, cost, type, num, sold_cost from buppin where barcode = ? ");
            stmt.setString(1, barcode);
            rs = stmt.executeQuery();
            while (rs.next()) {
                item.setBarcode(rs.getString("barcode"));
                item.setCost(rs.getInt("cost"));
                item.setName(rs.getString("name"));
                item.setPro_num(rs.getInt("num"));
                item.setType(rs.getString("type"));
                item.setSold_cost(rs.getInt("sold_cost"));
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
        return item;
    }

    public boolean updateBuppin(Item buppin) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("update buppin set num = ?, name = ?, date = now(), type = ?, cost = ?, sold_cost = ? where barcode = ?");
            stmt.setInt(1, buppin.getPro_num());
            stmt.setString(2, buppin.getName());
            stmt.setString(3, buppin.getType());
            stmt.setInt(4, buppin.getCost());
            stmt.setInt(5, buppin.getSold_cost());
            stmt.setString(6, buppin.getBarcode());

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

    public boolean addBuppin(Item newItem) {
        PreparedStatement stmt = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("INSERT into buppin (name, barcode, cost, num, date, type, sold_cost) values(?, ?, ?, ?, now(), ?, ?);");
            stmt.setString(1, newItem.getName());
            stmt.setString(2, newItem.getBarcode());
            stmt.setInt(3, newItem.getCost());
            stmt.setInt(4, newItem.getPro_num());
            stmt.setString(5, newItem.getType());
            stmt.setInt(6, newItem.getSold_cost());
            stmt.executeUpdate();
            //isExist = rs.next();

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }

    public boolean isBuppinExist(String bcode) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("select * from  buppin where barcode = ?");
            stmt.setString(1, bcode);
            rs = stmt.executeQuery();
            isExist = rs.next();
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isExist;
    }

    public Customer selectCustomerByID(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isHumanExist(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int customerIdFromFelicaID(String felicaID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCustomerCost(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
