/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

/**
 *
 * @author yasu,shinya-m
 */
public class Item {
    private String name;//名前
    private int cost;//仕入れ値
    private String barcode;//バーコード
    private String type;//種類
    private int pro_num;//個数
    private int sold_cost;//売値

    public int getSold_cost() {
        return sold_cost;
    }

    public void setSold_cost(int sold_cost) {
        this.sold_cost = sold_cost;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPro_num(int pro_num) {
        this.pro_num = pro_num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getPro_num() {
        return pro_num;
    }

    public String getType() {
        return type;
    }
    
    
}
