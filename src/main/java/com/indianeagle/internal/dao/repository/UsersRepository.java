package com.indianeagle.internal.dao.repository;

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
     * To load Active Users only
     * @param status
     * @return List
     */
    //List<User> loadActiveUsers(User user);WHY DO WE NEED TO SEND USER OBJECT FOR THIS?
    List<User> findByStatus(boolean status);
    /**
     * To get a user based on userName
     *
     * @param userName
     * @return User
     */
    User findByUserName(String userName);


    /**
     * return all user names
     *
     * @return List<String>
     */
    // List<String> loadAllUserNames();
    @Query("select u.userName from User u ")
    List<String> findAllUserNames();

}
