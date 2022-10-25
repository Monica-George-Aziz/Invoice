package InvoiceView;



public class InvoiceModelItems{

	    
	    private String name;
	    private int price;
	    private int count;
	    private InvoiceModelHeader invoice;

	    public InvoiceModelItems(String name, int price, int count, InvoiceModelHeader invoice) {
    this.name = name;
    this.price = price;
    this.count = count;
    this.invoice = invoice;
   // invoice.getItems().add(this);
}

public int getTotal() {
    return price * count;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getPrice() {
    return price;
}

public void setPrice(int price) {
    this.price = price;
}

public int getCount() {
    return count;
}

public void setCount(int count) {
    this.count = count;
}

public InvoiceModelHeader getInvoice() {
    return invoice;
}

public void setInvoice(InvoiceModelHeader invoice) {
    this.invoice = invoice;
}

@Override
public String toString() {
    return "InvoiceItem{" + "name=" + name + ", price=" + price + ", count=" + count + '}';
}

}
