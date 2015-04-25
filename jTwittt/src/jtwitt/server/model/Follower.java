/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nikos
 */
@Entity
@Table(name = "FOLLOWER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follower.findAll", query = "SELECT f FROM Follower f"),
    @NamedQuery(name = "Follower.findByFollowerUserId", query = "SELECT f FROM Follower f WHERE f.followerPK.followerUserId = :followerUserId"),
    @NamedQuery(name = "Follower.findByFollowingUserId", query = "SELECT f FROM Follower f WHERE f.followerPK.followingUserId = :followingUserId"),
    @NamedQuery(name = "Follower.findByIsActive", query = "SELECT f FROM Follower f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "Follower.findByPK", query = "SELECT f FROM Follower f WHERE f.followerPK.followerUserId = :followerUserId AND f.followerPK.followingUserId = :followingUserId "),
    @NamedQuery(name = "Follower.countFollowers", query = "SELECT COUNT(f) FROM Follower f WHERE f.followerPK.followingUserId = :userId"),
    @NamedQuery(name = "Follower.countFollowing", query = "SELECT COUNT(f) FROM Follower f WHERE f.followerPK.followerUserId = :userId")
})
public class Follower implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FollowerPK followerPK;
    @Basic(optional = false)
    @Column(name = "IS_ACTIVE")
    private short isActive;
    @JoinColumn(name = "FOLLOWER_USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AppUser follower;
    @JoinColumn(name = "FOLLOWING_USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AppUser following;

    public Follower() {
    }

    public Follower(FollowerPK followerPK) {
        this.followerPK = followerPK;
    }

    public Follower(FollowerPK followerPK, short isActive) {
        this.followerPK = followerPK;
        this.isActive = isActive;
    }

    public Follower(int followerUserId, int followingUserId) {
        this.followerPK = new FollowerPK(followerUserId, followingUserId);
    }

    public FollowerPK getFollowersPK() {
        return followerPK;
    }

    public void setFollowersPK(FollowerPK followerPK) {
        this.followerPK = followerPK;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public AppUser getUsers() {
        return follower;
    }

    public void setUsers(AppUser users) {
        this.follower = users;
    }

    public AppUser getUsers1() {
        return following;
    }

    public void setUsers1(AppUser users1) {
        this.following = users1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followerPK != null ? followerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follower)) {
            return false;
        }
        Follower other = (Follower) object;
        if ((this.followerPK == null && other.followerPK != null) || (this.followerPK != null && !this.followerPK.equals(other.followerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jtwitt.server.model.Follower[ followerPK=" + followerPK + " ]";
    }
    
}
