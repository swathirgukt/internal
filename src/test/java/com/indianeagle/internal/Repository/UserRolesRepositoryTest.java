package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.UserRolesRepository;
import com.indianeagle.internal.dto.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRolesRepositoryTest {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Test
    public void byRoleName() {
        List<Role> roles = userRolesRepository.findRolesByRoleName("ROLE_ADMIN");
        System.out.println(roles.size());
    }
}
