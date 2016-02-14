package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;

@Repository
@Transactional
@Component("jpaStoryDao")
public class JpaStoryDao implements StoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Story> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select s from Story s");
	     List<Story> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public Story findById(int storyId) throws SQLException {
		return entityManager.find(Story.class, storyId);
	}

	
	public void create(Story story) throws SQLException {
		entityManager.persist(story);
	}
	
	
	public void update(Story story) throws SQLException {
		entityManager.merge(story);
	}

	
	public void delete(int storyId) throws SQLException {
		Story story = findById(storyId);
		entityManager.remove(story);
	}


	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
