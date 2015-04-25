/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jtwitt.interfaces.IFollower;
import jtwitt.server.model.Follower;
import jtwitt.server.model.FollowerPK;

/**
 *
 * @author Nikos
 */
public class FollowerDao implements IFollower{
    
    EntityManager em;
    public FollowerDao(EntityManager em ){
        this.em = em;
    }
            
    @Override
    public int saveFollower(Follower follow, byte flag) {
       if(flag == 0){ 
           
           em.persist(follow); 
           return 1;
       } //new
       else if(flag == 1){ //edit            
            
            Follower editFollowers = em.find(Follower.class,follow.getFollowersPK());            
            editFollowers.setIsActive(follow.getIsActive());    
            em.persist(editFollowers);
            return 1;
        }
        else{
            return -1;
        }
    }
    
    @Override
    public List<Follower> loadFollowers(int follower){
        Query query = em.createNamedQuery("Follower.findByFollowerUserId");
        query.setParameter("followerUserId",follower);              
        return query.getResultList();
    }
    @Override
    public List<Follower> loadFollowing(int following) {
        Query query = em.createNamedQuery("Follower.findByFollowingUserId");
        query.setParameter("followingUserId",following);
        return query.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Follower> searchFollowers(String searchKeyWords) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int deleteFollower(FollowerPK fPK) {
        
        Follower deleteFollowers = em.find(Follower.class,fPK);                      
        em.remove(deleteFollowers);
        return 1;
    }

    @Override
    public List hasConnectionsWith(int owner, int other) {
           
        List li = new ArrayList();
        FollowerPK fPK1 = new FollowerPK(owner, other);
        FollowerPK fPK2 = new FollowerPK(other, owner);
        Follower findFollowers1 = em.find(Follower.class,fPK1);  
        Follower findFollowers2 = em.find(Follower.class,fPK2);  
        
        li.add(findFollowers1 != null);
        li.add(findFollowers2 != null);
        return li;
    }

    
    
}
