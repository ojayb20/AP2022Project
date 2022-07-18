package files.client;

import files.database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientHandler extends Thread{
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    private String username;
    private PrintStream outputStream = null;
    private final Socket socket;
    private final ClientHandler[] clientHandlers;
    private final int users;

    public ClientHandler(Socket socket, ClientHandler[] clientHandlers) {
        this.socket = socket;
        this.clientHandlers = clientHandlers;
        users = clientHandlers.length;
    }

    public void run() {
        int users = this.users;
        ClientHandler[] clientHandlers = this.clientHandlers;

        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new PrintStream(socket.getOutputStream());
            String currentUsername;
            
            while (true) {
                outputStream.println("Enter your username: ");
                currentUsername = inputStream.readLine().trim();
                ResultSet resultSet = Database.getUsername(currentUsername);
                if (currentUsername.indexOf('@') == -1) {
                    if (resultSet.next()){
                        break;
                    }else {
                        outputStream.println("You do not exist in the system.");
                        LOGGER.warn("An unauthorized user tried to utilize the chat feature.");
                    }
                }else {
                    outputStream.println("The name should not contain '@' character.");
                }
            }

            outputStream.println("Welcome " + currentUsername + ".\nTo leave enter '/leave' in a new line or close the window.");
            LOGGER.info("The user " + currentUsername + " successfully logged on to the chat");
            synchronized (this) {
                for (int i = 0; i < users; i++) {
                    if (clientHandlers[i] != null && clientHandlers[i] == this) {
                        username = "@" + currentUsername;
                        break;
                    }
                }
                for (int i = 0; i < users; i++) {
                    if (clientHandlers[i] != null && clientHandlers[i] != this) {
                        clientHandlers[i].outputStream.println(currentUsername + " entered the chat.");
                    }
                }
            }

            while (true) {
                String message = inputStream.readLine();
                if (message.startsWith("/leave")) {
                    break;
                }

                if (message.startsWith("@")) {
                    String[] text = message.split("\\s", 2);
                    if (text.length > 1 && text[1] != null) {
                        text[1] = text[1].trim();
                        if (!text[1].isEmpty()) {
                            synchronized (this) {
                                for (int i = 0; i < users; i++) {
                                    if (clientHandlers[i] != null && clientHandlers[i] != this && clientHandlers[i].username != null && clientHandlers[i].username.equals(text[0])) {
                                        clientHandlers[i].outputStream.println("[" + username + "]: " + text[1]);

                                        this.outputStream.println("~" + username + "~:: " + text[1]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    synchronized (this) {
                        for (int i = 0; i < users; i++) {
                            if (clientHandlers[i] != null && clientHandlers[i].username != null) {
                                clientHandlers[i].outputStream.println("[" + username + "]: " + message);
                            }
                        }
                    }
                }
            }
            synchronized (this) {
                for (int i = 0; i < users; i++) {
                    if (clientHandlers[i] != null && clientHandlers[i] != this && clientHandlers[i].username != null) {
                        clientHandlers[i].outputStream.println(currentUsername + " left the chat.");
                    }
                }
            }
            LOGGER.info("The user " + currentUsername + " left the chat");
            outputStream.println("You left.");

            synchronized (this) {
                for (int i = 0; i < users; i++) {
                    if (clientHandlers[i] == this) {
                        clientHandlers[i] = null;
                    }
                }
            }

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.fatal("Unable to close connections.");
        } catch (SQLException e) {
            LOGGER.fatal("An SQL error occurred in the ClientHandler class");
            throw new RuntimeException(e);
        }
    }
}

