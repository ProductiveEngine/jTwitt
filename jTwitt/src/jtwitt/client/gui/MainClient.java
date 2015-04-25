/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import jtwitt.server.model.AppUser;
import jtwitt.server.model.Tweet;
import jtwitt.socket.ClientOnlyListen;
import jtwitt.socket.ClientThread;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;
import jtwitt.util.Instructions;

/**
 * The main GUI of jTwitt
 * @author Nikos
 */
public class MainClient extends javax.swing.JFrame {

    public int user; //i metabliti tha paramenei k tha arxikopoiitai ston constructor tis klassis
    public AppUser appUser;
    public Tweet tweet;
    public int saved=0;
    public int deleted=-1;
    public int favorited = -1;
    public int retweeted = -1;
    private DefaultListModel listModel;
    public List<Tweet> tweetList = new ArrayList<>();    
    public List<AppUser> followList = new ArrayList<>();
    private ClientThread cThread;
    public String searchText;
    private CommunicationThread commThread;
    private Login login;
    public Socket sock;
    public int logout =-1;
    public ClientOnlyListen onlyListen = null;
    /**
     * Creates new form MainClient
     */
    public MainClient() {
        initComponents();                                                              
        setTitle("jTwitt");
        //startThreads();
        additionalGUISetup();       
            
        showLogin();
    }

    private void additionalGUISetup(){
        jLabelCounter.setText(140+"");
        listModel = new DefaultListModel();
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if(listModel.get(0) instanceof Tweet){
                        openTweetDetails(index);
                    }
                    else{
                        openFollowDetails(index);
                    }                    
                } 
            }
        });
    }
    
    private void openTweetDetails(int index){      
        TweetDetails td = new TweetDetails(this,true,tweetList.get(index),appUser,cThread);
        td.setVisible(true);
        listModel.clear();
        jList.repaint();
    }
    
    private void openFollowDetails(int index){      
        FollowDetails fd = new FollowDetails(this,true,followList.get(index),appUser,cThread);
        fd.setVisible(true);
    }
    
    /**
     * Tries to create a connection to the server if none active
     * @param serverIp string the server name or ip provided by the login form, default value 'localhost'
     * @param portNum integer the port number provided by the login form, default value '1234'
     * @return integer whose value indicates the connection result
     * @throws IOException
     */
    public int startThreads(String serverIp, int portNum) throws IOException
    {
        int success =-1;
        
        if(sock==null||sock.isClosed()==true)
        {//if no existing connections
        try{   sock = new Socket(serverIp,portNum);
                tweet = new Tweet();   
                commThread = new CommunicationThread(sock);   
                commThread.setIsClient(true);
                commThread.start();
		cThread = new ClientThread(commThread,this);
                 System.out.println("3 commThread:"+commThread.getState().toString() + " cThread:" + cThread.getState().toString());
                cThread.start();
                
                
                success = 1;
                //--------------------------------------------------------------------------------------
                //Server Update
                try{   
                    onlyListen = new ClientOnlyListen( new Socket(serverIp,portNum+1),cThread);
                    onlyListen.start();
                } catch (UnknownHostException e) {
                    System.err.println("Server Update-Don't know about host: hostname");
                    onlyListen = null;
                }catch (IOException e) {
                    System.err.println("Server Update-Couldn't get I/O for the connection to: hostname");
                    onlyListen = null;
                }
                //--------------------------------------------------------------------------------------
                
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host: hostname"+e);
               
                success = 2;
                 
                
            }catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to: hostname"+e);
                 
                 success = 3;

                 }
        }
        else {//if connection exists - e.g. when logout action has been performed
            success = 4;}
        
        return success;

    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelUserProfile = new javax.swing.JPanel();
        jLabelFullname = new javax.swing.JLabel();
        jLabelTweetsCount = new javax.swing.JLabel();
        jLabelFollowingCount = new javax.swing.JLabel();
        jLabelFollowersCount = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTweetArea = new javax.swing.JTextArea();
        jButtonTweet = new javax.swing.JButton();
        fullName = new javax.swing.JLabel();
        jButtonRefresh = new javax.swing.JButton();
        jLabelCounter = new javax.swing.JLabel();
        jPanelTweets = new javax.swing.JPanel();
        jLabelTweetsTitle = new javax.swing.JLabel();
        jButtonRefreshTweets = new javax.swing.JButton();
        jButtonOnlyMineTweets1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTextFieldSearch = new javax.swing.JTextField();
        jScrollPaneTweets = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        jTweets = new javax.swing.JRadioButton();
        jUsers = new javax.swing.JRadioButton();
        jButtonShowAllTweets = new javax.swing.JButton();
        jButtonOnlyMineTweets = new javax.swing.JButton();
        jButtonOnlyFav = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMain = new javax.swing.JMenu();
        jMenuItemEditAccount = new javax.swing.JMenuItem();
        jMenuItemEditFollowers = new javax.swing.JMenuItem();
        jMenuItemEditFollowing = new javax.swing.JMenuItem();
        jMenuItemLogout = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemInstructions = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabelFullname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelFullname.setText("Full Name");

        jLabelTweetsCount.setText("0 Tweets");
        jLabelTweetsCount.setToolTipText("Number of your tweets");

        jLabelFollowingCount.setText("0 Following");
        jLabelFollowingCount.setToolTipText("Number of jTwitt users you follow");

        jLabelFollowersCount.setText("0 Followers");
        jLabelFollowersCount.setToolTipText("Number of your followers");

        jTextAreaTweetArea.setColumns(20);
        jTextAreaTweetArea.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        jTextAreaTweetArea.setLineWrap(true);
        jTextAreaTweetArea.setRows(5);
        jTextAreaTweetArea.setToolTipText("The tweet must be 140 characters long");
        jTextAreaTweetArea.setWrapStyleWord(true);
        jTextAreaTweetArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaTweetAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaTweetArea);

        jButtonTweet.setText("Tweet");
        jButtonTweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTweetActionPerformed(evt);
            }
        });

        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setToolTipText("Refreshes your account details");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jLabelCounter.setForeground(new java.awt.Color(0, 102, 255));
        jLabelCounter.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelCounterPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanelUserProfileLayout = new javax.swing.GroupLayout(jPanelUserProfile);
        jPanelUserProfile.setLayout(jPanelUserProfileLayout);
        jPanelUserProfileLayout.setHorizontalGroup(
            jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserProfileLayout.createSequentialGroup()
                .addGroup(jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelUserProfileLayout.createSequentialGroup()
                        .addComponent(jLabelFollowingCount, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFollowersCount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jButtonRefresh))
                    .addGroup(jPanelUserProfileLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelCounter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTweet))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelUserProfileLayout.createSequentialGroup()
                        .addGroup(jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelTweetsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addComponent(fullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(83, 83, 83)))
                .addContainerGap())
        );
        jPanelUserProfileLayout.setVerticalGroup(
            jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFullname)
                    .addComponent(fullName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTweetsCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFollowingCount)
                    .addComponent(jLabelFollowersCount)
                    .addComponent(jButtonRefresh))
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUserProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTweet)
                    .addComponent(jLabelCounter))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelFullname.getAccessibleContext().setAccessibleName("");

        jLabelTweetsTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTweetsTitle.setText("Tweets");

        jButtonRefreshTweets.setText("Refresh");
        jButtonRefreshTweets.setToolTipText("Shows all the tweets from the twitters you follow");
        jButtonRefreshTweets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshTweetsActionPerformed(evt);
            }
        });

        jButtonOnlyMineTweets1.setText("Only mine");
        jButtonOnlyMineTweets1.setToolTipText("Shows only your tweets");
        jButtonOnlyMineTweets1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnlyMineTweetsActionPerformed(evt);
            }
        });

        jTextFieldSearch.setText("Search");
        jTextFieldSearch.setToolTipText("Press Enter to Search for Tweets or Users");
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
        });

        jList.setToolTipText("List Of Tweets or Users - double click to open details");
        jScrollPaneTweets.setViewportView(jList);

        buttonGroup1.add(jTweets);
        jTweets.setSelected(true);
        jTweets.setText("Tweets");
        jTweets.setToolTipText("Search for Tweets");

        buttonGroup1.add(jUsers);
        jUsers.setText("Users");
        jUsers.setToolTipText("Search for Users");

        jButtonShowAllTweets.setText("Show All");
        jButtonShowAllTweets.setToolTipText("Shows all the tweets from the jTwitt users you follow");
        jButtonShowAllTweets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowAllTweetsActionPerformed(evt);
            }
        });

        jButtonOnlyMineTweets.setText("Only Mine");
        jButtonOnlyMineTweets.setToolTipText("Shows only your tweets");
        jButtonOnlyMineTweets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnlyMineTweetsActionPerformed(evt);
            }
        });

        jButtonOnlyFav.setText("Only Fav");
        jButtonOnlyFav.setToolTipText("Shows only your favorite tweets");
        jButtonOnlyFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnlyFavActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTweetsLayout = new javax.swing.GroupLayout(jPanelTweets);
        jPanelTweets.setLayout(jPanelTweetsLayout);
        jPanelTweetsLayout.setHorizontalGroup(
            jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTweetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTweets, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(jPanelTweetsLayout.createSequentialGroup()
                        .addComponent(jLabelTweetsTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTweets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTweetsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOnlyFav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOnlyMineTweets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowAllTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelTweetsLayout.setVerticalGroup(
            jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTweetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTweetsTitle)
                        .addComponent(jTweets)
                        .addComponent(jUsers)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonShowAllTweets)
                    .addComponent(jButtonOnlyMineTweets)
                    .addComponent(jButtonOnlyFav))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuMain.setText("Account");

        jMenuItemEditAccount.setText("Edit Account");
        jMenuItemEditAccount.setToolTipText("Change your account details");
        jMenuItemEditAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditAccountActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemEditAccount);

        jMenuItemEditFollowers.setText("Edit Followers");
        jMenuItemEditFollowers.setToolTipText("Stop veing folloed by other jTwiit users");
        jMenuItemEditFollowers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditFollowersActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemEditFollowers);

        jMenuItemEditFollowing.setText("Edit Following");
        jMenuItemEditFollowing.setToolTipText("Stop Following jTwiit users");
        jMenuItemEditFollowing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditFollowingActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemEditFollowing);

        jMenuItemLogout.setText("Logout");
        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemLogout);

        jMenuBar1.add(jMenuMain);

        jMenu2.setText("Help");

        jMenuItemInstructions.setText("Instructions");
        jMenuItemInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInstructionsActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemInstructions);

        jMenuItemAbout.setText("About");
        jMenu2.add(jMenuItemAbout);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTweets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTweets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonRefreshTweetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshTweetsActionPerformed
        lockControls();
        cThread.setAction(Actions.REFRESH_TWEETS);
        cThread.wakeMe();
    }//GEN-LAST:event_jButtonRefreshTweetsActionPerformed

    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed
        try {
//            cThread.setAction(Actions.LOGOUT);
//            cThread.wakeMe();
            resetClient();
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemLogoutActionPerformed
 private void jButtonShowAllTweetsActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        cThread.setAction(Actions.REFRESH_TWEETS);
        cThread.wakeMe();
    }
    private void jButtonOnlyMineTweetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOnlyMineTweetsActionPerformed
        lockControls();
        cThread.setAction(Actions.ONLY_MINE);
        cThread.wakeMe();
    }//GEN-LAST:event_jButtonOnlyMineTweetsActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
     
     int key = evt.getKeyCode();
     if (key == KeyEvent.VK_ENTER) { 
        lockControls(); 
        searchText = jTextFieldSearch.getText();
        System.out.println("ENTER pressed Search:"+searchText+"---");
        if(jUsers.isSelected()){
            cThread.setAction(Actions.SEARCH_USERS);
        }
        else{
            cThread.setAction(Actions.SEARCH_TWEETS);
        }                    
        cThread.wakeMe();
        }
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        System.out.println(" "+evt);    
        
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItemEditAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditAccountActionPerformed

                EditUserData edit = new EditUserData(this,true,appUser,cThread);
                edit.setVisible(true);

    }//GEN-LAST:event_jMenuItemEditAccountActionPerformed

    private void jButtonTweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTweetActionPerformed
        lockControls();
        Date d = new Date();
        tweet.setTweet(jTextAreaTweetArea.getText());
        tweet.setCreateDate(d);
        tweet.setUserId(appUser);
        System.out.println("tweet text:"+tweet.getTweet()+ tweet.getUserId());
        cThread.setAction(Actions.SAVE_TWEET);
        cThread.wakeMe();
    }//GEN-LAST:event_jButtonTweetActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
       cThread.setAction(Actions.REFRESH_MY_PROFILE);
       cThread.wakeMe();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jLabelCounterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelCounterPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelCounterPropertyChange

    private void jTextAreaTweetAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaTweetAreaKeyReleased
       
        if(jTextAreaTweetArea.getText().length() >= 140){
            jTextAreaTweetArea.setText(jTextAreaTweetArea.getText().substring(0, 140));
        }        
        jLabelCounter.setText(140-jTextAreaTweetArea.getText().length()+"");
    }//GEN-LAST:event_jTextAreaTweetAreaKeyReleased

    private void jMenuItemEditFollowersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditFollowersActionPerformed
        
        lockControls();
        EditFollow ef = new EditFollow(this,true,Actions.LOAD_FOLLOWER_PROFILES,appUser,cThread);
        ef.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemEditFollowersActionPerformed

    private void jMenuItemEditFollowingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditFollowingActionPerformed
       
        lockControls();
        EditFollow ef = new EditFollow(this,true,Actions.LOAD_FOLLOWING_PROFILES,appUser,cThread);
        ef.setVisible(true);
    }//GEN-LAST:event_jMenuItemEditFollowingActionPerformed

    private void jButtonOnlyFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOnlyFavActionPerformed
        lockControls();
        cThread.setAction(Actions.ONLY_FAV);
        cThread.wakeMe();
    }//GEN-LAST:event_jButtonOnlyFavActionPerformed

    private void jMenuItemInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInstructionsActionPerformed

        Instructions showInstr = new Instructions();
        
    }//GEN-LAST:event_jMenuItemInstructionsActionPerformed

    /**
     * @param args the command line arguments, not used
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainClient().setVisible(false);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel fullName;
    private javax.swing.JButton jButtonOnlyFav;
    private javax.swing.JButton jButtonOnlyMineTweets;
    private javax.swing.JButton jButtonOnlyMineTweets1;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonRefreshTweets;
    private javax.swing.JButton jButtonShowAllTweets;
    private javax.swing.JButton jButtonTweet;
    private javax.swing.JLabel jLabelCounter;
    private javax.swing.JLabel jLabelFollowersCount;
    private javax.swing.JLabel jLabelFollowingCount;
    private javax.swing.JLabel jLabelFullname;
    private javax.swing.JLabel jLabelTweetsCount;
    private javax.swing.JLabel jLabelTweetsTitle;
    private javax.swing.JList jList;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemEditAccount;
    private javax.swing.JMenuItem jMenuItemEditFollowers;
    private javax.swing.JMenuItem jMenuItemEditFollowing;
    private javax.swing.JMenuItem jMenuItemInstructions;
    private javax.swing.JMenuItem jMenuItemLogout;
    private javax.swing.JMenu jMenuMain;
    private javax.swing.JPanel jPanelTweets;
    private javax.swing.JPanel jPanelUserProfile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneTweets;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextAreaTweetArea;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JRadioButton jTweets;
    public javax.swing.JRadioButton jUsers;
    // End of variables declaration//GEN-END:variables


    /**
     * Refreshes the TweetList after action performed
     */
    public void refreshTweetList(){
             
        if(tweetList != null){            
            listModel.clear();
            System.out.println("inside refresh");
            Iterator it = tweetList.iterator();            
            while(it.hasNext()){
                listModel.addElement(it.next());  
            }
           jList.setModel(listModel);
        }
        else
        {
          listModel.addElement("No Tweets found..."); 
          jList.setModel(listModel);
        }    
    }
    
    /**
     * Gets the basic user information
     */
    public void getUserInfo(){
           cThread.setAction(Actions.REFRESH_MY_PROFILE);
           cThread.wakeMe();
    }
    
  
    /**
     * Refreshes the main data of the user 
     * @param appUser the currently logged  in AppUser object
     */
    public void refreshAppUser(AppUser appUser){
        this.appUser = appUser;
        jLabelFullname.setText("Welcome "+this.appUser.getFirstName()+" "+this.appUser.getLastName());
        jLabelFollowingCount.setText(this.appUser.getNumOfFollowing()+" Following");
        jLabelFollowersCount.setText(this.appUser.getNumOfFollowers()+" Followers");
        jLabelTweetsCount.setText(this.appUser.getNumOfTweets()+" Tweets");
    }
    

    /**
     * Gets the result from SAVE Action and acts accordingly
     * @param save integer that indicates save success (1) or failure (-1)
     */
    public void getSaveResult(int save){
        this.saved = save;
        if (saved == 1){
            jTextAreaTweetArea.setText("Tweet Saved");           
        }
        else{
            jTextAreaTweetArea.setText("Tweet could not be saved.");
        }
    
    }
       
    /**
     * Gets Tweet search results and display them
     */
    public void searchTweetResults(){
        listModel = new DefaultListModel();       
        if (tweetList.isEmpty()){
            listModel.addElement("No results found for:"+searchText);
            jList.setModel(listModel);
        }
        else{ 
        Iterator it = tweetList.iterator();         
            while(it.hasNext()){
                  listModel.addElement(it.next());  
               }
           jList.setModel(listModel);
        }
   }
    /**
     * Gets User search results and display them
     */
    public void searchUsersResults(){
        listModel = new DefaultListModel();       
        if (followList.isEmpty()){
            listModel.addElement("No results found for:"+searchText);
            jList.setModel(listModel);
        }
        else{ 
        Iterator it = followList.iterator();         
            while(it.hasNext()){
                  listModel.addElement(it.next());  
               }
           jList.setModel(listModel);
        }
   }
    
    /**
     * Disable Controls while performing actions 
     */
    public void lockControls(){
       jButtonOnlyMineTweets.setEnabled(false);
       jButtonRefreshTweets.setEnabled(false);
       jButtonTweet.setEnabled(false);
       jMenuBar1.setEnabled(false);
       repaint();
   } 
    /**
     * Re-enable controls after actions performed
     */
    public void unlockControls(){
       jButtonOnlyMineTweets.setEnabled(true);
       jButtonRefreshTweets.setEnabled(true);
       jButtonTweet.setEnabled(true);
       jMenuBar1.setEnabled(true);
       repaint();
   }

    /**
     * Creates and displays a login form when MainClient is initialized
     * 
     */
    private void showLogin() {
        
        login = new Login();
        login.setMainClient(this);

        login.setVisible(true);
        
    }
   

    /**
     * Used by login or register to get access to the current ClientThread
     * @return
     */
    public ClientThread getCThread(){
        
        return cThread;
    }
       
    
    /**
     * On logout Action creates a new login form and sets the main client visible
     * @throws IOException
     */
    public void resetClient() throws IOException{

           
            login = new Login(); 
            login.setMainClient(this);
            login.setVisible(true);

            
            this.setVisible(false); // hide main client and does not close connection
                
    }
    
}
