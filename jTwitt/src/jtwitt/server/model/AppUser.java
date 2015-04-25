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
@Table(name = "APP_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a"),
    @NamedQuery(name = "AppUser.findByUserId", query = "SELECT a FROM AppUser a WHERE a.userId = :userId"),
    @NamedQuery(name = "AppUser.findByUsername", query = "SELECT a FROM AppUser a WHERE a.username = :username"),
    @NamedQuery(name = "AppUser.findByPassword", query = "SELECT a FROM AppUser a WHERE a.password = :password"),
    @NamedQuery(name = "AppUser.findByFirstName", query = "SELECT a FROM AppUser a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "AppUser.findByLastName", query = "SELECT a FROM AppUser a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "AppUser.findByEmail", query = "SELECT a FROM AppUser a WHERE a.email = :email"),
    @NamedQuery(name = "AppUser.findByCreateDate", query = "SELECT a FROM AppUser a WHERE a.createDate = :createDate"),
    @NamedQuery(name = "AppUser.findByNumOfFollowers", query = "SELECT a FROM AppUser a WHERE a.numOfFollowers = :numOfFollowers"),
    @NamedQuery(name = "AppUser.findByNumOfFollowing", query = "SELECT a FROM AppUser a WHERE a.numOfFollowing = :numOfFollowing"),
    @NamedQuery(name = "AppUser.findByNumOfTweets", query = "SELECT a FROM AppUser a WHERE a.numOfTweets = :numOfTweets"),
    @NamedQuery(name = "AppUser.findByFollower", query = "SELECT a FROM AppUser a, Follower f WHERE a.userId = f.follower.userId AND f.following.userId = :userId"),
    @NamedQuery(name = "AppUser.findByFollowing", query = "SELECT a FROM AppUser a, Follower f WHERE a.userId = f.following.userId AND f.follower.userId = :userId"),
//    @NamedQuery(name = "AppUser.login", query = "SELECT a FROM AppUser a WHERE a.userId = :userId"),
    @NamedQuery(name = "AppUser.login", query = "SELECT a FROM AppUser a WHERE a.username = :username AND a.password= :password"),
    @NamedQuery(name = "AppUser.searchFollow", query = "SELECT a FROM AppUser a WHERE a.username LIKE :sUser AND a.userId != :userId ORDER BY a.createDate DESC")
})
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "NUM_OF_FOLLOWERS")
    private int numOfFollowers;
    @Basic(optional = false)
    @Column(name = "NUM_OF_FOLLOWING")
    private int numOfFollowing;
    @Basic(optional = false)
    @Column(name = "NUM_OF_TWEETS")
    private int numOfTweets;

    public AppUser() {
    }

    public AppUser(Integer userId) {
        this.userId = userId;
    }

    public AppUser(Integer userId, String username, String password, String firstName, String lastName, String email, Date createDate, int numOfFollowers, int numOfFollowing, int numOfTweets) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.numOfFollowers = numOfFollowers;
        this.numOfFollowing = numOfFollowing;
        this.numOfTweets = numOfTweets;
    }
    public AppUser(String username, String password, String firstName, String lastName, String email) {        
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;               
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNumOfFollowers() {
        return numOfFollowers;
    }

    public void setNumOfFollowers(int numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public int getNumOfFollowing() {
        return numOfFollowing;
    }

    public void setNumOfFollowing(int numOfFollowing) {
        this.numOfFollowing = numOfFollowing;
    }

    public int getNumOfTweets() {
        return numOfTweets;
    }

    public void setNumOfTweets(int numOfTweets) {
        this.numOfTweets = numOfTweets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lastName+" "+firstName;
    }
    
}
//javax.persistence.Query query =
//  em.createQuery("select order from Order as order
//  left join order.customer as customer
//  where customer.name like '%' || :name || '%'");
//
//query.setParameter("name", name);
//query.setFirstResult(0);
//query.setMaxResults(10);
//
//// returns 10 or less orders
//List<Order> orders = query.getResultList();
