package com.iquestgroup.advancedframeworks.ScrumTaskboard.util;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaDeveloperDao;

public class DeveloperImportUtil {

	private static final int DEVELOPERS_NUMBER = 1000;
	private static final String DEVELOPER_POSITION = "developer";
	private static final String DEVELOPER = "dev";
	private static final String DEVELOPER_FIRST_NAME = "FN";
	private static final String DEVELOPER_LAST_NAME = "LN";

	public static void main(String[] args) throws SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/spring/application-config.xml");
		DeveloperDao jpaDeveloperDao = (JpaDeveloperDao) context.getBean("jpaDeveloperDao");

		for (int i = 1; i <= DEVELOPERS_NUMBER; i++) {
			User user = createUser(i);
			jpaDeveloperDao.create(createDeveloper(i, user));
		}
	}

	
	private static User createUser(int id) {
		User user = new User();
		user.setId(id);
		String credentials = DEVELOPER + Integer.toString(id);
		user.setUsername(credentials);
		user.setPassword(credentials);
		user.setPosition(DEVELOPER_POSITION);

		return user;
	}
	

	private static Developer createDeveloper(int id, User user) throws SQLException {
		String credentials = DEVELOPER + Integer.toString(id);

		Developer developer = new Developer();
		developer.setFirstName(credentials + DEVELOPER_FIRST_NAME);
		developer.setLastName(credentials + DEVELOPER_LAST_NAME);
		developer.setLevel(DEVELOPER_POSITION);
		developer.setUser(user);
		return developer;
	}
}
