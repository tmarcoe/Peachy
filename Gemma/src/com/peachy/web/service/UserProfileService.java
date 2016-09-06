package com.peachy.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.dao.UserProfileDao;
import com.peachy.web.orm.UserProfile;

@Service("userProfileService")
public class UserProfileService {
	@Autowired
	private UserProfileDao userProfileDao;

	public boolean exists(String username) {
		
		return userProfileDao.exists(username);
	}
	
	public void create(UserProfile user) {
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(false);

		user.setDateAdded(new Date());

		userProfileDao.create(user);
	}

	public List<UserProfile> getAllUsers(){
		
		return userProfileDao.getAllUsers();
	}
	public PagedListHolder<UserProfile> getPagedList() {
		return new PagedListHolder<UserProfile>(getAllUsers());
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

	public UserProfile getUserByID(int userID) {
		
		return userProfileDao.getUserByID(userID);
	}

	public List<UserProfile> getDailySpecialUsers() {
		return userProfileDao.getDailySpecialUsers();
	}
}
