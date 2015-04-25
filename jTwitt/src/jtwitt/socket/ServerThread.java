package jtwitt.socket;

import java.io.*;
import java.net.*;
import java.util.List;
import jtwitt.server.business.ServerDBConnector;
import jtwitt.server.dao.AppUserDao;
import jtwitt.util.Actions;
//import java.util.ArrayList;

/**
 * The Jtwitt Server Thread
 * Handles client requests
 * @author M
 */
public class ServerThread extends Thread{

    private ServerDBConnector serverDb;
    ServerUpdate serverUpdate;
    private CommunicationThread commThread;
            
    public List<ServerThread> connectionArray; 
   
    private AppUserDao appUserDAO;
    private ConnectionPool connectionPool;
            
    ServerThread(CommunicationThread commThread, ConnectionPool connectionPool,ServerUpdate serverUpdate) throws IOException{
       
        this.commThread = commThread;
        this.serverUpdate = serverUpdate;
        this.serverDb = new ServerDBConnector(commThread, serverUpdate);       
        this.connectionPool = connectionPool;
    }
   @Override
    public void run(){
        try {
            boolean bool=true;


            while(bool == true)
            {
                     
                try  {
 
                        
                        int readAction = commThread.readInt();
                        System.out.println("Func to execute: " + readAction);
                        
                        
                        serverDb.execute(Actions.values()[readAction]);

                        //bool = checkConnection(commThread.getSock());
                      } catch (IOException ex) {
                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //commThread.closeAll();
            }catch(Exception ex){System.out.println(ex);}
   }
   
    /** 
     * This method is used to check if the current socket is closed 
     * in order to do some clean up in threads and streams
     * @param sock the current socket
     * @return true if not closed, false if closed
     * @throws IOException
     */
    public boolean checkConnection(Socket sock) throws IOException{
        
        boolean check ;
        if(sock.isClosed()==true){
        check = false;

        }
        else {check = true;}
        
        return check;
    }
   
    /**
     * Assigns the List<ServerThread> to the local variable
     * @param stList List<ServerThread>
     */
    public void setConnectionArray(List<ServerThread> stList){
       this.connectionArray = stList;
   }

}

//
//
//
//objectInStr = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
//        clientMessage=(String)objectInStr.readObject();
//        //check content of clientMessage
//        if(!(clientMessage.toLowerCase().contains("exit"))){
//            response="you said : "+clientMessage;
//            //verify you got it
//            System.out.println("Received: " + clientMessage);	
//            // create an output stream to send response to client*/
//            objectOutStr = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
//            objectOutStr.writeObject(response);
//            objectOutStr.flush();   
//            System.out.println("u said: " + clientMessage);
//        }
//        else{
//        System.out.println("u said "+ clientMessage +" , so closing all connections\n");
//        objectOutStr.writeBytes("exit\n"); 
//        objectOutStr.flush();
//        bool=CheckConnection(sock);
//        } 
//    }
//     objectOutStr.close();
//     objectInStr.close();
//     sock.close();
//     //System.exit(0);
//    }   catch (ClassNotFoundException ex) {
//            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//catch (Exception e) {System.err.println("error ServerThread:"+e);}