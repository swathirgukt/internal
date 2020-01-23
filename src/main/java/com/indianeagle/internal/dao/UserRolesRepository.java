package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen
 * DAO to load all roles from Database
 */
@Repository
public interface UserRolesRepository extends JpaRepository<Role, String> {

    /**
     * To get all roles for user
     * @return list
     */
    //public List<Role> loadAll();

    /**
     * To find roles based on role name
     * @param roleName the roleName to set
     * @return list of roles
     */
    @Query("select role from Role role where role.roleName = :roleName")
    List<Role> findRolesByRoleName(@Param("roleName") String roleName);

}
