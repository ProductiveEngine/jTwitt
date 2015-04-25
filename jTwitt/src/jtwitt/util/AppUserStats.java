/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.util;

/**
 *
 * @author Nikos
 */
public enum AppUserStats {
 
    REFRESH_FOLLOW(1),REFRESH_TWEET(2);
    
    private int value;
    
    private AppUserStats(int value){
        this.value = value;
    }    
}
