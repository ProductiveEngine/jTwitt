/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.util;
 
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Opens the pdf with the instructions for jTwitt
 * @author M
 */
public class Instructions {
    

    public Instructions(){
     try { // path will be changed to relative path
 
		File pdfFile = new File("d:\\Instructions.pdf");
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
                           
                            JOptionPane.showConfirmDialog(null, "Awt Desktop is not supported!","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
 
		} else {
                           JOptionPane.showConfirmDialog(null, "File does not exist!","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
 
		System.out.println("Instructions Opened");
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
 

}
}
