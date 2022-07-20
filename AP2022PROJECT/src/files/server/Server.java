package files.server;
import files.client.ClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static ServerSocket serverSocket = null;

    private static final int users = 3;
    private static final ClientHandler[] clientHandlers = new ClientHandler[users];


    public static void main(String[] args) {

        int portNumber = 1234;

        System.out.println("Server started.\n"
                + "Now using port number=" + portNumber);


        try {
            serverSocket = new ServerSocket(portNumber);
            LOGGER.info("Server connected to port 1234");
        } catch (IOException e) {
            LOGGER.fatal("An error occurred while trying to connect to the server.");
            e.printStackTrace();
        }
        
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                int i;
                for (i = 0; i < users; i++) {
                    if (clientHandlers[i] == null) {
                        (clientHandlers[i] = new ClientHandler(socket, clientHandlers)).start();
                        break;
                    }
                }
                if (i == users) {
                    PrintStream printStream = new PrintStream(socket.getOutputStream());
                    printStream.println("Chat is currently in use. Try again later");
                    LOGGER.info("User tried to connect to chat but it was full.");
                    printStream.close();
                    socket.close();
                }
            } catch (IOException e) {
                LOGGER.fatal("An error occurred while trying to listen for connections");
                e.printStackTrace();
            }
        }
    }
}
