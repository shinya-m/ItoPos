/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.ArrayList;

/**
 *
 * @author yasu
 */
public class Customer {

    private String name;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    private String felicaId;
    private String account_name;
    private int cost;
    private int after_cost;
    private int user_num;
    private ArrayList<Item> shoppingCurt;
    private double allConsumedPoint;

    public double getAllConsumedPoint() {
        return allConsumedPoint;
    }

    public void setAllConsumedPoint(double allConsumedPoint) {
        this.allConsumedPoint = allConsumedPoint;
    }
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAfter_cost(int after_cost) {
        this.after_cost = after_cost;
    }
    
    public void itemToCurt(Item item){
        shoppingCurt.add(item);
    }

    public int getAfter_cost() {
        return after_cost;
    }

    public Customer() {
        this.shoppingCurt = new ArrayList<Item>(100);
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFelicaId(String mID) {
        this.felicaId = mID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_name() {
        return account_name;
    }

    public int getCost() {
        return cost;
    }

    public String getFelicaId() {
        return felicaId;
    }

    public String getName() {
        return name;
    }
}
