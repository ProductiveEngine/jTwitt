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
 * The representation of FavTweet
 * @author Nikos
 */
@Entity
@Table(name = "FAV_TWEET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavTweet.findAll", query = "SELECT f FROM FavTweet f"),
    @NamedQuery(name = "FavTweet.findByUserId", query = "SELECT f FROM FavTweet f WHERE f.favTweetPK.userId = :userId"),
    @NamedQuery(name = "FavTweet.findByTweetId", query = "SELECT f FROM FavTweet f WHERE f.favTweetPK.tweetId = :tweetId"),
    @NamedQuery(name = "FavTweet.findByOwnerId", query = "SELECT f FROM FavTweet f WHERE f.ownerId = :ownerId"),
    @NamedQuery(name = "FavTweet.findByPK", query = "SELECT f FROM FavTweet f WHERE f.favTweetPK.userId = :userId AND f.favTweetPK.tweetId = :tweetId"),
    @NamedQuery(name = "FavTweet.OnlyFav", query = "SELECT f.tweet FROM FavTweet f WHERE f.appUser.userId =:userId")
})
public class FavTweet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FavTweetPK favTweetPK;
    @Basic(optional = false)
    @Column(name = "OWNER_ID")
    private int ownerId;
    @JoinColumn(name = "TWEET_ID", referencedColumnName = "TWEET_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tweet tweet;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AppUser appUser;

    /**
     * Constructor of FavTweet object
     */
    public FavTweet() {
    }

    /**
     * Constructor of FavTweet object
     * @param favTweetPK FavTweetPK
     */
    public FavTweet(FavTweetPK favTweetPK) {
        this.favTweetPK = favTweetPK;
    }

    /**
     * Constructor of FavTweet object
     * @param favTweetPK FavTweetPK
     * @param ownerId integer
     */
    public FavTweet(FavTweetPK favTweetPK, int ownerId) {
        this.favTweetPK = favTweetPK;
        this.ownerId = ownerId;
    }

    /**
     * Constructor of FavTweet object
     * @param userId integer
     * @param tweetId integer
     */
    public FavTweet(int userId, int tweetId) {
        this.favTweetPK = new FavTweetPK(userId, tweetId);
    }

    /**
     * Constructor of FavTweet object
     * @return getFavTweetPK
     */
    public FavTweetPK getFavTweetPK() {
        return favTweetPK;
    }

    public void setFavTweetPK(FavTweetPK favTweetPK) {
        this.favTweetPK = favTweetPK;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
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
        hash += (favTweetPK != null ? favTweetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavTweet)) {
            return false;
        }
        FavTweet other = (FavTweet) object;
        if ((this.favTweetPK == null && other.favTweetPK != null) || (this.favTweetPK != null && !this.favTweetPK.equals(other.favTweetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jtwitt.server.model1.FavTweet[ favTweetPK=" + favTweetPK + " ]";
    }
    
}
