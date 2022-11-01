/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import invoiceModel.HeaderModel;
import invoiceModel.HeaderTable;
import invoiceModel.ItemModel;
import invoiceModel.ItemTable;
import invoiceView.InvoiceHeaderView;
import invoiceView.InvoiceLineView;
import invoiceView.View;


public class Controller implements ActionListener, ListSelectionListener {

    private View view;
    private InvoiceHeaderView invoiceDialog;
    private InvoiceLineView lineDialog;

    public Controller(View frame) {
        this.view = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Action = e.getActionCommand();

        switch (Action) {
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

            case "Load":
                load(null, null);
                break;

            case "Save":
                save();
                break;

            case "newInvoiceOK":
                newInvoiceOK();
                break;

            case "newInvoiceCancel":
                newInvoiceCancel();
                break;

            case "newLineOK":
                newLineOK();
                break;

            case "newLineCancel":
                newLineCancel();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = view.getInvoicesTable().getSelectedRow();
        System.out.println("Invoice row Selected " + selectedRow);
        if (selectedRow != -1) {
            HeaderTable selectedInv = view.getInvoices().get(selectedRow);
            view.getCustomerNameLbl().setText(selectedInv.getName());
            view.getInvDateLbl().setText(view.df.format(selectedInv.getDate()));
            view.getInvNumLbl().setText("" + selectedInv.getNum());
            view.getInvTotalLbl().setText("" + selectedInv.getTotal());
            view.setItemTableModel(new ItemModel(selectedInv.getItems()));
        } else {
        	view.getCustomerNameLbl().setText("");
        	view.getInvDateLbl().setText("");
        	view.getInvNumLbl().setText("");
        	view.getInvTotalLbl().setText("");
        	view.setItemTableModel(new ItemModel());
        }
    }

    private void newInvoice() {
        invoiceDialog = new InvoiceHeaderView(view);
        invoiceDialog.setVisible(true);
    }
    
    private void newInvoiceOK() {
        String dateStr = invoiceDialog.getInvDateField().getText();
        String name = invoiceDialog.getCustNameField().getText();
        try {
            Date date = view.df.parse(dateStr);
            int num = view.getNextInvNum();
            HeaderTable invoice = new HeaderTable(num, name, date);
            view.getInvoices().add(invoice);
            view.getHeaderTableModel().fireTableDataChanged();
            newInvoiceCancel();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(view, "Error in date expression", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newInvoiceCancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    private void deleteInvoice() {
        int selectedRow = view.getInvoicesTable().getSelectedRow();
        if (selectedRow == -1) {

        } else {
        	view.getInvoices().remove(selectedRow);
        	view.getHeaderTableModel().fireTableDataChanged();
        }
    }

    private void newLine() {
        int selectedRow = view.getInvoicesTable().getSelectedRow();
        if (selectedRow == -1) {

        } else {
            lineDialog = new InvoiceLineView(view);
            lineDialog.setVisible(true);
        }
    }

    private void newLineOK() {
        int selectedRow = view.getInvoicesTable().getSelectedRow();
        if (selectedRow == -1) {
            
        } else {
            String name = lineDialog.getItemNameField().getText();
            String countStr = lineDialog.getItemCountField().getText();
            String priceStr = lineDialog.getItemPriceField().getText();
            try {
                int count = Integer.parseInt(countStr);
                int price = Integer.parseInt(priceStr);
                HeaderTable invoice = view.getInvoices().get(selectedRow);
                ItemTable item = new ItemTable(name, price, count, invoice);
                view.getHeaderTableModel().fireTableDataChanged();
                view.getInvoicesTable().setRowSelectionInterval(selectedRow, selectedRow);
                newLineCancel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Error in number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }

    private void newLineCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void deleteLine() {
        int selectedInvoiceIndex = view.getInvoicesTable().getSelectedRow();
        int selectedItemIndex = view.getLinesTable().getSelectedRow();
        if (selectedInvoiceIndex != -1 && selectedItemIndex != -1) {
        	view.getInvoices().get(selectedInvoiceIndex).getItems().remove(selectedItemIndex);
        	view.getHeaderTableModel().fireTableDataChanged();
        	view.getInvoicesTable().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        } else {
        	JOptionPane.showMessageDialog(view, "Error in Delete Line from line table file", "Error", JOptionPane.ERROR_MESSAGE);
        	System.out.println("this is an error we could not delete line");
            
        }
    }
    
    public void load(String hPath, String lPath) {
        System.out.println("Load File");
        File hFile = null;
        File lFile = null;
        if (hPath == null && lPath == null) {
            JFileChooser fc = new JFileChooser();
            JOptionPane.showMessageDialog(view, "Choose Header File!", "Header", JOptionPane.WARNING_MESSAGE);
            int result = fc.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                hFile = fc.getSelectedFile();
                JOptionPane.showMessageDialog(view, "Choose Line File!", "Line", JOptionPane.WARNING_MESSAGE);
                result = fc.showOpenDialog(view);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lFile = fc.getSelectedFile();
                }
            }
        } else {
            hFile = new File(hPath);
            lFile = new File(lPath);
        }

        if (hFile != null && lFile != null) {
            try {
                List<String> hLines = readFile(hFile);
            
                List<String> lLines = readFile(lFile);
            
                view.getInvoices().clear();
                for (String hLine : hLines) {
                    
                    String[] parts = hLine.split(",");
                    if (parts.length < 3) {
                        JOptionPane.showMessageDialog(view, "Error in header format: " + hLine, "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                   
                    Date d = new Date();
                    int num = Integer.parseInt(parts[0]);
                    try {
                        d = View.df.parse(parts[1]);
                    } catch (ParseException pex) {
                        
                    }
                    String name = parts[2];
                    HeaderTable inv = new HeaderTable(num, name, d);
                    view.getInvoices().add(inv);
                }
                view.setHeaderTableModel(new HeaderModel(view.getInvoices()));

                for (String lLine : lLines) {
                   
                    String[] parts = lLine.split(",");
                    if (parts.length < 4) {
                        JOptionPane.showMessageDialog(view, "Error in lines format: " + lLine, "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                    
                    int invNum = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    int count = Integer.parseInt(parts[3]);
                    HeaderTable invoice = view.getInvoiceByNum(invNum);
                    if (invoice != null) {
                        ItemTable item = new ItemTable(name, price, count, invoice);
                    }
                }
                System.out.println("Check");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error while loading files: \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        JFileChooser fc = new JFileChooser();
        File hFile = null;
        File lFile = null;
        JOptionPane.showMessageDialog(view, "Select Header File", "Header File", JOptionPane.QUESTION_MESSAGE);
        int result = fc.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            hFile = fc.getSelectedFile();
            JOptionPane.showMessageDialog(view, "Select Line File", "Line File", JOptionPane.QUESTION_MESSAGE);
            result = fc.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                lFile = fc.getSelectedFile();
            }
        }
        
        if (hFile != null && lFile != null) {
            String hData = "";
            String lData = "";
            for (HeaderTable header : view.getInvoices()) {
                hData += header.getAsCSV();
                hData += "\n";
                
                for (ItemTable item : header.getItems()) {
                    lData += item.getAsCSV();
                    lData += "\n";
                }
            }
            System.out.println("Check");
            try {
                FileWriter hfw = new FileWriter(hFile);
                FileWriter lfw = new FileWriter(lFile);
                hfw.write(hData);
                lfw.write(lData);
                hfw.flush();
                lfw.flush();
                hfw.close();
                lfw.close();  
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error while writing files: \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    

}
