/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import obj.Item;

/**
 *
 * @author yasu
 */
public class DaoFactry {

    public static ItemDao createItemDao() {
        return new JdbcItemDao(ConnectionUtil.getConnection());
    }

    public static UserDao createUserDao() {
        return new JdbcUserDao(ConnectionUtil.getConnection());
    }

    public static HistoryDao createHistoryDao() {
        return new JdbcHistoryDao(ConnectionUtil.getConnection());
    }
}
