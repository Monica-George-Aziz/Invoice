package InvoiceView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;


public class Invoice extends javax.swing.JFrame{

	
	private JFrame frame;
	//private JTable table;
	private JTable InvTable;
	
	private JTable table_1;
	    private InvoiceController listener = new InvoiceController(this);
	    private ArrayList<InvoiceModelHeader> invoices;
	    
	    public static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    private TableHeader TableHeader;
	   // private static File Hpath= "C:\\Users\\AzizM7\\git\\Invoice\\SalesInvoiceTest\\InvoiceHeader.csv";
	    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Invoice I = new Invoice();
			  
//					try {
//						I.listener.load("InvoiceHeader.csv","InvoiceLine.csv");
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				try {
					Invoice window = new Invoice();
					window.listener.load("InvoiceHeader.csv","InvoiceLine.csv");
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Invoice() {
		initialize();
	}

	/**
	 * Initialize  the contents of the frame.
	 */
	private void initialize() {
	
	     
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem Load = new JMenuItem("Load File");
		mnNewMenu.add(Load);
		
		Load.addActionListener(listener);
		
		JMenuItem Save = new JMenuItem("Save File");
		mnNewMenu.add(Save);
		frame.getContentPane().setLayout(null);
//		 String[] Hcols= {"NO.","Customer Name","Date"}; 
//		 String[] [] Hrows= {	
//				{null, null, null, null},
//				{null, null, null, null},
//				{null, null, null, null},
//			}; 
		InvTable = new JTable();
		
		InvTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		InvTable.setColumnSelectionAllowed(true);
		InvTable.setSurrendersFocusOnKeystroke(true);
		InvTable.setBounds(10, 24, 300, 178);
//		InvTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				
//			},
//			new String[] {
//				
//			}
//		));
		
		frame.getContentPane().add(InvTable);
		
	
		JLabel lblNewLabel = new JLabel("Invoice Numbers");
		lblNewLabel.setBounds(350, 50, 77, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(".");
		lblNewLabel_1.setBounds(460, 30, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblInvoiceDate = new JLabel("Invoice Date");
		lblInvoiceDate.setBounds(350, 30, 77, 14);
		frame.getContentPane().add(lblInvoiceDate);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(350, 70, 95, 14);
		frame.getContentPane().add(lblCustomerName);
		
		JLabel lblInvoiceTotal = new JLabel("Invoice Total");
		lblInvoiceTotal.setBounds(350, 90, 95, 14);
		frame.getContentPane().add(lblInvoiceTotal);
		
		JLabel lblCustomerName_2 = new JLabel(".");
		lblCustomerName_2.setBounds(460, 70, 95, 14);
		frame.getContentPane().add(lblCustomerName_2);
		
		JLabel lblCustomerName_3 = new JLabel(".");
		lblCustomerName_3.setBounds(460, 90, 95, 14);
		frame.getContentPane().add(lblCustomerName_3);
		
		JLabel lblNewLabel_1_1 = new JLabel(".");
		lblNewLabel_1_1.setBounds(460, 50, 49, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Invoice Items");
		lblNewLabel_2.setBounds(353, 119, 67, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		table_1 = new JTable();
		table_1.setSurrendersFocusOnKeystroke(true);
		//table_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {} ,
			new String[] {}
		));
		//table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBounds(362, 144, 307, 89);
		table_1.setColumnSelectionAllowed(true);
		frame.getContentPane().add(table_1);
		
		JButton btnNewButton = new JButton("Create New Invoice");
		btnNewButton.setBounds(26, 245, 142, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDeleteinvoice = new JButton("Delete Invoice");
		btnDeleteinvoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnDeleteinvoice.setBounds(192, 245, 142, 23);
		frame.getContentPane().add(btnDeleteinvoice);
		
		JButton btnCreateNewLine = new JButton("Create New Line");
		btnCreateNewLine.setBounds(390, 245, 133, 23);
		frame.getContentPane().add(btnCreateNewLine);
		
		JButton btnDeleteLine = new JButton("Delete Line");
		btnDeleteLine.setBounds(557, 245, 112, 23);
		frame.getContentPane().add(btnDeleteLine);
	}
	public ArrayList<InvoiceModelHeader> getInvoices(){
		if(invoices==null) 
			invoices = new ArrayList<>();
			return invoices;
		
	}
	 public InvoiceModelHeader getInvoiceByNum(int num) {
		 InvoiceModelHeader inv = null;
	        for (InvoiceModelHeader item : getInvoices()) {
	            if (item.getNum() == num) {
	                inv = item;
	                break;
	            }
	        }
	        return inv;
	    }

	
	public void setTableHeader(TableHeader TableHeader) {
		this.TableHeader= TableHeader;
		this.InvTable.setModel(TableHeader); 
	}
	public TableHeader getTableHeader() {
		if(TableHeader == null)
			TableHeader= new TableHeader(invoices);
		return TableHeader;
	}
	
}
