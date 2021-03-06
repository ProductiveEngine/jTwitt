
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.gui;

import java.util.ArrayList;
import java.util.List;
import jtwitt.server.model.AppUser;
import jtwitt.server.model.Follower;
import jtwitt.server.model.FollowerPK;
import jtwitt.socket.ClientThread;
import jtwitt.util.Actions;

/**
 *
 * @author Nikos
 */
public class FollowDetails extends javax.swing.JDialog {

    private byte action;
    public AppUser follower;
    public AppUser user;
    public Follower follow; 
    public FollowerPK followPK; 
    public List connections = new ArrayList();
    private boolean activeFollowing = false;
    ClientThread cThread;
     
    public FollowDetails(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public FollowDetails(java.awt.Frame parent, boolean modal, AppUser follower, AppUser user ,ClientThread cThread) {
        super(parent, modal);       
        initComponents();
        cThread.setFollowDetails(this);
        this.follower = follower;
        this.user = user;
        this.cThread = cThread;
        additionalGUISetup();
    }
    private void additionalGUISetup(){
        jLabelUsername.setText(follower.getUsername());
        jLabelFirstName.setText(follower.getFirstName());
        jLabelLastName.setText(follower.getLastName());
        jLabelError.setText("");
        lockControls();
        cThread.setAction(Actions.HAS_CONNECTIONS_WITH);
        cThread.wakeMe();
                
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alway
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jToggleFollow = new javax.swing.JToggleButton();
        jButtonFollowing = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Username :");

        jLabel2.setText("First name :");

        jLabel3.setText("Last name :");

        jLabelUsername.setText("username");

        jLabelFirstName.setText("jLabel4");

        jLabelLastName.setText("jLabel4");

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jToggleFollow.setText("Follow");
        jToggleFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleFollowActionPerformed(evt);
            }
        });

        jButtonFollowing.setText("Stop being followed");
        jButtonFollowing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFollowingActionPerformed(evt);
            }
        });

        jLabelError.setForeground(new java.awt.Color(255, 0, 102));
        jLabelError.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 62, Short.MAX_VALUE)
                        .addComponent(jButtonFollowing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose)
                    .addComponent(jToggleFollow)
                    .addComponent(jButtonFollowing))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jToggleFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleFollowActionPerformed
            
        lockControls();
        jLabelError.setText("");
        if(jToggleFollow.isSelected()){
            jToggleFollow.setText("UnFollow");
            follow = new Follower(user.getUserId(),follower.getUserId());
            cThread.setAction(Actions.SAVE_FOLLOW);           
        }
        else{
            jToggleFollow.setText("Follow");
            followPK = new FollowerPK(user.getUserId(),follower.getUserId());
            cThread.setAction(Actions.DELETE_FOLLOW);
        }
         cThread.wakeMe();
         
    }//GEN-LAST:event_jToggleFollowActionPerformed

    private void jButtonFollowingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFollowingActionPerformed
        lockControls();
        followPK = new FollowerPK(follower.getUserId(),user.getUserId());
        cThread.setAction(Actions.DELETE_FOLLOW);        
        cThread.wakeMe();
        activeFollowing = false;
    }//GEN-LAST:event_jButtonFollowingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonFollowing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JToggleButton jToggleFollow;
    // End of variables declaration//GEN-END:variables
    
    public void lockControls(){
        jButtonClose.setEnabled(false);
        jToggleFollow.setEnabled(false);
        jButtonFollowing.setEnabled(activeFollowing);
    }
    public void unlockControlsInitial(){
        jButtonClose.setEnabled(true);
        jToggleFollow.setEnabled(true);        
        
        boolean temp = (boolean)connections.get(0);
        jToggleFollow.setSelected(temp);
        if(temp){
            jToggleFollow.setText("UnFollow");
        }
        else{
            jToggleFollow.setText("Follow");
        }
        
        
        activeFollowing = (boolean)connections.get(1);
        jButtonFollowing.setEnabled(activeFollowing);
    }
    public void unlockControls(){
        jButtonClose.setEnabled(true);
        jToggleFollow.setEnabled(true);
        jButtonFollowing.setEnabled(activeFollowing);
    }
}
