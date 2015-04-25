/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.util;

/**
 *
 * @author Nikos
 */
public enum SocketActions {
    
    READ_INT(1),WRITE_INT(2),READ_OBJECT(3), WRITE_OBJECT(4);
    
    private int value;
    
    private SocketActions(int value){
        this.value = value;
    }    
}
