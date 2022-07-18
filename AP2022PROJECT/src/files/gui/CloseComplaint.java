package files.gui;

import files.database.Database;
import files.regularclass.Complaint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CloseComplaint extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(CloseComplaint.class);
    ArrayList<Complaint> complaints;
    public CloseComplaint(ArrayList<Complaint> complaints) {
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
        JButton resolveBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Close Complaint");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("CLOSE COMPLAINT");

        complaintArea.setColumns(20);
        complaintArea.setRows(5);
        complaintContainer.setViewportView(complaintArea);

        complaintID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        complaintID.addItemListener(this::complaintIDItemStateChanged);

        complaintIDLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintIDLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintIDLabel.setText("Complaint ID:");

        resolveBtn.setBackground(new java.awt.Color(255, 255, 0));
        resolveBtn.setForeground(new java.awt.Color(0, 0, 0));
        resolveBtn.setText("Resolve");
        resolveBtn.addActionListener(evt -> {
            try {
                resolveBtnActionPerformed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(276, 276, 276)
                                .addComponent(resolveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(346, Short.MAX_VALUE))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(complaintIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(117, 117, 117)
                                                .addComponent(complaintID, 0, 354, Short.MAX_VALUE)
                                                .addGap(77, 77, 77))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(complaintContainer, javax.swing.GroupLayout.Alignment.TRAILING))
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
                                .addGap(24, 24, 24)
                                .addComponent(complaintContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(resolveBtn)
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

    private void resolveBtnActionPerformed() throws SQLException {
        String ID = (String) complaintID.getSelectedItem();
        int response;
        if ("".equals(ID)) {
            JOptionPane.showMessageDialog(panel, "Select a Complaint.", "No Complaint", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A technician tried to close a complaint but didn't select a complaint.");
        }else{
            response = Database.updateResolved(ID);
            if (response == 1) {
                JOptionPane.showMessageDialog(panel, "Complaint closed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                LOGGER.info("A technician successfully closed a customer's complaint.");
                complaintID.removeItemAt(complaintID.getSelectedIndex());
                complaintID.setSelectedIndex(0);
                complaintArea.setText(null);
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
    private javax.swing.JComboBox<String> complaintID;
    private javax.swing.JPanel panel;
}
