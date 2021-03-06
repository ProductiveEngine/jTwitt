/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.gui;

import java.util.Date;
import javax.swing.JOptionPane;
import jtwitt.server.model.AppUser;
import jtwitt.server.model.Tweet;
import jtwitt.socket.ClientThread;
import jtwitt.util.Actions;
import jtwitt.util.FixText;

/**
 * The display of details of a tweet object
 * Allows the user to interact with the tweet
 * @author Nikos
 */
public class TweetDetails extends javax.swing.JDialog{

    
     public Tweet tweet;
     public AppUser user;
     ClientThread cThread;
     MainClient gui;
     Date d;
     public Tweet reply;     
     private boolean sameUser = false;
     private boolean hasReplied = false;
    
     public TweetDetails(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     *
     * @param parent
     * @param modal
     * @param tweet
     * @param user
     * @param cThread
     */
    public TweetDetails(java.awt.Frame parent, boolean modal, Tweet tweet, AppUser user ,ClientThread cThread) {
        super(parent, modal);
       
        initComponents();
         
        cThread.setTweetDetails(this);
        
        this.tweet = tweet;
        this.user = user;
        this.cThread = cThread;
        d = new Date(); 
        sameUser = tweet.getUserId().getUserId().intValue() == user.getUserId().intValue()?true:false;
        fillTweetDetails();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jUserDetails = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTweet = new javax.swing.JTextArea();
        jRetweet = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jReplyTweet = new javax.swing.JTextArea();
        jReply = new javax.swing.JButton();
        jSave = new javax.swing.JButton();
        jLabelReply = new javax.swing.JLabel();
        jFavorited = new javax.swing.JLabel();
        jToggleFav = new javax.swing.JToggleButton();
        jButtonOpenLinks = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jLabelReTweet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jUserDetails.setText("Tweeted by: ");

        jDate.setText("On: ");

        jTweet.setColumns(20);
        jTweet.setLineWrap(true);
        jTweet.setRows(5);
        jTweet.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTweet);

        jRetweet.setText("Retweet");
        jRetweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRetweetActionPerformed(evt);
            }
        });

        jLabel1.setText("OPTIONS:");

        jDelete.setText("Delete");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jReplyTweet.setColumns(20);
        jReplyTweet.setLineWrap(true);
        jReplyTweet.setRows(5);
        jReplyTweet.setToolTipText("");
        jReplyTweet.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jReplyTweet);

        jReply.setText("Reply");
        jReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReplyActionPerformed(evt);
            }
        });

        jSave.setText("Save");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jLabelReply.setText("Reply:");

        jFavorited.setForeground(new java.awt.Color(51, 51, 255));
        jFavorited.setText("Favorited");

        jToggleFav.setText("Favorite");
        jToggleFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleFavActionPerformed(evt);
            }
        });

        jButtonOpenLinks.setText("Open Links");
        jButtonOpenLinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenLinksActionPerformed(evt);
            }
        });

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jLabelReTweet.setForeground(new java.awt.Color(51, 51, 255));
        jLabelReTweet.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDate)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFavorited, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelReTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jUserDetails, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelReply, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonOpenLinks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleFav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jReply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRetweet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jUserDetails)
                .addGap(1, 1, 1)
                .addComponent(jDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFavorited)
                    .addComponent(jLabelReTweet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabelReply)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRetweet)
                    .addComponent(jToggleFav)
                    .addComponent(jSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDelete)
                    .addComponent(jButtonOpenLinks)
                    .addComponent(jReply)
                    .addComponent(jButtonClose)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRetweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRetweetActionPerformed

        cThread.setAction(Actions.RETWEET);
        cThread.wakeMe(); 
        
    }//GEN-LAST:event_jRetweetActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
             
        cThread.setAction(Actions.DELETE_TWEET);
        cThread.wakeMe();
                
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReplyActionPerformed
        reply = new Tweet();
        reply.setCreateDate(d);
        reply.setReplyId(tweet.getTweetId());
        reply.setTweet(jReplyTweet.getText());
        reply.setUserId(user);         

        cThread.setAction(Actions.REPLY);
        cThread.wakeMe();
             
    }//GEN-LAST:event_jReplyActionPerformed

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
              
        tweet.setTweet(jTweet.getText());
        tweet.setModifyDate(d);
        cThread.setAction(Actions.EDIT_TWEET);
        cThread.wakeMe();
 
    }//GEN-LAST:event_jSaveActionPerformed

    private void jToggleFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleFavActionPerformed
        lockControls(); 
        if(!jToggleFav.isSelected()){                 
                cThread.setAction(Actions.UNFAVORITE);
                cThread.wakeMe();
         }
         else{              
                cThread.setAction(Actions.FAVORITE);
                cThread.wakeMe();
         }          
    }//GEN-LAST:event_jToggleFavActionPerformed

    private void jButtonOpenLinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenLinksActionPerformed
        FixText ft = new FixText();
        ft.OpenBrowser(jTweet.getText());
    }//GEN-LAST:event_jButtonOpenLinksActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonOpenLinks;
    private javax.swing.JLabel jDate;
    private javax.swing.JButton jDelete;
    private javax.swing.JLabel jFavorited;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelReTweet;
    private javax.swing.JLabel jLabelReply;
    private javax.swing.JButton jReply;
    private javax.swing.JTextArea jReplyTweet;
    private javax.swing.JButton jRetweet;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleFav;
    private javax.swing.JTextArea jTweet;
    private javax.swing.JLabel jUserDetails;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param retweet
     */
    public void retweeted(int retweet)
{
    if(retweet == 1){   
        jLabelReTweet.setText("ReTweeted");   
        jRetweet.setEnabled(false);
    }
    else{
        jLabelReTweet.setText("");        
    }
}
    /**
     *
     * @param retweet
     */
    public void retweeted2(int retweet)
{
    if(retweet == 1){   
        jLabelReTweet.setText("ReTweeted");   
        jRetweet.setEnabled(false);
    }
    else{
        jLabelReTweet.setText(""); 
        jRetweet.setEnabled(true);
    }
}
    /**
     *
     * @param deleted
     */
    public void deletedOk(int deleted){
    if(deleted == 1){        
        JOptionPane.showConfirmDialog(null, "Tweet Deleted","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    else {    
        JOptionPane.showConfirmDialog(null, "Tweet was not Deleted!","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
    }  
}

    /**
     *
     * @param edited
     */
    public void edited(int edited){
    if(edited == 1){        
        JOptionPane.showConfirmDialog(null, "Tweet Edited","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
    else {        
        JOptionPane.showConfirmDialog(null, "Tweet was not Edited!","Confirm",JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     *
     * @param favorited
     */
    public void favorited(int favorited){

    if (favorited == 1){
        jFavorited.setText("Favorite");       
        jToggleFav.setSelected(true);
        
    }    
    else{
        jFavorited.setText("");    
        jToggleFav.setSelected(false);
    }    
}

    /**
     *
     * @param unfavorited
     */
    public void deleteFav(int unfavorited){
    
    if(unfavorited == 1){
        jFavorited.setText("");
        jToggleFav.setSelected(false);
    }
    else{
        jFavorited.setText("Favorite");
    }        
}
    
    /**
     *
     * @param replied
     */
    public void replied(int replied){   
    if(replied == 1){
        jLabelReply.setText("Replied successfully");        
        hasReplied = true;
        jReply.setEnabled(false);
        jReplyTweet.setEnabled(false);
    }
    else{
        jLabelReply.setText("Error replying");        
        hasReplied = false;
        jReply.setEnabled(true);
    }        
}

private void fillTweetDetails() {
              
        AppUser tweetUser = tweet.getUserId();        
        jDate.setText("On "+tweet.getCreateDate());
        jTweet.setText(tweet.getTweet());
        jUserDetails.setText("By "+tweetUser.getFirstName()+" "+tweetUser.getLastName());
        jTweet.setEditable(sameUser);
        jDelete.setEnabled(sameUser);           
        jReply.setEnabled(!hasReplied);
        jRetweet.setEnabled(!sameUser);
        jSave.setEnabled(sameUser);
        isFavorited();
    }

    /**
     *
     */
    public void isFavorited()
{
    cThread.setAction(Actions.IS_FAVORITED);
    cThread.wakeMe();

}
    /**
     *
     */
    public void lockControls(){
    jToggleFav.setEnabled(false);
}
    /**
     *
     */
    public void unlockControls(){
    jToggleFav.setEnabled(true);
}
}

