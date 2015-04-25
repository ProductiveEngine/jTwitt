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
    CommunicationThread commThread;
    
    /**
     * Creates a new Server Socket
     */
    public JtwittServer(){
        try {
            server = new ServerSocket(1234);                         
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
                
                commThread = new CommunicationThread(connection);                
                commThread.start();                 
                ServerThread st = new ServerThread(commThread,cp);
                cp.add(st);                                                
                st.start();              
            } catch (IOException ex) {
                Logger.getLogger(JtwittServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     }
}
