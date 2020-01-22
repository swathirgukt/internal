package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen
 * DAO to perform CRUD operations on user
 */
@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    /**
     * To save user
     * @param user
     */
    //void save(User user);

    /**
     * To update user
     * @param user
     */
    //void update(User user);

    /**
     * To get all users.
     * @return list
     */
    //List<User> loadAll();

    /**
     * To load Active Users only
     * @param user
     * @return List
     */
    //List<User> loadActiveUsers(User user);WHY DO WE NEED TO SEND USER OBJECT FOR THIS?

    /**
     * To get a user based on userName
     *
     * @param userName
     * @return User
     */
    User findByUserName(String userName);

    /**
     * To save or update user
     * @param user
     */
    //  void saveOrUpdate(User user);

    /**
     * return all user names
     *
     * @return List<String>
     */
    // List<String> loadAllUserNames();
    @Query("select u.userName from User u ")
    List<String> findAllUserNames();

}
