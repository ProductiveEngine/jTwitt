/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.util.Actions;

/**
 *
 * @author Nikos
 */
public class ClientOnlyListen extends Thread implements Runnable{
    
    private Socket sock;
    private ClientThread cThread;
    
    private DataInputStream is;
    private String line;
    private Actions action;
    private DataInputStream dataInStr;
    
    public ClientOnlyListen(Socket mySocket, ClientThread cThread){
       this.sock = mySocket;
       this.cThread = cThread;       
   }
    @Override
    public void run(){
                
        while(true){
          
           listen();
            
        }
    }
     public synchronized void listen() {
        
        try {
            
            dataInStr = new DataInputStream(new BufferedInputStream(sock.getInputStream()));           
            int ordinal = dataInStr.readInt();
            action = Actions.values()[ordinal];
            System.out.println( " client only listen :" + action + " &" +ordinal);

            cThread.setAction(action);            
            cThread.wakeMe();            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientOnlyListen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            
        }
     }
   
}
/*

            objectOutStr = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            objectOutStr.writeObject(sentence);
            objectOutStr.flush();
             
            objectInStr = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
                try {
                    response=(String)objectInStr.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            if(!(response==null))
            {
             if((response.toLowerCase().contains("exit"))){
                 closeConn=true;
             }
             
             System.out.println("response equals:"+response+"\n");
            }
            else
            {System.out.println("no response...");}
            }
            //closing streams
             //objectInStr.close();
             objectOutStr.close();
             objectInStr.close();
             //then exit
             System.exit(0);
             * 
             * */