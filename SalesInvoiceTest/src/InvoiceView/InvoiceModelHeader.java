package InvoiceView;

import java.util.ArrayList;
import java.util.Date;


public class InvoiceModelHeader {
	   private int num;
	    private String name;
	    private Date date;
	    private ArrayList<InvoiceModelItems> invoices;
	   // private ArrayList<InvoiceModelHeader> items;

	    public InvoiceModelHeader(int num, String name, Date date) {
	        this.num = num;
	        this.name = name;
	        this.date = date;
	        
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

		public ArrayList<InvoiceModelItems> getInvoices() {
			   if (invoices == null) {
				   invoices = new ArrayList<>();
		        }
		        return invoices;
		}

//	    public int getTotal() {
//	        return getItems().stream().mapToInt(item -> item.getTotal()).sum();
//	    }
//
//		  public ArrayList<InvoiceModelHeader> getItems() {
//        if (items == null) {
//            items = new ArrayList<>();
//        }
//        return items;
//    }
		  @Override
		    public String toString() {
		        return "InvoiceHeader{" + "num=" + num + ", name=" + name + ", date=" + date + '}';
		    }

}
