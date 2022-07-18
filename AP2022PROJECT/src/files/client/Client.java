package files.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class Client {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    public static void main(String[] args) {
        MessageHandler messageHandler = new MessageHandler();
        ClientGUI clientGUI = new ClientGUI(messageHandler);
        clientGUI.setVisible(true);
        clientGUI.setTitle("Query --> " + args[0]);

        try {
            messageHandler.startSocket();
        } catch (IOException ex) {
            LOGGER.warn("Cannot connect to localhost 4444");
            System.out.println("Cannot connect to localhost 4444!");
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
