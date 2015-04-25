/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.socket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles the List of ServerThreads
 * @author Nikos
 */
public class ConnectionPool extends Thread{
    
    public List<ServerThread> connectionArray = new ArrayList<>();

    /**
     *
     * @return
     */
    public synchronized List<ServerThread> getConnectionArray() {
        return connectionArray;
    }

    /**
     *
     * @param connectionArray
     */
    public synchronized void setConnectionArray(List<ServerThread> connectionArray) {
        this.connectionArray = connectionArray;
    }
    
    /**
     * Adds a ServerThread to the ConnectionArray and removes null
     * @param st ServerThread 
     */
    public synchronized void add(ServerThread st){
        connectionArray.removeAll(Collections.singleton(null));
        connectionArray.add(st);
    }        
}
