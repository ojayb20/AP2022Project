package files.gui;

import files.classes.Complaint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StaffViewComplaints extends JFrame {
    ArrayList<Complaint> complaints;
    public StaffViewComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        JLabel headingLabel = new JLabel();
        JScrollPane complaintContainer = new JScrollPane();
        complaintArea = new javax.swing.JTextArea();
        complaintID = new javax.swing.JComboBox<>();
        JLabel complaintIDLabel = new JLabel();
        JLabel typeOfComplaintLabel = new JLabel();
        complaintType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("View Complaints");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("VIEW COMPLAINTS");

        complaintArea.setColumns(20);
        complaintArea.setRows(5);
        complaintContainer.setViewportView(complaintArea);
        complaintArea.setEditable(false);

        complaintID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        complaintID.addItemListener(this::complaintIDItemStateChanged);

        complaintIDLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintIDLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintIDLabel.setText("Complaint ID:");

        typeOfComplaintLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        typeOfComplaintLabel.setForeground(new java.awt.Color(255, 255, 0));
        typeOfComplaintLabel.setText("Complaint Type:");

        complaintType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "All", "Cable", "Internet", "Mobile", "Landline" }));
        complaintType.addItemListener(this::complaintTypeItemStateChanged);

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
                                        .addComponent(typeOfComplaintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(complaintID, 0, 296, Short.MAX_VALUE)
                                        .addComponent(complaintType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(77, 77, 77))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(typeOfComplaintLabel)
                                        .addComponent(complaintType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(complaintID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(complaintIDLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(complaintContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
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
        String IDSelection = (String) complaintID.getSelectedItem();
        String typeSelection = (String) complaintType.getSelectedItem();
        if ("".equals(typeSelection)){
            if ("".equals(IDSelection)){
                complaintArea.setText(null);
            }else{
                customerComplaints = idComplaints(IDSelection);
            }
        }else {
            if ("".equals(IDSelection)){
                complaintArea.setText(complaintArea.getText());
            }else{
                customerComplaints = idComplaints(IDSelection);
            }
        }
        assert false;
        complaintArea.setText(customerComplaints.toString());
    }

    private void complaintTypeItemStateChanged(java.awt.event.ItemEvent evt) {
        StringBuilder customerComplaints = new StringBuilder();
        String typeSelection = (String) complaintType.getSelectedItem();

        if ("".equals(typeSelection)){
            complaintArea.setText(null);
        }else if ("All".equals(typeSelection)){
            customerComplaints = complaints(1, null);
        }else{
            customerComplaints = complaints(2, typeSelection);
        }
        assert false;
        complaintArea.setText(customerComplaints.toString());
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

    public StringBuilder idComplaints(String selection){
        StringBuilder customerComplaints = new StringBuilder();
        int idx = findIndex(selection);
        Complaint complaint = this.complaints.get(idx);
        assert false;
        customerComplaints.append("Complaint ID: ").append(complaint.getComplaintID()).append("\n").append("Username: ").append(complaint.getUsername()).append("\n").append("First Name: ").append(complaint.getFirstName()).append("\n").append("Last Name: ").append(complaint.getLastName()).append("\n").append("Email Address: ").append(complaint.getEmail()).append("\n").append("Contact Number: ").append(complaint.getContactNumber()).append("\n").append("Complaint Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Assigner: ").append(complaint.getAssigner()).append("\n").append("Assignee: ").append(complaint.getAssignee()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Resolved: ").append(complaint.getResolved()).append("\n\n");

        return customerComplaints;
    }

    public StringBuilder complaints(int choice, String selection) {
        StringBuilder customerComplaints = new StringBuilder();
        if (choice == 1){
            complaintID.removeAllItems();
            complaintID.addItem("");
            for (Complaint complaint: this.complaints){
                assert false;
                customerComplaints.append("Complaint ID: ").append(complaint.getComplaintID()).append("\n").append("Username: ").append(complaint.getUsername()).append("\n").append("First Name: ").append(complaint.getFirstName()).append("\n").append("Last Name: ").append(complaint.getLastName()).append("\n").append("Email Address: ").append(complaint.getEmail()).append("\n").append("Contact Number: ").append(complaint.getContactNumber()).append("\n").append("Complaint Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Assigner: ").append(complaint.getAssigner()).append("\n").append("Assignee: ").append(complaint.getAssignee()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Resolved: ").append(complaint.getResolved()).append("\n\n");
            }
            populateComboBox();
        }else{
            complaintID.removeAllItems();
            complaintID.addItem("");
            for (Complaint complaint: this.complaints){
                if (complaint.getType().equals(selection)){
                    assert false;
                    customerComplaints.append("Complaint ID: ").append(complaint.getComplaintID()).append("\n").append("Username: ").append(complaint.getUsername()).append("\n").append("First Name: ").append(complaint.getFirstName()).append("\n").append("Last Name: ").append(complaint.getLastName()).append("\n").append("Email Address: ").append(complaint.getEmail()).append("\n").append("Contact Number: ").append(complaint.getContactNumber()).append("\n").append("Complaint Type: ").append(complaint.getType()).append("\n").append("Complaint: ").append(complaint.getComplaint()).append("\n").append("Assigner: ").append(complaint.getAssigner()).append("\n").append("Assignee: ").append(complaint.getAssignee()).append("\n").append("Response: ").append(complaint.getResponse()).append("\n").append("Response Date: ").append(complaint.getResponseDate()).append("\n").append("Resolved: ").append(complaint.getResolved()).append("\n\n");
                    complaintID.addItem(complaint.getComplaintID());
                }
            }
        }

        return customerComplaints;
    }

    private javax.swing.JTextArea complaintArea;
    private javax.swing.JComboBox<String> complaintID;
    private javax.swing.JComboBox<String> complaintType;
}
