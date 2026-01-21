package Frame;

import Entity.*;
import java.lang.*;
import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
import java.util.Scanner;

public class AirlineTicketSystem extends JFrame implements ActionListener, MouseListener
{
    JPanel panel;
    
    JLabel userLabel, flightLabel, sourceLabel, destLabel, dateLabel, timeLabel, seatLabel, mealLabel, serviceLabel, remarkLabel;
    
    JTextField userTF, serviceTF, remarkTF;
    
    JComboBox<String> flightCombo, seatCombo, sourceCombo, destCombo, dateCombo, timeCombo;
    
    JRadioButton mealVeg, mealNonVeg, mealNone;
    ButtonGroup mealGroup;
    
    JCheckBox insuranceCheck, luggageCheck;
    
    JButton addButton, removeButton, saveButton, clearButton;
    
    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;

    private Ticket[] tickets;
    private int count;
    private Flight[] flightList;

    public AirlineTicketSystem(Flight[] fList)
    {
        super("Air Ticket Management System");
        this.setSize(1000, 750); 
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.flightList = fList;

        tickets = new Ticket[100]; 
        count = 0;

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 240, 250));

        userLabel = new JLabel("Passenger Name:");
        userLabel.setBounds(30, 30, 120, 30);
        panel.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(150, 30, 150, 30);
        panel.add(userTF);

        flightLabel = new JLabel("Flight:");
        flightLabel.setBounds(330, 30, 100, 30);
        panel.add(flightLabel);

        String[] flightNames = new String[fList.length];
        for(int i=0; i<fList.length; i++) {
            if(fList[i] != null) flightNames[i] = fList[i].toString();
        }
        flightCombo = new JComboBox<>(flightNames);
        flightCombo.setBounds(400, 30, 150, 30);
        panel.add(flightCombo);

        sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(30, 80, 100, 30);
        panel.add(sourceLabel);

        String[] cities = {"Dhaka", "Chittagong", "Sylhet", "Cox's Bazar", "Rajshahi", "Jessore", "Saidpur"};
        sourceCombo = new JComboBox<>(cities);
        sourceCombo.setBounds(150, 80, 150, 30);
        panel.add(sourceCombo);

        destLabel = new JLabel("Destination:");
        destLabel.setBounds(330, 80, 100, 30);
        panel.add(destLabel);

        destCombo = new JComboBox<>(cities);
        destCombo.setSelectedIndex(1); 
        destCombo.setBounds(400, 80, 150, 30);
        panel.add(destCombo);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(30, 130, 120, 30);
        panel.add(dateLabel);

        String[] dates = {"22/01/2026", "23/01/2026", "24/01/2026", "25/01/2026", "26/01/2026"};
        dateCombo = new JComboBox<>(dates);
        dateCombo.setBounds(150, 130, 150, 30);
        panel.add(dateCombo);

        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(330, 130, 100, 30);
        panel.add(timeLabel);

        String[] times = {"08:00 AM", "10:00 AM", "01:00 PM", "04:00 PM", "08:00 PM"};
        timeCombo = new JComboBox<>(times);
        timeCombo.setBounds(400, 130, 150, 30);
        panel.add(timeCombo);

        seatLabel = new JLabel("Seat Type:");
        seatLabel.setBounds(600, 30, 100, 30);
        panel.add(seatLabel);

        String[] seats = {"Economy", "Business"};
        seatCombo = new JComboBox<>(seats);
        seatCombo.setBounds(680, 30, 150, 30);
        panel.add(seatCombo);

        mealLabel = new JLabel("Meal Pref:");
        mealLabel.setBounds(600, 80, 100, 30);
        panel.add(mealLabel);

        mealVeg = new JRadioButton("Veg");
        mealVeg.setBounds(680, 80, 50, 30);
        mealVeg.setBackground(new Color(230, 240, 250));
        
        mealNonVeg = new JRadioButton("Non-Veg");
        mealNonVeg.setBounds(730, 80, 80, 30);
        mealNonVeg.setBackground(new Color(230, 240, 250));
        
        mealNone = new JRadioButton("None");
        mealNone.setBounds(810, 80, 60, 30);
        mealNone.setBackground(new Color(230, 240, 250));
        mealNone.setSelected(true); 

        mealGroup = new ButtonGroup();
        mealGroup.add(mealVeg);
        mealGroup.add(mealNonVeg);
        mealGroup.add(mealNone);

        panel.add(mealVeg);
        panel.add(mealNonVeg);
        panel.add(mealNone);

        serviceLabel = new JLabel("Services:");
        serviceLabel.setBounds(600, 130, 100, 30);
        panel.add(serviceLabel);

        serviceTF = new JTextField();
        serviceTF.setBounds(680, 130, 150, 30);
        panel.add(serviceTF);

        insuranceCheck = new JCheckBox("Travel Insurance");
        insuranceCheck.setBounds(680, 160, 130, 30);
        insuranceCheck.setBackground(new Color(230, 240, 250));
        panel.add(insuranceCheck);

        luggageCheck = new JCheckBox("Extra Luggage");
        luggageCheck.setBounds(810, 160, 120, 30);
        luggageCheck.setBackground(new Color(230, 240, 250));
        panel.add(luggageCheck);

        remarkLabel = new JLabel("Remarks:");
        remarkLabel.setBounds(30, 180, 100, 30);
        panel.add(remarkLabel);

        remarkTF = new JTextField();
        remarkTF.setBounds(150, 180, 400, 30);
        panel.add(remarkTF);

        addButton = new JButton("Add Ticket");
        addButton.setBounds(150, 230, 120, 40);
        addButton.addActionListener(this);
        panel.add(addButton);

        removeButton = new JButton("Remove Selected");
        removeButton.setBounds(290, 230, 150, 40);
        removeButton.addActionListener(this);
        panel.add(removeButton);

        saveButton = new JButton("Save to File");
        saveButton.setBounds(460, 230, 120, 40);
        saveButton.setBackground(Color.ORANGE);
        saveButton.addActionListener(this);
        panel.add(saveButton);
        
        clearButton = new JButton("Clear Fields");
        clearButton.setBounds(600, 230, 120, 40);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        String[] colNames = {"Passenger", "Flight", "Source", "Dest", "Date", "Seat", "Meal", "Services", "Remarks"};
        model = new DefaultTableModel(colNames, 0);
        
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(255, 165, 0)); 
                    c.setForeground(Color.BLACK);
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(240, 240, 240)); 
                    }
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        };
        
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.setRowHeight(25);
        table.addMouseListener(this);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(30, 290, 920, 350);
        panel.add(scroll);

        loadData();
        updateTable();

        this.add(panel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == addButton) {
            String name = userTF.getText();
            String src = sourceCombo.getSelectedItem().toString();
            String dst = destCombo.getSelectedItem().toString();
            String date = dateCombo.getSelectedItem().toString();
            String time = timeCombo.getSelectedItem().toString();
            String seat = seatCombo.getSelectedItem().toString();
            String flight = flightCombo.getSelectedItem().toString();
            
            String meal = "None";
            if(mealVeg.isSelected()) meal = "Veg";
            else if(mealNonVeg.isSelected()) meal = "Non-Veg";
            
            String svcs = serviceTF.getText();
            if(insuranceCheck.isSelected()) {
                if(!svcs.isEmpty()) svcs += ", ";
                svcs += "Insurance";
            }
            if(luggageCheck.isSelected()) {
                if(!svcs.isEmpty()) svcs += ", ";
                svcs += "Luggage";
            }
            
            String rmks = remarkTF.getText();

            if(name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Passenger Name.");
                return;
            }
            if(src.equals(dst)) {
                JOptionPane.showMessageDialog(this, "Source and Destination cannot be the same.");
                return;
            }

            Ticket t;
            if(seat.equals("Business")) {
                t = new BusinessTicket(name, src, dst, date, time, seat, flight, meal, svcs, rmks);
            } else {
                t = new Ticket(name, src, dst, date, time, seat, flight, meal, svcs, rmks);
            }

            if(count < tickets.length) {
                tickets[count] = t;
                count++;
                updateTable(); 
                JOptionPane.showMessageDialog(this, "Ticket Added!");
            } else {
                JOptionPane.showMessageDialog(this, "Storage Full!");
            }
        }
        else if(ae.getSource() == removeButton) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow >= 0) {
                for(int i = selectedRow; i < count - 1; i++) {
                    tickets[i] = tickets[i+1];
                }
                tickets[count-1] = null;
                count--;
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to remove.");
            }
        }
        else if(ae.getSource() == clearButton) {
            clearFields();
        }
        else if(ae.getSource() == saveButton) {
            saveData();
        }
    }

    public void clearFields() {
        userTF.setText("");
        sourceCombo.setSelectedIndex(0);
        destCombo.setSelectedIndex(1);
        mealNone.setSelected(true);
        insuranceCheck.setSelected(false);
        luggageCheck.setSelected(false);
        serviceTF.setText("");
        remarkTF.setText("");
    }

    public void updateTable() {
        model.setRowCount(0); 
        for(int i=0; i<count; i++) {
            if(tickets[i] != null) {
                model.addRow(tickets[i].showDetails());
            }
        }
    }

    public void saveData()
    {
        try 
        {
            File file = new File("Tickets.txt");
            if(!file.exists()) file.createNewFile();
            
            FileWriter fwriter = new FileWriter(file, false); 
            
            for (int i = 0; i < count; i++) {
                if (tickets[i] != null) {
                    fwriter.write(tickets[i].toTxt() + "\n");
                }
            }
            fwriter.close();
            JOptionPane.showMessageDialog(this, "Saved Successfully!");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Saving: " + ioe.getMessage());
        }
    }

    public void loadData()
    {
        try {
            File file = new File("Tickets.txt");
            if (!file.exists()) return; 

            Scanner scanner = new Scanner(file);
            count = 0; 

            while (scanner.hasNextLine() && count < tickets.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length >= 10) {
                    String seat = parts[5].trim();
                    Ticket t;

                    if(seat.equalsIgnoreCase("Business")) {
                        t = new BusinessTicket(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                            parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(), 
                            parts[8].trim(), parts[9].trim()
                        );
                    } else {
                        t = new Ticket(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                            parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(), 
                            parts[8].trim(), parts[9].trim()
                        );
                    }
                    tickets[count] = t;
                    count++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error Loading: " + e.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int selectedRow = table.getSelectedRow();
        if(selectedRow >= 0 && selectedRow < count) {
            Ticket t = tickets[selectedRow];
            userTF.setText(t.getPassengerName());
        }
    }
    
    @Override public void mousePressed(MouseEvent me) {}
    @Override public void mouseReleased(MouseEvent me) {}
    @Override public void mouseEntered(MouseEvent me) {}
    @Override public void mouseExited(MouseEvent me) {}
}