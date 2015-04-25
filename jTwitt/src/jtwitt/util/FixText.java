/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nikos
 */
public class FixText {       
    
    /**
     * Opens the tweet hyperlink
     * @param text String the given tweet
     */
    public void OpenBrowser(String text){        
        StringTokenizer st = new StringTokenizer(text);
        String tempString;
        while (st.hasMoreTokens()) { 
            tempString = st.nextToken();
            if(IsMatch(tempString)){
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI(tempString);
                    desktop.browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }            
        }
        
    }
    
 /**
 * Checks whether the specified string is a hyperlink
 * @param s String the tweet to be checked
 * @return  true if tweet is a link false if not
 */
    private boolean IsMatch(String s) {
        try {
            Pattern patt = Pattern.compile("http://.*|www\\..*");                    
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
