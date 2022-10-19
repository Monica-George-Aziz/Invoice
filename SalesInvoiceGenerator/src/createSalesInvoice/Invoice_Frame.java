package createSalesInvoice;
import java.awt.FlowLayout;

import javax.swing.*;

public class Invoice_Frame extends JFrame {
	private JButton CreateBtn ;
	private JButton DeleteBtn ;
	private JButton SaveBtn ;
	private JButton CancelBtn;
	public  Invoice_Frame() {
		
	setLayout(new FlowLayout());
	 CreateBtn= new JButton("Create");
		add(CreateBtn);
		CreateBtn.setLocation(1000,1000);
		
		 DeleteBtn= new JButton("Delete");
			add(DeleteBtn);
		
			SaveBtn= new JButton("Save");
				add(SaveBtn);
				 CancelBtn= new JButton("Cancel");
					add(CancelBtn);
	setSize(600, 400);
	setLocation(100,100);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
		//f.setVisible(true);
		
		
	}


}
