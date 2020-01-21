package com.indianeagle.internal.facade;

import com.yana.internal.dto.Role;
import java.util.List;

/**
 * @author kiran.paluvadi
 * Facade to find all roles
 */
public interface UserRolesService {

    /**
     * To find all Roles
     *
     * @return list
     */
    List<Role> loadAll();

    /**
     * To find roles based on role name
     *
     * @param roleName the roleName to set
     * @return list of roles
     */
    List<Role> findRolesByRoleName(String roleName);
}
