package InvoiceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
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

    private void load(String hPath, String lPath) throws IOException {
        File hFile = null;
        File lFile = null;
        if (hPath == null && lPath == null) {
            JFileChooser fc = new JFileChooser();
            JOptionPane.showMessageDialog(null, "Choose Header File!", "Header", JOptionPane.WARNING_MESSAGE);
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                hFile = fc.getSelectedFile();
                readFile(hFile);
                JOptionPane.showMessageDialog(null, "Choose Line File!", "Line", JOptionPane.WARNING_MESSAGE);
                result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lFile = fc.getSelectedFile();
                    readFile(lFile);
                }
            }
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
                    InvoiceModel inv = new InvoiceModel(num, name, d);
                    
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while loading files", "Error", JOptionPane.ERROR_MESSAGE);
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


	    


