package files.gui;

import files.database.Database;
import files.classes.Complaint;
import files.classes.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignTechnician extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(AssignTechnician.class);
    String username;
    ArrayList<Complaint> complaints;
    ArrayList<User> technicianID;
    public AssignTechnician(ArrayList<Complaint> complaints, ArrayList<User> technicianID, String username) {
        this.complaints = complaints;
        this.technicianID = technicianID;
        this.username = username;
        initComponents();
    }
    
    private void initComponents() {
        panel = new javax.swing.JPanel();
        JLabel headingLabel = new JLabel();
        JScrollPane complaintContainer = new JScrollPane();
        complaintArea = new javax.swing.JTextArea();
        complaintID = new javax.swing.JComboBox<>();
        JLabel complaintIDLabel = new JLabel();
        JLabel technicianLabel = new JLabel();
        technicians = new javax.swing.JComboBox<>();
        JButton assignBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Assign Technician");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18)); 
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("ASSIGN TECHNICIAN");

        complaintArea.setColumns(20);
        complaintArea.setRows(5);
        complaintContainer.setViewportView(complaintArea);
        complaintArea.setEditable(false);

        complaintID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        complaintID.addItemListener(this::complaintIDItemStateChanged);

        complaintIDLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintIDLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintIDLabel.setText("Complaint ID:");

        technicianLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        technicianLabel.setForeground(new java.awt.Color(255, 255, 0));
        technicianLabel.setText("Technician:");

        technicians.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        assignBtn.setBackground(new java.awt.Color(255, 255, 0));
        assignBtn.setForeground(new java.awt.Color(0, 0, 0));
        assignBtn.setText("Assign");
        assignBtn.addActionListener(evt -> {
            try {
                assignBtnActionPerformed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(complaintContainer))
                                .addContainerGap())
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(complaintIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(technicianLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(complaintID, 0, 288, Short.MAX_VALUE)
                                        .addComponent(technicians, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(77, 77, 77))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(276, 276, 276)
                                .addComponent(assignBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(technicianLabel)
                                        .addComponent(technicians, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(complaintID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(complaintIDLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(complaintContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(assignBtn)
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
        }else if ("All".equals(selection)){
            customerComplaints = complaints(1, null);
        }else{
            customerComplaints = complaints(2, selection);
        }
        assert false;
        complaintArea.setText(customerComplaints.toString());
    }

    private void assignBtnActionPerformed() throws SQLException {
        String technician = (String) technicians.getSelectedItem();
        String ID = (String) complaintID.getSelectedItem();
        if ("".equals(technician)){
            JOptionPane.showMessageDialog(panel, "Please select a technician.", "No Selection", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("The Representative User " + this.username + " tried to assign a technician without choosing a technician.");
        }else if ("".equals(ID)){
            JOptionPane.showMessageDialog(panel, "Please select a complaint.", "No Selection", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("The Representative User " + this.username + " tried to assign a technician without choosing a complaint.");
        }else{
            int response = Database.assignTechnician(ID, this.username, technician);
            if (response == 1){
                JOptionPane.showMessageDialog(panel, "Technician successfully assigned.", "Success", JOptionPane.INFORMATION_MESSAGE);
                LOGGER.info("The Representative User " + this.username + " successfully assigned a technician to a complaint.");
                complaintID.removeItemAt(complaintID.getSelectedIndex());
                complaintID.setSelectedIndex(0);
                technicians.setSelectedIndex(0);
                complaintArea.setText(null);
            }else{
                JOptionPane.showMessageDialog(panel, "Unable to assign technician.", "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.error("The Representative User " + this.username + " was unable to assign a technician to a complaint due to some SQL error.");
            }
        }
    }

    public void populateComboBox(){
        for (Complaint complaint : this.complaints) {
            complaintID.addItem(complaint.getComplaintID());
        }

        for (User user: this.technicianID){
            technicians.addItem(user.getUsername());
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
                customerComplaints.append("Complaint ID: ").append(complaint.getComplaintID()).append("\n").append("Username: ").append(complaint.getUsername()).append("\n").append("First Name: ").append(complaint.getFirstName()).append("\n").append("Last Name: ").append(complaint.getLastName()).append("\n").append("Email Address: ").append(complaint.getEmail()).append("\n").append("Contact Number: ").append(complaint.getContactNumber()).append("\n").append("Complaint Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Assigner: ").append(complaint.getAssigner()).append("\n").append("Assignee: ").append(complaint.getAssignee()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Resolved: ").append(complaint.getResolved()).append("\n\n");
            }
        }else{
            int idx = findIndex(selection);
            Complaint complaint = this.complaints.get(idx);
            assert false;
            customerComplaints.append("Complaint ID: ").append(complaint.getComplaintID()).append("\n").append("Username: ").append(complaint.getUsername()).append("\n").append("First Name: ").append(complaint.getFirstName()).append("\n").append("Last Name: ").append(complaint.getLastName()).append("\n").append("Email Address: ").append(complaint.getEmail()).append("\n").append("Contact Number: ").append(complaint.getContactNumber()).append("\n").append("Complaint Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Assigner: ").append(complaint.getAssigner()).append("\n").append("Assignee: ").append(complaint.getAssignee()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Resolved: ").append(complaint.getResolved()).append("\n\n");
        }
        return customerComplaints;
    }

    private javax.swing.JTextArea complaintArea;
    private javax.swing.JComboBox<String> complaintID;
    private javax.swing.JPanel panel;
    private javax.swing.JComboBox<String> technicians;
}
