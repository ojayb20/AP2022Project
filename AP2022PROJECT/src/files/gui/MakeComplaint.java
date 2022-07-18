package files.gui;

import files.database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeComplaint extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MakeComplaint.class);
    private final String username;
    public MakeComplaint(String username) {
        this.username = username;
        initComponents();
    }
    
    private void initComponents() {
        panel = new javax.swing.JPanel();
        JLabel headingLabel = new JLabel();
        JButton saveBtn = new JButton();
        JScrollPane complaintContainer = new JScrollPane();
        complaintArea = new javax.swing.JTextArea();
        complaintType = new javax.swing.JComboBox<>();
        JLabel typeOfComplaintLabel = new JLabel();
        JLabel complaintLabel = new JLabel();
        JLabel emailAddressLabel = new JLabel();
        emailAddressField = new javax.swing.JTextField();
        JLabel firstNameLabel = new JLabel();
        JLabel lastNameLabel = new JLabel();
        JLabel contactNumberLabel = new JLabel();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        contactNumberField = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Make Complaint");
        setPreferredSize(new java.awt.Dimension(664, 684));
        setResizable(false);

        panel.setBackground(new java.awt.Color(4, 75, 240));

        headingLabel.setFont(new java.awt.Font("Arial Black", Font.BOLD, 18));
        headingLabel.setForeground(new java.awt.Color(255, 255, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("MAKE COMPLAINT");

        saveBtn.setBackground(new java.awt.Color(255, 255, 0));
        saveBtn.setForeground(new java.awt.Color(0, 0, 0));
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(evt -> {
            try {
                saveBtnActionPerformed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        complaintArea.setColumns(20);
        complaintArea.setRows(5);
        complaintContainer.setViewportView(complaintArea);

        complaintType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Cable", "Internet", "Mobile", "Landline" }));

        typeOfComplaintLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18)); 
        typeOfComplaintLabel.setForeground(new java.awt.Color(255, 255, 0));
        typeOfComplaintLabel.setText("Type of Complaint:");

        complaintLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        complaintLabel.setForeground(new java.awt.Color(255, 255, 0));
        complaintLabel.setText("Enter complaint below:");

        emailAddressLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        emailAddressLabel.setForeground(new java.awt.Color(255, 255, 0));
        emailAddressLabel.setText("Email Address: ");

        emailAddressField.setBackground(new java.awt.Color(255, 255, 255));
        emailAddressField.setEditable(false);

        firstNameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        firstNameLabel.setForeground(new java.awt.Color(255, 255, 0));
        firstNameLabel.setText("First Name: ");

        lastNameLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        lastNameLabel.setForeground(new java.awt.Color(255, 255, 0));
        lastNameLabel.setText("Last Name: ");

        contactNumberLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        contactNumberLabel.setForeground(new java.awt.Color(255, 255, 0));
        contactNumberLabel.setText("Contact Number:");

        firstNameField.setBackground(new java.awt.Color(255, 255, 255));
        firstNameField.setEditable(false);

        lastNameField.setBackground(new java.awt.Color(255, 255, 255));
        lastNameField.setEditable(false);

        contactNumberField.setBackground(new java.awt.Color(255, 255, 255));
        contactNumberField.setEditable(false);

        try(ResultSet resultSet = Database.getCustomerInformation(username)) {
            if (resultSet.next()){
                firstNameField.setText(resultSet.getString(1));
                lastNameField.setText(resultSet.getString(2));
                emailAddressField.setText(resultSet.getString(3));
                contactNumberField.setText(resultSet.getString(4));
            }
        }catch (SQLException e){
            LOGGER.fatal("Unable to retrieve information from database for customer complaint.");
            throw new RuntimeException(e);
        }

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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(238, 238, 238))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(complaintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(emailAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(162, 162, 162)
                                                                .addComponent(emailAddressField))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(162, 162, 162)
                                                                .addComponent(firstNameField))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                                .addComponent(typeOfComplaintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                                                .addComponent(complaintType, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                                .addComponent(contactNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(141, 141, 141)
                                                                .addComponent(contactNumberField))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(162, 162, 162)
                                                                .addComponent(lastNameField)))
                                                .addGap(77, 77, 77))))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(complaintType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeOfComplaintLabel))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(firstNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lastNameLabel)
                                        .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(emailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailAddressLabel))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(contactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(contactNumberLabel))
                                .addGap(73, 73, 73)
                                .addComponent(complaintLabel)
                                .addGap(18, 18, 18)
                                .addComponent(complaintContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveBtn)
                                .addGap(11, 11, 11))
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

    private void saveBtnActionPerformed() throws SQLException {
        String type = (String) complaintType.getSelectedItem();
        String complaint = complaintArea.getText().trim();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailAddressField.getText();
        String contactNumber = contactNumberField.getText();
        int response;
        assert type != null;
        if (type.equals("")){
            JOptionPane.showMessageDialog(panel, "Please select a type of complaint.", "No Complaint Type", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A customer tried to make a complaint without choosing a complaint type.");
        }else if (complaint.equals("")){
            JOptionPane.showMessageDialog(panel, "No complaint was entered.", "Empty Complaint Area", JOptionPane.ERROR_MESSAGE);
            LOGGER.warn("A customer tried to make a complaint without entering a complaint.");
        }else {
            response = Database.registerComplaint(username, firstName, lastName, email, contactNumber, type, complaint);
            if (response == 1) {
                JOptionPane.showMessageDialog(panel, "Complain registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                LOGGER.info("The user was able to successfully register a complaint.");
                complaintType.setSelectedIndex(0);
                complaintArea.setText(null);
            } else {
                JOptionPane.showMessageDialog(panel, "There was an error while registering your complaint. Please try again later.", "Complaint Not Registered", JOptionPane.ERROR_MESSAGE);
                LOGGER.error("The complaint was unable to be registered due to an SQL error.");
            }
        }
    }

    private javax.swing.JTextArea complaintArea;
    private javax.swing.JComboBox<String> complaintType;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.JTextField emailAddressField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JPanel panel;
}
