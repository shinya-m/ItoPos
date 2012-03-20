/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import obj.History;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasuhiro-i
 */
public class JdbcHistoryDao implements HistoryDao {

    private Connection conn = null;

    public JdbcHistoryDao(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<String> getHistory() {
        ArrayList<String> history = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("select user.nickname, buppin.name, history.date " +
                    "from history, user, buppin where buppin.barcode=history.bid and history.uid = user.mid order by history.date desc limit 0, 10");
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("buppin.name").length() > 5) {
                    history.add(rs.getString("user.nickname") + " " + rs.getString("buppin.name").substring(0, 5) + " " + rs.getString("history.date"));
                } else {
                    history.add(rs.getString("nickname") + " " + rs.getString("name") + " " + rs.getString("date"));
                }
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
        return history;
    }

    public boolean insertHistory(History h) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            stmt = conn.prepareStatement("INSERT into history (uid, bid, date) values(?, ?, now());");
            stmt.setString(1, h.getUid());
            stmt.setString(2, h.getBid());

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
}
