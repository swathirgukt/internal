package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author kiran.paluvadi
 * POJO which represents Roles of a user
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable{
    @Id
    @Column(name = "ROLES")
    private String roleName;
    @Column(name = "ACCESS_SCREENS")
    private String accessScreens = "ALL";

    @OneToMany
    private Set<User> users;


    /**
     * @return the accessScreens
     */
    public String getAccessScreens() {
        return accessScreens;
    }

    /**
     * @param accessScreens the accessScreens to set
     */
    public void setAccessScreens(String accessScreens) {
        this.accessScreens = accessScreens;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Overriding equals method
     */

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Role) {
            Role role = (Role) obj;
            return this.getRoleName().equals(role.getRoleName());
        } else {
            return false;
        }
    }

    /**
     * @return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
