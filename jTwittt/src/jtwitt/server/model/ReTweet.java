/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.model;

import java.io.Serializable;
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
@Table(name = "RE_TWEET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReTweet.findAll", query = "SELECT r FROM ReTweet r"),
    @NamedQuery(name = "ReTweet.findByUserId", query = "SELECT r FROM ReTweet r WHERE r.reTweetPK.userId = :userId"),
    @NamedQuery(name = "ReTweet.findByTweetId", query = "SELECT r FROM ReTweet r WHERE r.reTweetPK.tweetId = :tweetId"),
    @NamedQuery(name = "ReTweet.findByOwnerId", query = "SELECT r FROM ReTweet r WHERE r.ownerId = :ownerId")})
public class ReTweet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReTweetPK reTweetPK;
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @JoinColumn(name = "TWEET_ID", referencedColumnName = "TWEET_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tweet tweet;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AppUser appUser;

    public ReTweet() {
    }

    public ReTweet(ReTweetPK reTweetPK) {
        this.reTweetPK = reTweetPK;
    }

    public ReTweet(int userId, int tweetId) {
        this.reTweetPK = new ReTweetPK(userId, tweetId);
    }

    public ReTweetPK getReTweetPK() {
        return reTweetPK;
    }

    public void setReTweetPK(ReTweetPK reTweetPK) {
        this.reTweetPK = reTweetPK;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reTweetPK != null ? reTweetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReTweet)) {
            return false;
        }
        ReTweet other = (ReTweet) object;
        if ((this.reTweetPK == null && other.reTweetPK != null) || (this.reTweetPK != null && !this.reTweetPK.equals(other.reTweetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jtwitt.server.model1.ReTweet[ reTweetPK=" + reTweetPK + " ]";
    }
    
}
