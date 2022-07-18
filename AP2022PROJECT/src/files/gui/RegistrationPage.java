package files.gui;

import files.database.Database;
import files.database.Hashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Enumeration;

public class RegistrationPage extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationPage.class);

    
    public RegistrationPage() {
        initComponents();
    }     
    private void initComponents() {         
        btnGroup = new ButtonGroup();
        panel = new javax.swing.JPanel();
        JLabel headerLabel = new JLabel();
        JLabel firstNameLabel = new JLabel();
        firstNameField = new javax.swing.JTextField();
        JRadioButton customerRadioBtn = new JRadioButton();
        JRadioButton technicianRadioBtn = new JRadioButton();
        JRadioButton representativeRadioBtn = new JRadioButton();
        JLabel lastNameLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        usernameField = new javax.swing.JTextField();
        JLabel passwordLabel = new JLabel();
        passwordField = new javax.swing.JPasswordField();
        lastNameField = new javax.swing.JTextField();
        JButton registerBtn = new JButton();
        JLabel emailLabel = new JLabel();
        emailField = new JTextField();
        JLabel contactNumberLabel = new JLabel();
        contactNumberField = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Registration Page");

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headerLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headerLabel.setForeground(new java.awt.Color(255, 255, 0));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("NEW USER REGISTRATION");

        firstNameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        firstNameLabel.setForeground(new java.awt.Color(255, 255, 0));
        firstNameLabel.setText("First Name:");

        firstNameField.setBackground(new java.awt.Color(255, 255, 255));
        

        customerRadioBtn.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        customerRadioBtn.setForeground(new java.awt.Color(255, 255, 0));
        customerRadioBtn.setText("Customer");
        

        technicianRadioBtn.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        technicianRadioBtn.setForeground(new java.awt.Color(255, 255, 0));
        technicianRadioBtn.setText("Technician");
        

        representativeRadioBtn.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        representativeRadioBtn.setForeground(new java.awt.Color(255, 255, 0));
        representativeRadioBtn.setText("Representative");

        btnGroup.add(customerRadioBtn);
        btnGroup.add(technicianRadioBtn);
        btnGroup.add(representativeRadioBtn);


        lastNameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        lastNameLabel.setForeground(new java.awt.Color(255, 255, 0));
        lastNameLabel.setText("Last Name:");

        usernameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(new java.awt.Color(255, 255, 0));
        usernameLabel.setText("Username:");

        usernameField.setBackground(new java.awt.Color(255, 255, 255));

        passwordLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        passwordLabel.setForeground(new java.awt.Color(255, 255, 0));
        passwordLabel.setText("Password:");

        passwordField.setBackground(new java.awt.Color(255, 255, 255));

        lastNameField.setBackground(new java.awt.Color(255, 255, 255));

        registerBtn.setBackground(new java.awt.Color(255, 255, 0));
        registerBtn.setForeground(new java.awt.Color(0, 0, 0));
        registerBtn.setText("REGISTER");
        registerBtn.addActionListener(evt -> {
            try {
                registerBtnActionPerformed();
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        });

        emailLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        emailLabel.setForeground(new java.awt.Color(255, 255, 0));
        emailLabel.setText("Email Address:");

        emailField.setBackground(new java.awt.Color(255, 255, 255));

        contactNumberLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        contactNumberLabel.setForeground(new java.awt.Color(255, 255, 0));
        contactNumberLabel.setText("Contact Number:");

        contactNumberField.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(contactNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(firstNameField)
                                                                        .addComponent(usernameField)
                                                                        .addComponent(passwordField)
                                                                        .addComponent(lastNameField)
                                                                        .addComponent(emailField)
                                                                        .addComponent(contactNumberField))
                                                                .addGap(122, 122, 122)))
                                                .addContainerGap())
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(customerRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(76, 76, 76)
                                                .addComponent(technicianRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(representativeRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customerRadioBtn)
                                        .addComponent(technicianRadioBtn)
                                        .addComponent(representativeRadioBtn))
                                .addGap(36, 36, 36)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameLabel)
                                        .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastNameLabel)
                                        .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailLabel)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(contactNumberLabel)
                                        .addComponent(contactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameLabel)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addComponent(registerBtn)
                                .addGap(14, 14, 14))
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

    private void registerBtnActionPerformed() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();
        String email = emailField.getText().trim();
        String contact_number = contactNumberField.getText().trim();
        String type = selectedButton();
        String hashedPassword;
        int response;

        if(type == null){
            JOptionPane.showMessageDialog(panel, "User type was not selected.", "No User Type Found", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't select a user type.");
        }else if(firstName.equals("")){
            JOptionPane.showMessageDialog(panel, "First name field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter a first name.");
        }else if(lastName.equals("")){
            JOptionPane.showMessageDialog(panel, "Last name field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter a last name.");
        }else if(email.equals("")){
            JOptionPane.showMessageDialog(panel, "Email address field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter an email address.");
        }else if(contact_number.equals("")){
            JOptionPane.showMessageDialog(panel, "Contact number field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter a contact number.");
        }else if(username.equals("")){
            JOptionPane.showMessageDialog(panel, "Username field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter a username.");
        }else if(password.equals("")){
            JOptionPane.showMessageDialog(panel, "Password field is empty.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A person tried to register but didn't enter a password.");
        }else {
            try {
                hashedPassword = Hashing.hashPassword(password);
                response = Database.registration(firstName, lastName, username, hashedPassword, email, contact_number, type);
                if (response == 1) {
                    JOptionPane.showMessageDialog(panel, "Successful Registration.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    LOGGER.info("A new " + type + " user, " + username + ", was successfully registered.");
                    firstNameField.setText(null);
                    lastNameField.setText(null);
                    usernameField.setText(null);
                    passwordField.setText(null);
                    emailField.setText(null);
                    contactNumberField.setText(null);
                    btnGroup.clearSelection();
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                }
            }catch (SQLException ex){
                LOGGER.fatal("A person tried to register user with the same email address/username already exists.");
                JOptionPane.showMessageDialog(panel, "A user already exists with that email address/username.", "Email Address/Username In Use", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public String selectedButton(){
        for(Enumeration<AbstractButton> buttons = btnGroup.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if(button.isSelected()){
                return button.getText();
            }
        }
        return null;
    }

    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JPanel panel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.ButtonGroup btnGroup;

}
