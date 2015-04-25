/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nikos
 */
@Embeddable
public class FollowerPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "FOLLOWER_USER_ID")
    private int followerUserId;
    @Basic(optional = false)
    @Column(name = "FOLLOWING_USER_ID")
    private int followingUserId;

    public FollowerPK() {
    }

    public FollowerPK(int followerUserId, int followingUserId) {
        this.followerUserId = followerUserId;
        this.followingUserId = followingUserId;
    }

    public int getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(int followerUserId) {
        this.followerUserId = followerUserId;
    }

    public int getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(int followingUserId) {
        this.followingUserId = followingUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) followerUserId;
        hash += (int) followingUserId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FollowerPK)) {
            return false;
        }
        FollowerPK other = (FollowerPK) object;
        if (this.followerUserId != other.followerUserId) {
            return false;
        }
        if (this.followingUserId != other.followingUserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jtwitt.server.model1.FollowerPK[ followerUserId=" + followerUserId + ", followingUserId=" + followingUserId + " ]";
    }
    
}
