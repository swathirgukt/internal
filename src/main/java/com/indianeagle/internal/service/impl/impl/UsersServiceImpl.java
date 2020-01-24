package com.indianeagle.internal.service.impl.impl;

import com.indianeagle.internal.dao.repository.UsersRepository;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.service.UsersService;
import com.indianeagle.internal.util.CryptoUtil;
import com.indianeagle.internal.util.SimpleUtils;
import java.util.List;

/**
 * @author kiran.paluvadi
 * User Service to perform all operations on USER table
 */
public class UsersServiceImpl implements UsersService {

	 private UsersRepository usersRepository;
		
	 /**
	  * save user
	  * @param userForm
	  */
	public void save(User userForm)	{
		usersRepository.save(userForm);
	}
	
	/**
	 * save or update user
	 * @param user
	 */
    public void saveOrUpdate(User user) {
		usersRepository.save(user);
	}

	/**
	 * to find all users in Database
	 * @return List
	 */
	public List<User> loadAll() {
		return usersRepository.findAll();
	}

	/**
	 * to find all active users in Database
	 * @param user
	 * @return List
	 */
	public List<User> loadActiveUsers(User user){
		return usersRepository.findByStatus(user.getStatus());
	}

	/**
	 * to find user based on userName
	 * @param userName
	 */
	public User findByUserName(String userName){
		return usersRepository.findByUserName(userName);
	}

	/**
     * return all user names
     * @return List<String>
     */
   public List<String> loadAllUserNames(){
    	return usersRepository.findAllUserNames();
    }

   /**
	 * @param dao the dao to set
	 */
	public void setDao(UsersRepository dao) {
		this.usersRepository = dao;
	}
	
	/**
	 * Resets User's password to a randomly generated string
	 * @param user
	 * @return String
	 */
	public String resetPassword(User user) {
		String newPassword = SimpleUtils.generateRandomAcessCode();
		String encrypted = CryptoUtil.encryptPassWord(newPassword);
		user.setPassword(encrypted);
		usersRepository.save(user);
		return newPassword;
	}
}
