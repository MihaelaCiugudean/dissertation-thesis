package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.UserDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.UserService;

@Service("userService")
public class UserServiceImplementation implements UserService {

	private UserDao userDao;
	
	@Autowired
	private DeveloperDao developerDao;
	
	private Logger logger = Logger.getLogger(UserServiceImplementation.class.getName());
	
	@Autowired
	public UserServiceImplementation(UserDao userDao){
		this.userDao = userDao;
	}
	

	public List<User> findAll() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			users = (ArrayList<User>) userDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users from the database!");
			e.printStackTrace();
		}
		return users;
	}


	public User findById(int userId) {
		try {
			User user = userDao.findById(userId);
			return user;
		} catch (SQLException e) {
			logger.warn("The user with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}

	
	public User findByUsernameAndPassword(String username, String password) {
		try {
			User user = userDao.findByUsernameAndPassword(username, password);
			return user;
		} catch (SQLException e) {
			logger.warn("The user with the specified username and password can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	

	public void create(User user) {
		try {
			userDao.create(user);
		} catch (SQLException e) {
			logger.warn("The user can not be created!", e);
			e.printStackTrace();
		}
	}


	public void update(User user) {
		try {
			userDao.update(user);
		} catch (SQLException e) {
			logger.warn("The user can not be updated!", e);
			e.printStackTrace();
		}
	}


	public void delete(int userId) {
		try {
			userDao.delete(userId);
		} catch (SQLException e) {
			logger.warn("The user with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
	

	public void registerUser(User user) {
		user.setPosition("developer");
		try {
			userDao.create(user);
		} catch (SQLException e) {
			logger.warn("The user with the specified username and password can not be created!", e);
			e.printStackTrace();
		}
	}
}
