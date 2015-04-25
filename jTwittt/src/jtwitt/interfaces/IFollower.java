/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.interfaces;

import java.util.List;
import jtwitt.server.model.Follower;
import jtwitt.server.model.FollowerPK;

/**
 * The interface that exposes the main methods Follower related classes implement
 * @author Nikos
 */
public interface IFollower {
     // Follower ---------------------------------------------------------
    public int saveFollower(Follower follow, byte flag);  //0 save , 1 edit   
    public List<Follower> loadFollowers(int follower);
    public List<Follower> loadFollowing(int following);
    public List<Follower> searchFollowers(String searchKeyWords);
    public int deleteFollower(FollowerPK fPK);
    public List hasConnectionsWith(int owner, int other);
    
    //---------------------------------------------------------------------
}
