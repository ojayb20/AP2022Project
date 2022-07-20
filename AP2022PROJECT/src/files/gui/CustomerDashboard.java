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

public class CustomerDashboard extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDashboard.class);
    private final String username;
    public CustomerDashboard(String username) {
        this.username = username;
        initComponents();
    }
    private void initComponents() {

        JPanel panel = new JPanel();
        JLabel headingLabel = new JLabel();
        JButton complaintBtn = new JButton();
        JButton queryBtn = new JButton();
        JButton changePasswordBtn = new JButton();
        JButton logoutBtn = new JButton();
        JButton viewAllComplaintsBtn = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Customer Dashboard");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18)); 
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("CUSTOMER DASHBOARD");

        complaintBtn.setBackground(new java.awt.Color(255, 255, 0));
        complaintBtn.setForeground(new java.awt.Color(0, 0, 0));
        complaintBtn.setText("Register Complaint");
        complaintBtn.addActionListener(this::complaintBtnActionPerformed);

        queryBtn.setBackground(new java.awt.Color(255, 255, 0));
        queryBtn.setForeground(new java.awt.Color(0, 0, 0));
        queryBtn.setText("Make Query");
        queryBtn.addActionListener(this::queryBtnActionPerformed);

        changePasswordBtn.setBackground(new java.awt.Color(255, 255, 0));
        changePasswordBtn.setForeground(new java.awt.Color(0, 0, 0));
        changePasswordBtn.setText("Change Password");
        changePasswordBtn.addActionListener(this::changePasswordBtnActionPerformed);

        logoutBtn.setBackground(new java.awt.Color(255, 255, 0));
        logoutBtn.setForeground(new java.awt.Color(0, 0, 0));
        logoutBtn.setText("Log Out");
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        viewAllComplaintsBtn.setBackground(new java.awt.Color(255, 255, 0));
        viewAllComplaintsBtn.setForeground(new java.awt.Color(0, 0, 0));
        viewAllComplaintsBtn.setText("View All Complaints");
        viewAllComplaintsBtn.addActionListener(evt -> {
            try {
                viewAllComplaintsBtnActionPerformed();
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
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(complaintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(viewAllComplaintsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(89, 89, 89)
                                                .addComponent(queryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(changePasswordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(245, Short.MAX_VALUE)
                                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(234, 234, 234))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(complaintBtn)
                                        .addComponent(queryBtn)
                                        .addComponent(viewAllComplaintsBtn))
                                .addGap(47, 47, 47)
                                .addComponent(changePasswordBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(logoutBtn)
                                .addGap(36, 36, 36))
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
        MakeComplaint makeComplaint  = new MakeComplaint(username);
        makeComplaint.setVisible(true);
    }

    private void queryBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String [] arguments1 = new String[]{"Customer"};
        new Client().main(arguments1);
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

    private void viewAllComplaintsBtnActionPerformed() throws SQLException {
        ArrayList<Complaint> complaints = new ArrayList<>();
        String response;
        String responseDate;
        String assignee;
        try(ResultSet resultSet = Database.customersComplaints(this.username)){
            while (resultSet.next()){
                if (resultSet.getString(4) == null){
                    response = "No response has been posted yet";
                } else {
                    response = resultSet.getString(4);
                }

                if (resultSet.getString(5) == null){
                    responseDate = "None";
                }else{
                    responseDate = resultSet.getString(5);
                }

                if (resultSet.getString(6) == null){
                    assignee = "No one has been assigned this complaint yet";
                }
                else{
                    assignee = resultSet.getString(6);
                }
                complaints.add(new Complaint(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), response, responseDate, assignee));
            }
            ViewComplaintsForCustomer viewComplaintsForCustomer = new ViewComplaintsForCustomer(complaints);
            viewComplaintsForCustomer.setVisible(true);
        }catch (SQLException e){
            LOGGER.fatal("An SQL error occurred on the Customer Dashboard.");
            throw new RuntimeException(e);
        }
    }
}
