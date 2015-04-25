/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.util.SocketActions;


/**
 * Handles the streams for both client and server using synchronized methods
 * @author Nikos
 */
public class CommunicationThread extends Thread implements Serializable{
        
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Socket sock;
    private SocketActions action;
    private BufferedOutputStream bus;
    private BufferedInputStream bis;
    
    boolean isClient = false;
    
    /**
     * Gets the socket to be handled and assigns it to a local variable
     * @param sock Socket 
     */
    public CommunicationThread(Socket sock){
    
        this.sock = sock;        
    }
    /**
     * Reads an integer from the stream using DataInputStream
     * @return integer
     * @throws IOException
     */
    public synchronized int readInt() throws IOException{
        int result = dataIn.readInt();
        return result;               
    }
    /**
     * Writes an integer to the stream using DataOutputStream
     * @param i integer
     * @throws IOException
     */
    public synchronized void writeInt(int i) throws IOException{              
         dataOut.writeInt(i);
         dataOut.flush();           
   }
    /**
     * Reads an object from the stream using ObjectInputStream
     * @return object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized Object readObject() throws IOException, ClassNotFoundException{
        Object o;               
        o = objectIn.readObject();        
        return o;
    }
    /**
     * Writes an object to the stream using ObjectOutputStream
     * @param o object
     * @throws IOException
     */
    public synchronized void writeObject(Object o) throws IOException{       
         objectOut.reset();
         objectOut.writeObject(o);
         objectOut.flush();
         
    }     
    @Override
    public void run(){
        
        boolean ok = true;

        try {       
           bus = new BufferedOutputStream(sock.getOutputStream());
           bis = new BufferedInputStream(sock.getInputStream());
            if(isClient){                
                dataOut = new DataOutputStream(bus);    
                dataOut.flush();
                dataIn = new DataInputStream(bis);                
                objectOut = new ObjectOutputStream(bus);
                objectOut.flush();
                objectIn = new ObjectInputStream(bis);                            
            }
            else{                                
                dataIn = new DataInputStream(bis);    
                dataOut = new DataOutputStream(bus);  
                dataOut.flush();
                objectIn = new ObjectInputStream(bis);
                objectOut = new ObjectOutputStream(bus);
                objectOut.flush();                   
            }
               
        } catch (Exception ex) {
            Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
            ok = false;
            
        } 
                
    }
    /**
     * Gets the current SocketActions object
     * @return SocketActions 
     */
    public synchronized SocketActions getAction() {
        return action;
    }

    /**
     * Assigns a SocketActions object to the local variable
     * @param action SocketActions
     */
    public synchronized void setAction(SocketActions action) {
        this.action = action;
    }

    /**
     * Gets the current Socket object
     * @return Socket
     */
    public Socket getSock() {
        return sock;
    }

    /**
     * Assigns a Socket object to the local variable
     * @param sock Socket
     */
    public void setSock(Socket sock) {
        this.sock = sock;
    }    

    /**
     * 
     * @return boolean
     */
    public boolean isIsClient() {
        return isClient;
    }

    /**
     * Sets  the local variable true if a client or false if server
     * @param isClient boolean
     */
    public void setIsClient(boolean isClient) {
        this.isClient = isClient;
    }
    /**
     * Resets Streams to avoid corrupted data
     * @throws IOException
     */
    public void resetAll() throws IOException{
       
        dataIn.reset();
        objectOut.reset();  
    }
    
    /**
     * Closes Streams
     * @throws IOException
     */
    public void closeAll() throws IOException{
//        dataIn.close();
//        objectIn.close();
        
        dataOut.close();
        objectOut.close();
        
//        bis.close();
//        bus.close();
//        
//        sock.close();

    }

    /**
     *
     * @return
     */
    public DataOutputStream getDataOut() {
        return dataOut;
    }

    /**
     *
     * @param dataOut
     */
    public void setDataOut(DataOutputStream dataOut) {
        this.dataOut = dataOut;
    }

    /**
     *
     * @return
     */
    public DataInputStream getDataIn() {
        return dataIn;
    }

    /**
     *
     * @param dataIn
     */
    public void setDataIn(DataInputStream dataIn) {
        this.dataIn = dataIn;
    }

    /**
     *
     * @return
     */
    public ObjectInputStream getObjectIn() {
        return objectIn;
    }

    /**
     *
     * @param objectIn
     */
    public void setObjectIn(ObjectInputStream objectIn) {
        this.objectIn = objectIn;
    }

    /**
     *
     * @return
     */
    public ObjectOutputStream getObjectOut() {
        return objectOut;
    }

    /**
     *
     * @param objectOut
     */
    public void setObjectOut(ObjectOutputStream objectOut) {
        this.objectOut = objectOut;
    }
    
}
