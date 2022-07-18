package files.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;

public class MessageHandler extends Observable{
    private static final Logger LOGGER = LogManager.getLogger(MessageHandler.class);
    private Socket socket;
    private OutputStream outputStream;
    private static final String CRLF = "\r\n";

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }

    public void startSocket() throws IOException {
        socket = new Socket("localhost", 4444);
        outputStream = socket.getOutputStream();

        Thread thread = new Thread(() -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = bufferedReader.readLine()) != null) {
                    notifyObservers(message);
                }
            } catch (IOException ex) {
                LOGGER.debug("An IOException occurred in the MessageHandler class in the startSocket method.");
                notifyObservers(ex);
            }
        });
        thread.start();
    }

    public void sendMessage(String text) {
        try {
            outputStream.write((text + CRLF).getBytes());
            outputStream.flush();
        } catch (IOException ex) {
            LOGGER.debug("An IOException occurred in the MessageHandler class in the sendMessage method.");
            notifyObservers(ex);
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            LOGGER.debug("An IOException occurred in the MessageHandler class in the close method.");
            notifyObservers(ex);
        }
    }
}