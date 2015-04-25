/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import jtwitt.server.model.Tweet;

/**
 * On a click cell event this class handles the appearance of the clicked cell
 * background and foreground
 * @author Nikos
 */
public class TweetCellRenderer extends DefaultListCellRenderer {
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Tweet entry = (Tweet) value;
        setText(entry.getTweet());
        if(isSelected){
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        }
        else {
            setBackground(Color.white);
            setForeground(Color.black);
        }                        
        
        return this;
    }

    
}
