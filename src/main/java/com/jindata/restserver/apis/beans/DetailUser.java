package com.jindata.restserver.apis.beans;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@XmlRootElement(name="User")
public class DetailUser extends User{
    public DetailUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            long userno, String email, String isdeleted, String isEmailAuth, 
            Date lastLogin, Date writedate,long grantuserno) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     */
    private static final long serialVersionUID = -4489724615200438289L;
    private enum USERTYPE {ADMIN,MANAGER,USER};
    private long userno;
    private String email;
    private String isdeleted;
    private String isEmailAuth;
    private Date lastLogin;
    private Date writedate;
    private long grantuserno;
    private USERTYPE usertype;
    private List<Group> groups;
    private List<Role> roles;
    private List<Roletarget> roletargets;
    /**
     * @return the userno
     */
    public long getUserno() {
        return userno;
    }
    /**
     * @param userno the userno to set
     */
    public void setUserno(long userno) {
        this.userno = userno;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the isdeleted
     */
    public String getIsdeleted() {
        return isdeleted;
    }
    /**
     * @param isdeleted the isdeleted to set
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }
    /**
     * @return the isEmailAuth
     */
    public String getIsEmailAuth() {
        return isEmailAuth;
    }
    /**
     * @param isEmailAuth the isEmailAuth to set
     */
    public void setIsEmailAuth(String isEmailAuth) {
        this.isEmailAuth = isEmailAuth;
    }
    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }
    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    /**
     * @return the writedate
     */
    public Date getWritedate() {
        return writedate;
    }
    /**
     * @param writedate the writedate to set
     */
    public void setWritedate(Date writedate) {
        this.writedate = writedate;
    }
    /**
     * @return the grantuserno
     */
    public long getGrantuserno() {
        return grantuserno;
    }
    /**
     * @param grantuserno the grantuserno to set
     */
    public void setGrantuserno(long grantuserno) {
        this.grantuserno = grantuserno;
    }
    /**
     * @return the usertype
     */
    public USERTYPE getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        if(usertype.equals("U")){
            this.usertype = USERTYPE.USER;
        }else if(usertype.equals("M")){
            this.usertype = USERTYPE.MANAGER;
        }else if(usertype.equals("A")){
            this.usertype = USERTYPE.ADMIN;
        }
    }
    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }
    /**
     * @param groups the groups to set
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }
    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    /**
     * @return the roletargets
     */
    public List<Roletarget> getRoletargets() {
        return roletargets;
    }
    /**
     * @param roletargets the roletargets to set
     */
    public void setRoletargets(List<Roletarget> roletargets) {
        this.roletargets = roletargets;
    }
    
    @Override
    public String toString() {
        return super.toString() + "; Email: "+ this.email;
    }
}
