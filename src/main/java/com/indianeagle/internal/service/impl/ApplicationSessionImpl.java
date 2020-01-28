package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.service.ApplicationSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *  Global Session Object for Application
 * @author Unskrishna
 */
public class ApplicationSessionImpl implements ApplicationSession {

	/**
	 * Get Spring Security Context
	 * @return SecurityContext
	 */
	public SecurityContext getContext() {
		return SecurityContextHolder.getContext();
	}
	
	/**
	 * Current Login User Name
	 * @return string
	 */
	public String loginUserName(){
		return getContext().getAuthentication().getName();	
	}

	@Override
	public GrantedAuthority[] ApplicationSession() {
		return new GrantedAuthority[0];
	}


	/**
	 * Current Login User Roles
	 * @return string []
	 */
	public GrantedAuthority[] loginUserRoles()
	{
		return getContext().getAuthentication().getAuthorities().toArray(new GrantedAuthority[0]);
	}
	
	/**
	 * Return 'true' if current user has admin roles other wise 'false' 
	 * @return string []
	 */
	public boolean isAdminRoleUser(){
		boolean adminRoleUser = false;
		GrantedAuthority Authorities[] = getContext().getAuthentication().getAuthorities().toArray(new GrantedAuthority[0]);
		for (GrantedAuthority grantedAuthority : Authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
				adminRoleUser =  true;
		}
		return adminRoleUser;
	}
}
