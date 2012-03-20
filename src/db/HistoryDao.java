/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import obj.*;

/**
 *
 * @author yasuhiro-i
 */
public interface HistoryDao {

    abstract boolean insertHistory(History h);

    public ArrayList<String> getHistory();
}
