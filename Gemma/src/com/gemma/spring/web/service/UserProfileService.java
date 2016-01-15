package com.gemma.spring.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.dao.UserProfileDao;

@Service("userProfileService")
public class UserProfileService {
	@Autowired
	private UserProfileDao userProfileDao;

	public boolean exists(String username) {
		
		return userProfileDao.exists(username);
	}
	
	public void create(UserProfile user) {
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		user.setDateAdded(new Date());

		userProfileDao.create(user);
	}

	public List<UserProfile> getAllUsers(){
		
		return userProfileDao.getAllUsers();
	}
	
	public boolean delete(String username) {
		
		return userProfileDao.delete(username);
	}
	
	public UserProfile getUser(String username) {
		
		return userProfileDao.getUser(username);
	}
	
	public void partialUpdate(UserProfile user) {
		
		userProfileDao.partialUpdate(user);
	}
	
	public void updatePassword(UserProfile user) {
		
		userProfileDao.updatePassword(user);
	}

	public void updateProfile(UserProfile user) {
		
		userProfileDao.updateProfile(user);
		
	}
}
