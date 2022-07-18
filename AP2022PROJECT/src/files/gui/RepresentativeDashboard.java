package files.gui;

import files.client.Client;
import files.database.Database;
import files.regularclass.Complaint;
import files.regularclass.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepresentativeDashboard extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(RepresentativeDashboard.class);
    private final String username;
    public RepresentativeDashboard(String username) throws SQLException {
        initComponents();
        this.username = username;
    }
    
    private void initComponents() throws SQLException{

        JPanel panel = new JPanel();
        JLabel headingLabel = new JLabel();
        JButton viewComplaintBtn = new JButton();
        JButton queryBtn = new JButton();
        JButton assignComplaintBtn = new JButton();
        JButton changePasswordBtn = new JButton();
        JButton logoutBtn = new JButton();
        resolvedComplaints = new javax.swing.JLabel();
        outstandingComplaints = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Representative Dashboard");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("REPRESENTATIVE DASHBOARD");

        viewComplaintBtn.setBackground(new java.awt.Color(255, 255, 0));
        viewComplaintBtn.setForeground(new java.awt.Color(0, 0, 0));
        viewComplaintBtn.setText("View Complaint");
        viewComplaintBtn.addActionListener(this::viewComplaintBtnActionPerformed);

        queryBtn.setBackground(new java.awt.Color(255, 255, 0));
        queryBtn.setForeground(new java.awt.Color(0, 0, 0));
        queryBtn.setText("Resolve Query");
        queryBtn.addActionListener(this::queryBtnActionPerformed);

        assignComplaintBtn.setBackground(new java.awt.Color(255, 255, 0));
        assignComplaintBtn.setForeground(new java.awt.Color(0, 0, 0));
        assignComplaintBtn.setText("Assign Complaint");
        assignComplaintBtn.addActionListener(this::assignComplaintBtnActionPerformed);

        changePasswordBtn.setBackground(new java.awt.Color(255, 255, 0));
        changePasswordBtn.setForeground(new java.awt.Color(0, 0, 0));
        changePasswordBtn.setText("Change Password");
        changePasswordBtn.addActionListener(this::changePasswordBtnActionPerformed);

        logoutBtn.setBackground(new java.awt.Color(255, 255, 0));
        logoutBtn.setForeground(new java.awt.Color(0, 0, 0));
        logoutBtn.setText("Log Out");
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        resolvedComplaints.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        resolvedComplaints.setForeground(new java.awt.Color(255, 255, 0));

        outstandingComplaints.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        outstandingComplaints.setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(215, 215, 215))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(viewComplaintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(queryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(assignComplaintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(changePasswordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(resolvedComplaints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(outstandingComplaints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(viewComplaintBtn)
                                        .addComponent(queryBtn))
                                .addGap(27, 27, 27)
                                .addComponent(assignComplaintBtn)
                                .addGap(55, 55, 55)
                                .addComponent(changePasswordBtn)
                                .addGap(26, 26, 26)
                                .addComponent(resolvedComplaints)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(outstandingComplaints)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(logoutBtn)
                                .addGap(20, 20, 20))
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
        showComplaintsStatistics();
    }

    private void viewComplaintBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Complaint> complaints = new ArrayList<>();
        String assigner;
        String assignee;
        String response;
        String responseDate;
        String resolved;

        try(ResultSet resultSet = Database.getAllComplaints()){
            while (resultSet.next()){
                if (resultSet.getString(9) == null){
                    assigner = "Complaint not assigned";
                }else{
                    assigner = resultSet.getString(9);
                }

                if (resultSet.getString(10) == null){
                    assignee = "Complaint not assigned";
                }else{
                    assignee = resultSet.getString(10);
                }

                if (resultSet.getString(11) == null){
                    response = "Complaint not assigned";
                } else {
                    response = resultSet.getString(11);
                }

                if (resultSet.getString(12) == null){
                    responseDate = "Complaint not assigned";
                }else{
                    responseDate = resultSet.getString(12);
                }

                if (resultSet.getString(13) == null){
                    resolved = "No";
                }
                else{
                    resolved = resultSet.getString(13);
                }
                complaints.add(new Complaint(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), assigner, assignee, response, responseDate, resolved));
            }
            StaffViewComplaints staffViewComplaints = new StaffViewComplaints(complaints);
            staffViewComplaints.setVisible(true);
        } catch (SQLException e) {
            LOGGER.fatal("An SQL error occurred on the Representative Dashboard.");
            throw new RuntimeException(e);
        }
    }

    private void queryBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String [] arguments1 = new String[]{"Representative"};
        new Client().main(arguments1);
    }

    private void assignComplaintBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Complaint> complaints = new ArrayList<>();
        ArrayList<User> technicians = new ArrayList<>();
        String assigner = "Complaint not assigned";
        String assignee = "Complaint not assigned";
        String response = "Complaint not assigned";
        String responseDate;
        String resolved = "Complaint not assigned";

        try(ResultSet resultSet = Database.getAllComplaints()){
            while (resultSet.next()){
                if ((resultSet.getString(13) != null && resultSet.getString(11) != null) || (resultSet.getString(10) != null && resultSet.getString(9) != null)){
                    continue;
                }

                if (resultSet.getString(12) == null){
                    responseDate = "Complaint not assigned";
                }else{
                    responseDate = resultSet.getString(12);
                }
                complaints.add(new Complaint(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), assigner, assignee, response, responseDate, resolved));
            }

            try(ResultSet resultSet1 = Database.getTechnicians()){
                while (resultSet1.next()){
                    technicians.add(new User(resultSet1.getString(1)));
                }
                AssignTechnician assignTechnician = new AssignTechnician(complaints, technicians, this.username);
                assignTechnician.setVisible(true);
            } catch (SQLException e) {
                LOGGER.fatal("An SQL error occurred on the Representative Dashboard.");
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            LOGGER.fatal("An SQL error occurred on the Representative Dashboard.");
            throw new RuntimeException(e);
        }
    }

    private void changePasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ChangePassword changePassword = new ChangePassword(this.username);
        changePassword.setVisible(true);
    }

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }

    public void showComplaintsStatistics() throws SQLException {
        outstandingComplaints.setText("Outstanding Complaints: " + Database.getAmountOfOutstandingComplaints());
        resolvedComplaints.setText("Resolved Complaints: " + Database.getAmountOfResolvedComplaints());
    }

    private javax.swing.JLabel outstandingComplaints;
    private javax.swing.JLabel resolvedComplaints;
}
