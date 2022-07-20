package files.gui;

import files.client.Client;
import files.database.Database;
import files.classes.Complaint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TechnicianDashboard extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(TechnicianDashboard.class);
    private final String username;
    public TechnicianDashboard(String username) {
        initComponents();
        this.username = username;
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        JLabel headingLabel = new JLabel();
        JButton complaintBtn = new JButton();
        JButton queryBtn = new JButton();
        JButton changePasswordBtn = new JButton();
        JButton logoutBtn = new JButton();
        JButton closeComplaintBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Technician Dashboard");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("TECHNICIAN DASHBOARD");

        complaintBtn.setBackground(new java.awt.Color(255, 255, 0));
        complaintBtn.setForeground(new java.awt.Color(0, 0, 0));
        complaintBtn.setText("Make Response");
        complaintBtn.addActionListener(this::complaintBtnActionPerformed);

        queryBtn.setBackground(new java.awt.Color(255, 255, 0));
        queryBtn.setForeground(new java.awt.Color(0, 0, 0));
        queryBtn.setText("Resolve Query");
        queryBtn.addActionListener(this::queryBtnActionPerformed);

        changePasswordBtn.setBackground(new java.awt.Color(255, 255, 0));
        changePasswordBtn.setForeground(new java.awt.Color(0, 0, 0));
        changePasswordBtn.setText("Change Password");
        changePasswordBtn.addActionListener(this::changePasswordBtnActionPerformed);

        logoutBtn.setBackground(new java.awt.Color(255, 255, 0));
        logoutBtn.setForeground(new java.awt.Color(0, 0, 0));
        logoutBtn.setText("Log Out");
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        closeComplaintBtn.setBackground(new java.awt.Color(255, 255, 0));
        closeComplaintBtn.setForeground(new java.awt.Color(0, 0, 0));
        closeComplaintBtn.setText("Close Complaint");
        closeComplaintBtn.addActionListener(this::closeComplaintBtnActionPerformed);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(complaintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(queryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                                .addComponent(changePasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(closeComplaintBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(complaintBtn)
                                .addGap(35, 35, 35)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(queryBtn)
                                        .addComponent(changePasswordBtn))
                                .addGap(41, 41, 41)
                                .addComponent(closeComplaintBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(logoutBtn)
                                .addGap(23, 23, 23))
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
    }

    private void complaintBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Complaint> complaints = new ArrayList<>();
        String assigner;
        String assignee;
        String response = "Complaint not assigned";
        String responseDate;
        String resolved = "Complaint not assigned";

        try(ResultSet resultSet = Database.getComplaintsForTechnician(this.username)){
            while (resultSet.next()){
                if ((resultSet.getString(13) != null && resultSet.getString(11) != null)){
                    continue;
                }

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

                if (resultSet.getString(12) == null){
                    responseDate = "Complaint not assigned";
                }else{
                    responseDate = resultSet.getString(12);
                }

                complaints.add(new Complaint(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), assigner, assignee, response, responseDate, resolved));
            }
            MakeResponse makeResponse = new MakeResponse(complaints);
            makeResponse.setVisible(true);
        } catch (SQLException e) {
            LOGGER.fatal("An SQL error occurred on the Technician Dashboard.");
            throw new RuntimeException(e);
        }
    }

    private void queryBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String [] arguments1 = new String[]{"Technician"};
        new Client().main(arguments1);
    }

    private void changePasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ChangePassword changePassword = new ChangePassword(this.username);
        changePassword.setVisible(true);
    }
    
    public void closeComplaintBtnActionPerformed(java.awt.event.ActionEvent evt){
        ArrayList<Complaint> complaints = new ArrayList<>();
        String resolved = "No resolved";

        try(ResultSet resultSet = Database.getComplaintsForTechnician(this.username)){
            while (resultSet.next()){
                if (resultSet.getString(13) == null && (resultSet.getString(11) != null && resultSet.getString(12) != null)){
                    complaints.add(new Complaint(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resolved));
                }
            }
            CloseComplaint closeComplaint = new CloseComplaint(complaints);
            closeComplaint.setVisible(true);
        } catch (SQLException e) {
            LOGGER.fatal("An SQL error occurred on the Technician Dashboard.");
            throw new RuntimeException(e);
        }
    }

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
