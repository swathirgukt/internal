package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.User;
import java.util.List;

/**
 * @author kiran.paluvadi
 * Facade to perform save,update,delete and find users.
 */
public interface UsersService {

    /**
     * save user
     *
     * @param userForm
     */
    void save(User userForm);

    /**
     * save or update user
     *
     * @param user
     */
    void saveOrUpdate(User user);

    /**
     * to find all users in Database
     *
     * @return List
     */
    List<User> loadAll();

    /**
     * To find all active users from database
     *
     * @param user
     * @return List
     */
    List<User> loadActiveUsers(User user);

    /**
     * to find user based on userName
     *
     * @param userName
     * @return User
     */
    User findByUserName(String userName);

    /**
     * Resets User's password to a randomly generated string {@link SimpleUtils.generateRandomAcessCode}
     *
     * @param user
     * @return String
     */
    String resetPassword(User user);

    /**
     * return all user names
     *
     * @return List<String>
     */
    List<String> loadAllUserNames();
}
