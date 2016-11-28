/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.networking;

import com.sun.istack.internal.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author kejsi
 */
public class MessageListener extends Thread{
    
    ServerSocket server;
    int port = 8877;
    WritableGUI gui;
    
    //save info and pass it to object 
    public MessageListener(WritableGUI gui, int port) {
        this.port = port;
        this.gui = gui;
        
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MessageListener() {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    @Override
    public void run() {
         //socket is like a connection to a port 
            Socket clientSocket;
        try {
            /*keeps accepting connections from the server
            when it gets connection => stores it in client socker
            if this conn is null(ex: ports been closed) otherwise it keeps accepting
            connections !!
            */
             while((clientSocket = server.accept()) != null) {
                //store it in gui
                //decouple message listener from ui
                //get string from socket ; now that the server has accepted the conn and it is stored in client socket and is not null 
                InputStream is = clientSocket.getInputStream();
                //for efficency
                //br converts to string 
                BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
                //to get line of data 
                String line = br.readLine();
                if(line != null) {
                    //we just get access to gui !
                    gui.write(line);
                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
