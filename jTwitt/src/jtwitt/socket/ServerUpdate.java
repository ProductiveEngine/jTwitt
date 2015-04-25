/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.server.business.ServerDBConnector;
import jtwitt.util.Actions;



public class ServerUpdate extends Thread implements Runnable{
    
    Socket sock;
    private Actions action;
    
    PrintWriter printwriter;
    private DataOutputStream dataOutStr;
    
    ServerUpdate(Socket conn){
        this.sock = conn;
        
    }
   @Override
    public void run(){
       boolean bool=false;


        while(bool == false)
        {
            updateClients();
        }
   }
   public synchronized void updateClients()  {
        try {
            wait();
            
            dataOutStr = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            dataOutStr.writeInt(action.ordinal());
            dataOutStr.flush();
            
        } catch (InterruptedException ex  ) {
            Logger.getLogger(ServerUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException e){
            
        }
   }

   public synchronized void wakeMe(){
       notify();
   }
    public Actions getAction() {
        return action;
    }

    public synchronized void setAction(Actions action) {
        this.action = action;
    }
   
           
}
