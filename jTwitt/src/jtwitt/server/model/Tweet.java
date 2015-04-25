/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nikos
 */
@Entity
@Table(name = "TWEET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t"),
    @NamedQuery(name = "Tweet.findByTweetId", query = "SELECT t FROM Tweet t WHERE t.tweetId = :tweetId"),
    @NamedQuery(name = "Tweet.findByTweet", query = "SELECT t FROM Tweet t WHERE t.tweet = :tweet"),
    @NamedQuery(name = "Tweet.findByReplyId", query = "SELECT t FROM Tweet t WHERE t.replyId = :replyId"),
    @NamedQuery(name = "Tweet.findByCreateDate", query = "SELECT t FROM Tweet t WHERE t.createDate = :createDate"),
    @NamedQuery(name = "Tweet.findByModifyDate", query = "SELECT t FROM Tweet t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tweet.findByUserId", query = "SELECT t FROM Tweet t WHERE t.userId.userId = :userId OR t.tweetId IN ( SELECT r.tweet.tweetId FROM ReTweet r WHERE r.appUser.userId = :userId ) ORDER BY t.createDate DESC"),
    @NamedQuery(name = "Tweet.countTweets", query = "SELECT COUNT(t) FROM Tweet t WHERE t.userId.userId = :userId"),
    @NamedQuery(name = "Tweet.searchTweets", query = "SELECT t FROM Tweet t WHERE t.tweet LIKE :sTweet ORDER BY t.createDate DESC")    
})


public class Tweet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TWEET_ID")
    private Integer tweetId;
    @Column(name = "TWEET")
    private String tweet;
    @Basic(optional = false)
    @Column(name = "REPLY_ID")
    private int replyId;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private List<FavTweet> favTweetsList;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private AppUser userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private List<ReTweet> reTweetList;

    public Tweet() {
    }

    public Tweet(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Tweet(Tweet clone) {
        this.tweetId = clone.tweetId;
        this.tweet = clone.tweet;
        this.replyId = clone.replyId;
        this.createDate = clone.createDate;
        this.modifyDate = clone.modifyDate;
        this.favTweetsList = clone.favTweetsList;
        this.userId = clone.userId;
    }

    public Tweet(Integer tweetId, String tweet, int replyId, Date createDate, Date modifyDate, List<AppUser> usersList, List<FavTweet> favTweetsList, AppUser userId) {
        this.tweetId = tweetId;
        this.tweet = tweet;
        this.replyId = replyId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.favTweetsList = favTweetsList;
        this.userId = userId;
    }

        
    public Tweet(Integer tweetId, int replyId, Date createDate) {
        this.tweetId = tweetId;
        this.replyId = replyId;
        this.createDate = createDate;
    }
    
    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    @XmlTransient
    public List<FavTweet> getFavTweetsList() {
        return favTweetsList;
    }

    public void setFavTweetsList(List<FavTweet> favTweetsList) {
        this.favTweetsList = favTweetsList;
    }

    public AppUser getUserId() {
        return userId;
    }

    public void setUserId(AppUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweetId != null ? tweetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tweet)) {
            return false;
        }
        Tweet other = (Tweet) object;
        if ((this.tweetId == null && other.tweetId != null) || (this.tweetId != null && !this.tweetId.equals(other.tweetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userId.getUsername()+": "+tweet;
    }
    
}
