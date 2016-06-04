package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;

@Repository
@Transactional
@Component("jpaDeveloperDao")
public class JpaDeveloperDao implements DeveloperDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Developer> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select d from Developer d");
	     List<Developer> resultList = query.getResultList();
	     if(resultList.size() >0) return resultList;
	     return null;
	}
	
	
	public Developer findById(int developerId)
			throws SQLException {
		return entityManager.find(Developer.class, developerId);
	}

	
	public Developer findByName(String firstName, String lastName) throws SQLException {
		Developer developer = new Developer();
		Query query = entityManager
				.createQuery("select d from Developer d "
								+ " where d.firstName = :firstName and d.lastName = :lastName")
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName);
		 developer = (Developer) query.getSingleResult();
		 return developer;
	}
	
	
	public Developer findByUserId(int userId)
			throws SQLException {
		Query query = entityManager.createQuery("select d from Developer d "+" where d.user.id = :userId")
				.setParameter("userId", userId);
		Developer developer = (Developer) query.getSingleResult();
		return developer;
	}
	
	
	public void create(Developer developer) throws SQLException {
		entityManager.merge(developer);
	}

	
	public void update(Developer developer) throws SQLException {
		entityManager.merge(developer);
	}

	
	public void delete(int developerId) throws SQLException {
		Developer developer = findById(developerId);
		entityManager.remove(developer);
	}


	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
