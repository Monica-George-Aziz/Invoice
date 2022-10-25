package InvoiceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;






public class InvoiceController implements ActionListener {
private Invoice Iframe;
public InvoiceController(Invoice Iframe) {
	this.Iframe = Iframe;
}
	 @Override
	    public void actionPerformed(ActionEvent e) {
	        String ActionC = e.getActionCommand();

	        switch (ActionC) {
            case "New Invoice":
                newInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "New Line":
                newLine();
                break;

            case "Delete Line":
                deleteLine();
                break;

            case "Load File":
                try {
					load(null, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                break;

            case "Save File":
                save();
                break;

        }
    }

    private void newInvoice() {
    }

    private void deleteInvoice() {
    }

    private void newLine() {
    }

    private void deleteLine() {
    }

    void load(String hPath, String lPath) throws IOException {
        File hFile = null;
        File lFile = null;
        if (hPath == null && lPath == null) {
            JFileChooser fc = new JFileChooser();
            JOptionPane.showMessageDialog(Iframe, "Choose Header File!", "Header", JOptionPane.WARNING_MESSAGE);
            int result = fc.showOpenDialog(Iframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                hFile = fc.getSelectedFile();
                readFile(hFile);
                JOptionPane.showMessageDialog(Iframe, "Choose Line File!", "Line", JOptionPane.WARNING_MESSAGE);
                result = fc.showOpenDialog(Iframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lFile = fc.getSelectedFile();
                    readFile(lFile);
                }
            }
           
        }
        else {
        	hFile=new File(hPath);
        	lFile= new File(lPath);
        }

        if (hFile != null && lFile != null) {
            try {
                List<String> hLines = readFile(hFile);
             
                List<String> lLines = readFile(lFile);
              
                System.out.println("check");
                for (String hLine : hLines) {
                    String[] parts = hLine.split(",");
                   
                    Date d = new Date();
                    int num = Integer.parseInt(parts[0]);
                    try{d = Invoice.df.parse(parts[1]);}catch (ParseException pex) {}
                    String name = parts[2];
                    InvoiceModelHeader inv = new InvoiceModelHeader(num, name, d);
                    Iframe.getInvoices().add(inv);
                    }
                Iframe.setTableHeader(new TableHeader(Iframe.getInvoices()));
                for (String lLine : lLines) {
                    
                    String[] parts = lLine.split(",");
                    /*
                        parts = ["1", "Mobile", "3200", "1"]
                    */
                    int invNum = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    int count = Integer.parseInt(parts[3]);
                    InvoiceModelHeader invoice = Iframe.getInvoiceByNum(invNum);
                    InvoiceModelItems item = new InvoiceModelItems(name, price, count, invoice);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(Iframe, "Error while loading files", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<String> readFile(File f) throws IOException {
        List<String> lines = new ArrayList<>();

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    private void save() {
    }
   
    	
    	 public void inputMethodTextChanged(InputMethodEvent event) {
    		 
    	}
     }


	    


