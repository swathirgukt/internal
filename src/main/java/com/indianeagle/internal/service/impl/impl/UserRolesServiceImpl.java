package com.indianeagle.internal.service.impl.impl;

import com.indianeagle.internal.dao.repository.UserRolesRepository;
import com.indianeagle.internal.dto.Role;
import com.indianeagle.internal.service.UserRolesService;
import java.util.List;

/**
 * @author kiran.paluvadi
 * User Roles Service to load all roles from USER_ROLES table
 */
public class UserRolesServiceImpl implements UserRolesService {

	private UserRolesRepository rolesRepository;
	
	/**
	 * To find all Roles
	 * @return list
	 */
	public List<Role> loadAll() {
		return rolesRepository.findAll();
	}

    /**
     * To find roles based on role name
     * @param roleName the roleName to set
     * @return list of roles
     */
    //todo
    public List<Role> findRolesByRoleName(String roleName){
        return rolesRepository.findRolesByRoleName(roleName);
    }

	/**
	 * @param rolesdao the rolesdao to set
	 */
	public void setRolesdao(UserRolesRepository rolesdao) {
		this.rolesRepository = rolesdao;
	}
}
