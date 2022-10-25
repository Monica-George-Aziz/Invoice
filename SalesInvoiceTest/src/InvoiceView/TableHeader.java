package InvoiceView;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableHeader extends  AbstractTableModel{
	private ArrayList<InvoiceModelHeader> invoices;
	private String[] columns = {"Num","Name","Date","Total"};

	public TableHeader(ArrayList<InvoiceModelHeader> invoices) {
		this.invoices = invoices;
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return invoices.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		InvoiceModelHeader inv = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inv.getNum();
            case 1: 
                return inv.getName();
            case 2:
                return Invoice.df.format(inv.getDate());
          
            default:
                return "";
        }
	}
	@Override
	public String getColumnName(int column) {
		
		return columns[column];
	}




}
