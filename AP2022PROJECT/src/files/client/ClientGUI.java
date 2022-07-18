package files.client;

import files.client.MessageHandler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class ClientGUI extends JFrame implements Observer {
    private final MessageHandler messageHandler;

    public ClientGUI(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        messageHandler.addObserver(this);
        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        msgArea = new JTextArea();
        msgField = new JTextField();
        JButton sendBtn = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel.setBackground(new java.awt.Color(4, 75, 240));

        msgArea.setColumns(20);
        msgArea.setRows(10);
        jScrollPane1.setViewportView(msgArea);
        msgArea.setEditable(false);

        sendBtn.setBackground(new java.awt.Color(255, 255, 0));
        sendBtn.setForeground(new java.awt.Color(0, 0, 0));
        sendBtn.setText("SEND");
        sendBtn.addActionListener(this::sendBtnActionPerformed);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                messageHandler.close();
            }
        });

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(431, Short.MAX_VALUE)
                                .addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(msgField, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(117, Short.MAX_VALUE))
                                .addGroup(panelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                                        .addContainerGap()))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(308, Short.MAX_VALUE)
                                .addComponent(sendBtn)
                                .addGap(23, 23, 23))
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                                        .addComponent(msgField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String message = msgField.getText().trim();
        if (message.length() > 0) {
            messageHandler.sendMessage(message);
            msgField.selectAll();
            msgField.requestFocus();
            msgField.setText("");
        }
    }

    public void update(Observable o, Object arg) {
        final Object finalArg = arg;
        SwingUtilities.invokeLater(() -> {
            msgArea.append(finalArg.toString());
            msgArea.append("\n");
        });
    }

    private JTextArea msgArea;
    private JTextField msgField;
}
