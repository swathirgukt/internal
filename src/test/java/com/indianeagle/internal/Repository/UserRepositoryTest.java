package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.UsersRepository;
import com.indianeagle.internal.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void findByStatus() {
        List<User> users = usersRepository.findByStatus(true);
        System.out.println(users.size());
    }

    @Test
    public void findByUserName() {
        User user = usersRepository.findByUserName("IEPL0079");
        System.out.println(user);
    }

    @Test
    public void findAllUserNames() {
        List<String> stringList = usersRepository.findAllUserNames();
        for (String s : stringList
        ) {
            System.out.println(s);
        }
    }
}
