package files.gui;

import files.database.Database;
import files.database.Hashing;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePassword extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(ChangePassword.class);
    String username;
    public ChangePassword(String username) {
        initComponents();
        this.username = username;
    }
    
    private void initComponents() {
        panel = new javax.swing.JPanel();
        JLabel headingLabel = new JLabel();
        JButton changeBtn = new JButton();
        oPasswordField = new javax.swing.JPasswordField();
        JLabel opasswordLabel = new JLabel();
        JLabel nPasswordLabel = new JLabel();
        nPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Password");
        setResizable(false);

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("CHANGE PASSWORD");

        changeBtn.setBackground(new java.awt.Color(255, 255, 0));
        changeBtn.setForeground(new java.awt.Color(0, 0, 0));
        changeBtn.setText("Change");
        changeBtn.addActionListener(evt -> {
            try {
                changeBtnActionPerformed();
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        });

        opasswordLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        opasswordLabel.setForeground(new java.awt.Color(255, 255, 0));
        opasswordLabel.setText("Old Password: ");

        nPasswordLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        nPasswordLabel.setForeground(new java.awt.Color(255, 255, 0));
        nPasswordLabel.setText("New Password: ");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(267, Short.MAX_VALUE)
                                .addComponent(changeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(234, 234, 234))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(opasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(55, 55, 55)
                                                                .addComponent(oPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(nPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(55, 55, 55)
                                                                .addComponent(nPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(opasswordLabel)
                                        .addComponent(oPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nPasswordLabel)
                                        .addComponent(nPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(changeBtn)
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

        panel.getAccessibleContext().setAccessibleName("");

        pack();
    }

    private void changeBtnActionPerformed() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String oldPassword = String.valueOf(oPasswordField.getPassword()).trim();
        String newPassword = String.valueOf(nPasswordField.getPassword()).trim();
        String hashedPassword;
        int response;

        if (oldPassword.equals("")) {
            JOptionPane.showMessageDialog(panel, "Old Password field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("The user " + this.username + "tried to update password without entering their old password");
        } else if (newPassword.equals("")) {
            JOptionPane.showMessageDialog(panel, "New Password field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("The user " + this.username + "tried to update password without entering their new password");
        } else {
            try(ResultSet resultSet = Database.getLogin(this.username)) {
                if (resultSet.next()) {
                    if (Hashing.validatePassword(oldPassword, resultSet.getString(2))) {
                        if (Hashing.validatePassword(newPassword, resultSet.getString(2))){
                            JOptionPane.showMessageDialog(panel, "New password cannot be the same as the old password.", "Duplicate Password", JOptionPane.ERROR_MESSAGE);
                            LOGGER.warn("The user " + this.username + "tried to update password with the same password");
                        }else {
                            hashedPassword = Hashing.hashPassword(newPassword);
                            response = Database.updatePassword(this.username, hashedPassword);
                            if (response == 1) {
                                JOptionPane.showMessageDialog(panel, "Password Updated Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                LOGGER.info("The user " + this.username + " has updated their password.");
                                oPasswordField.setText(null);
                                nPasswordField.setText(null);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(panel, "Password could not be updated successfully.", "Error", JOptionPane.ERROR_MESSAGE);
                                LOGGER.error("The user " + this.username + " was unable to update their password in the database due to some SQL issue.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Incorrect Password entered.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
                        LOGGER.error("The user " + this.username + " was unable to update their password in the database due to them entering the incorrect old password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "User does not exist.", "404: User Not Found", JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("The user " + this.username + " was unable to update their password in the database as they do not exist in the database.");
                }
            }catch (SQLException e){
                LOGGER.fatal("An SQL error occurred on the Change Password Page.");
                throw new RuntimeException(e);
            }
        }
    }

//    public static void main(String[] args) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
//                 UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//    }

    private javax.swing.JPasswordField nPasswordField;
    private javax.swing.JPasswordField oPasswordField;
    private javax.swing.JPanel panel;
}
