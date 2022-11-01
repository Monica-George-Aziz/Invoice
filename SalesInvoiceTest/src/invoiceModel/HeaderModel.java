/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoiceModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import invoiceView.View;



public class HeaderModel extends AbstractTableModel {

    private ArrayList<HeaderTable> invoices;
    private String[] columns = {"Num", "Name", "Date", "Total"};

    public HeaderModel(ArrayList<HeaderTable> invoices) {
        this.invoices = invoices;
    }
   
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HeaderTable inv = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inv.getNum();
            case 1: 
                return inv.getName();
            case 2:
                return View.df.format(inv.getDate());
            case 3:
                return inv.getTotal();
            default:
                return "";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
