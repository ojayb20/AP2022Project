package files.gui;

import files.database.Database;
import files.database.Hashing;
import files.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class LoginPage extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);
    public LoginPage() {
        initComponents();
    }
    
    private void initComponents() {

        panel = new javax.swing.JPanel();
        JLabel headerLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        usernameField = new javax.swing.JTextField();
        JLabel passwordLabel = new JLabel();
        passwordField = new javax.swing.JPasswordField();
        JButton loginBtn = new JButton();
        JButton registerBtn = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Login Page");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headerLabel.setBackground(new java.awt.Color(255, 255, 255));
        headerLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18)); 
        headerLabel.setForeground(new java.awt.Color(255, 255, 0));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("LOGIN PAGE");

        usernameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(new java.awt.Color(255, 255, 0));
        usernameLabel.setText("Username:");

        usernameField.setBackground(new java.awt.Color(255, 255, 255));

        passwordLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        passwordLabel.setForeground(new java.awt.Color(255, 255, 0));
        passwordLabel.setText("Password:");

        passwordField.setBackground(new java.awt.Color(255, 255, 255));

        loginBtn.setBackground(new java.awt.Color(255, 255, 0));
        loginBtn.setForeground(new java.awt.Color(0, 0, 0));
        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(evt -> {
            try {
                loginBtnActionPerformed();
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        });

        registerBtn.setBackground(new java.awt.Color(255, 255, 0));
        registerBtn.setForeground(new java.awt.Color(0, 0, 0));
        registerBtn.setText("REGISTER");
        registerBtn.addActionListener(this::registerBtnActionPerformed);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(usernameField))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(0, 73, Short.MAX_VALUE)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(passwordField))))
                                .addGap(77, 77, 77))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameLabel)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(registerBtn)
                                        .addComponent(loginBtn))
                                .addGap(26, 26, 26))
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

    private void loginBtnActionPerformed() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(panel, "Username field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A user tried to login without entering their username.");
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(panel, "Password field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A user tried to login without entering their password.");
        }else {
            try(ResultSet resultSet = Database.getLogin(username)) {
                if (resultSet.next()) {
                    if (Hashing.validatePassword(password, resultSet.getString(2))) {
                        JOptionPane.showMessageDialog(panel, "Successful Login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        LOGGER.info("The user " + username + " successfully logged in.");
                        usernameField.setText(null);
                        passwordField.setText(null);
                        dispose();
                        if (resultSet.getString(3).equals("Customer")) {
                            CustomerDashboard customerDashboard = new CustomerDashboard(username);
                            customerDashboard.setVisible(true);
                        } else if (resultSet.getString(3).equals("Technician")) {
                            TechnicianDashboard technicianDashboard = new TechnicianDashboard(username);
                            technicianDashboard.setVisible(true);
                        } else if (resultSet.getString(3).equals("Representative")) {
                            RepresentativeDashboard representativeDashboard = new RepresentativeDashboard(username);
                            representativeDashboard.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Incorrect Username or Password entered.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
                        LOGGER.error("A user tried to login with invalid credentials.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "User does not exist.", "404: User Not Found", JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("A user that isn't registered tried to login.");
                }
            }catch (SQLException e){
                LOGGER.fatal("An SQL error occurred on the Login Page.");
                throw new RuntimeException(e);
            }
        }
    }

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.setVisible(true);
    }


    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    private javax.swing.JPanel panel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
}
