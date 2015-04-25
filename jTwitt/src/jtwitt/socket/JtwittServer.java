package jtwitt.socket;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Server of jtwitt
 * @author M
 */
public class JtwittServer{
    
    ServerSocket server;
    ServerSocket updater;
    CommunicationThread commThread;
    
    /**
     * Creates a new Server Socket
     */
    public JtwittServer(){
        try {
            server = new ServerSocket(1234);
            updater = new ServerSocket(1235); 
        } catch (IOException ex) {
            Logger.getLogger(JtwittServer.class.getName()).log(Level.SEVERE, null, ex);
        }							
    }	
    /**
     * Creates and initializes a JtwittServer
     * @param args unused
     */
    public static void main (String[] args){        
        JtwittServer myServer = new JtwittServer();
        myServer.init();
    }
    
    /**
     * Server starts listening and ServerThreads created
     */
    public void init()
    {
        ConnectionPool cp = new ConnectionPool();
        cp.start();
        
        while(true){
            try {
                System.out.println("Server is on and Listening...");
                Socket connection = server.accept();                               
                Socket connectionUpdate = updater.accept();
                
                commThread = new CommunicationThread(connection);                
                commThread.start();        
                ServerUpdate su = new ServerUpdate(connectionUpdate); 
                ServerThread st = new ServerThread(commThread,cp,su);
                cp.add(st);                                                
                st.start();   
                su.start();
            } catch (IOException ex) {
                Logger.getLogger(JtwittServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     }
}
