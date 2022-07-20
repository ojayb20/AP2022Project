package files.gui;

import files.database.Database;
import files.classes.Complaint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class MakeResponse extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MakeResponse.class);
    ArrayList<Complaint> complaints;
    public MakeResponse(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
        initComponents();
    }
    
    private void initComponents() {

        panel = new javax.swing.JPanel();
        JLabel headingLabel = new JLabel();
        JScrollPane complaintContainer = new JScrollPane();
        complaintArea = new javax.swing.JTextArea();
        complaintID = new javax.swing.JComboBox<>();
        JLabel complaintIDLabel = new JLabel();
        JButton respondBtn = new JButton();
        JLabel responseLabel = new JLabel();
        JScrollPane complaintContainer1 = new JScrollPane();
        responseArea = new javax.swing.JTextArea();
        JLabel visitDateLabel = new JLabel();
        visitDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Make Response");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18)); 
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("MAKE RESPONSE");

        complaintArea.setColumns(20);
        complaintArea.setRows(5);
        complaintContainer.setViewportView(complaintArea);
        complaintArea.setEditable(false);

        complaintID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        complaintID.addItemListener(this::complaintIDItemStateChanged);

        complaintIDLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintIDLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintIDLabel.setText("Complaint ID:");

        respondBtn.setBackground(new java.awt.Color(255, 255, 0));
        respondBtn.setForeground(new java.awt.Color(0, 0, 0));
        respondBtn.setText("Respond");
        respondBtn.addActionListener(evt -> {
            try {
                respondBtnActionPerformed();
            } catch (ParseException | SQLException e) {
                throw new RuntimeException(e);
            }
        });

        responseLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        responseLabel.setForeground(new java.awt.Color(255, 255, 0));
        responseLabel.setText("Response:");

        responseArea.setColumns(20);
        responseArea.setRows(5);
        complaintContainer1.setViewportView(responseArea);

        visitDateLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        visitDateLabel.setForeground(new java.awt.Color(255, 255, 0));
        visitDateLabel.setText("Visitation Date: ");

        visitDate.setForeground(new java.awt.Color(255, 255, 0));
        visitDate.setDateFormatString("dd/MM/yyyy");
        visitDate.setMinSelectableDate(new Date());
        visitDate.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(276, 276, 276)
                                                .addComponent(respondBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(responseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(complaintContainer1)))
                                .addContainerGap())
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(complaintIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(117, 117, 117)
                                                .addComponent(complaintID, 0, 317, Short.MAX_VALUE)
                                                .addGap(77, 77, 77))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(complaintContainer, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addContainerGap())
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(visitDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(visitDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(complaintIDLabel)
                                        .addComponent(complaintID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(complaintContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(responseLabel))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(complaintContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                                .addComponent(visitDateLabel))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(visitDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22)
                                .addComponent(respondBtn)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        populateComboBox();
    }

    private void complaintIDItemStateChanged(java.awt.event.ItemEvent evt) {
        StringBuilder customerComplaints = new StringBuilder();
        String selection = (String) complaintID.getSelectedItem();

        if ("".equals(selection)) {
            complaintArea.setText(null);
        }else{
            customerComplaints = complaints(2, selection);
        }
        assert false;
        complaintArea.setText(customerComplaints.toString());
    }

    private void respondBtnActionPerformed() throws ParseException, SQLException {
        String ID = (String) complaintID.getSelectedItem();
        String complaintResponse = responseArea.getText().trim();
        String date = String.valueOf(visitDate.getDate()).trim();
        Date today = new Date();
        LocalDate localDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int response;

        if ("".equals(ID)){
            JOptionPane.showMessageDialog(panel, "Select a Complaint.", "No Complaint", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A technician tried to make a response to a complaint but didn't select a complaint.");
        }else if ("".equals(complaintResponse.trim())){
            JOptionPane.showMessageDialog(panel, "Please enter a response.", "No Response", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A technician tried to make a response to a complaint but didn't type a response.");
        }else if (date.equals("null")){
            JOptionPane.showMessageDialog(panel, "Please enter select a visitation date.", "No Date", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A technician tried to make a response to a complaint but didn't select a visitation date.");
        }else {
            complaintResponse += "\n\n" + "Visitation Date: " + visitDate.getDate();
            try {
                response = Database.updateResponse(ID, complaintResponse, String.valueOf(localDate));
                if (response == 1) {
                    JOptionPane.showMessageDialog(panel, "Response made successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    LOGGER.info("A technician successfully made a response to a customer's complaint.");
                    complaintID.removeItemAt(complaintID.getSelectedIndex());
                    complaintID.setSelectedIndex(0);
                    complaintArea.setText(null);
                    responseArea.setText(null);
                    visitDate.setDate(null);
                } else {
                    JOptionPane.showMessageDialog(panel, "Unable to submit response.", "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("A technician was unable to make a response to a complaint due to some SQL error.");
                }
            }catch (SQLSyntaxErrorException e){
                JOptionPane.showMessageDialog(panel, "Don't put any ' when making the complaint", "Syntax Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.fatal("A technician tried to enter a response with an ' in it.");
            }
        }
    }

    public void populateComboBox(){
        for (Complaint complaint : this.complaints) {
            complaintID.addItem(complaint.getComplaintID());
        }
    }

    public int findIndex(String id){
        for (Complaint complaint : this.complaints){
            if (complaint.getComplaintID().equals(id)){
                return complaints.indexOf(complaint);
            }
        }
        return 0;
    }

    public StringBuilder complaints(int choice, String selection){
        StringBuilder customerComplaints = new StringBuilder();
        if (choice == 1){
            for (Complaint complaint : this.complaints) {
                assert false;
                customerComplaints.append("Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Responder: ").append(complaint.getAssignee()).append("\n\n");
            }
        }else{
            int idx = findIndex(selection);
            Complaint complaint = this.complaints.get(idx);
            assert false;
            customerComplaints.append("Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Responder: ").append(complaint.getAssignee()).append("\n\n");
        }
        return customerComplaints;
    }

    private javax.swing.JTextArea complaintArea;
    private javax.swing.JTextArea responseArea;
    private javax.swing.JComboBox<String> complaintID;
    private javax.swing.JPanel panel;
    private com.toedter.calendar.JDateChooser visitDate;
}
