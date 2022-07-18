package files.gui;

import files.regularclass.Complaint;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewComplaintsForCustomer extends JFrame {
    ArrayList<Complaint> complaints;
    public ViewComplaintsForCustomer(ArrayList<Complaint> complaints) {
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

        complaintID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "All" }));
        complaintID.addItemListener(evt -> {
            try {
                complaintIDItemStateChanged();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        complaintIDLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintIDLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintIDLabel.setText("Complaint ID:");

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
                                .addComponent(complaintIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(complaintID, 0, 298, Short.MAX_VALUE)
                                .addGap(77, 77, 77))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(complaintID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(complaintIDLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(complaintContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
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

    private void complaintIDItemStateChanged() throws SQLException {
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

    public StringBuilder complaints(int choice, String selection) {
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
    private javax.swing.JComboBox<String> complaintID;
}
