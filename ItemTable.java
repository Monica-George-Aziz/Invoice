
package invoiceModel;
//model to create table Items

public class ItemTable {
    
    private String name;
    private int price;
    private int count;
    private HeaderTable invoice;

    public ItemTable(String name, int price, int count, HeaderTable invoice) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
        invoice.getItems().add(this);
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

    public HeaderTable getInvoice() {
        return invoice;
    }

    public void setInvoice(HeaderTable invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "name=" + name + ", price=" + price + ", count=" + count + '}';
    }
    
    public String getAsCSV() {
        return getInvoice().getNum()+","+getName()+","+price+","+count;
    }
    
}
