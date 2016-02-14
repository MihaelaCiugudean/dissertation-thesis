package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.UserDao;

@Repository
@Transactional
@Component("jpaUserDao")
public class JpaUserDao implements UserDao {

	private Logger logger = Logger.getLogger(JpaUserDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<User> findAll() throws SQLException {
		Query query = entityManager.createQuery("select u from User u");
		List<User> resultList = query.getResultList();
		return resultList;
	}


	public User findById(int userId) throws SQLException {
		return entityManager.find(User.class, userId);
	}
	
	
	public User findByUsernameAndPassword(String username, String password) {
		User user = new User();
		Query query = entityManager.createQuery("SELECT u from User u "
				+ " where u.username = :username and u.password = :password ");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			user = (User) query.getSingleResult();
			return user;
		}
		return null;
	}

	
	public void create(User user) throws SQLException {
		entityManager.persist(user);
	}


	public void update(User user) throws SQLException {
		entityManager.merge(user);
	}


	public void delete(int userId) throws SQLException {
		User user = findById(userId);
		entityManager.remove(user);
	}

	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
