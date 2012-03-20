/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import obj.Item;
import obj.Customer;

/**
 *
 * @author yasu
 */
public interface ItemDao {

    abstract Item selectByBarCode(String barcode);

    abstract Customer selectCustomerByID(int id);

    abstract boolean isHumanExist(int id);

    abstract int customerIdFromFelicaID(String felicaID);

    abstract void updateCustomerCost(Customer customer);

    abstract boolean updateBuppin(Item buppin);

    abstract boolean addBuppin(Item newItem);

    abstract boolean isBuppinExist(String bcode);
}
