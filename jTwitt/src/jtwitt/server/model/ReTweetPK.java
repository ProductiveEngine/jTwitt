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
public class ReTweetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @Column(name = "TWEET_ID")
    private int tweetId;

    public ReTweetPK() {
    }

    public ReTweetPK(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) tweetId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReTweetPK)) {
            return false;
        }
        ReTweetPK other = (ReTweetPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.tweetId != other.tweetId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jtwitt.server.model1.ReTweetPK[ userId=" + userId + ", tweetId=" + tweetId + " ]";
    }
    
}
