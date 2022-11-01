/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoiceModel;

import java.util.ArrayList;
import java.util.Date;

import invoiceView.View;



public class HeaderTable {
    private int num;
    private String name;
    private Date date;
    private ArrayList<ItemTable> items;

    public HeaderTable(int num, String name, Date date) {
        this.num = num;
        this.name = name;
        this.date = date;
    }

    public int getTotal() {
        return getItems().stream().mapToInt(item -> item.getTotal()).sum();
    }
    
    public int getTotal2() {
        int total = 0;
        for (ItemTable item : getItems()) {
            total += item.getTotal();
        }
        return total;
    }
    
    public ArrayList<ItemTable> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "num=" + num + ", name=" + name + ", date=" + date + '}';
    }
    
    public String getAsCSV() {
        return num+","+View.df.format(date)+","+name;
    }
}
