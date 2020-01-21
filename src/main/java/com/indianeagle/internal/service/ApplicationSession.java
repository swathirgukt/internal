package com.indianeagle.internal.facade;

import org.springframework.security.GrantedAuthority;

/**
 * Global Session Object for Application
 *
 * @author kiran.paluvadi
 */
public interface ApplicationSession {

    /**
     * Current Login User Name
     *
     * @return String
     */
    String loginUserName();

    /**
     * Gives Currently LoggedIn  User Roles
     *
     * @return Array
     */
    GrantedAuthority[] ApplicationSession();

    /**
     * Return 'true' if current user has admin roles other wise 'false'
     *
     * @return string []
     */
    boolean isAdminRoleUser();

}
