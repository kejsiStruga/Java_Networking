package chat.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kejsi
 */
public class MessageTransmitter extends Thread{

    String msg , hostname; 
    int port;
    
    public MessageTransmitter(String msg, String hostname, int port) {
        this.msg = msg;
        this.hostname = hostname;
        this.port = port;
        
    }
    
    @Override
    public void run() {
        try {
            Socket s = new Socket(hostname, port);
            //send data as bytes
            s.getOutputStream().write(msg.getBytes());
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
