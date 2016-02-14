package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Comment;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.CommentDao;

@Repository
@Transactional
@Component("jpaCommentDao")
public class JpaCommentDao implements CommentDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Comment> findAll() throws SQLException {
		Query query = entityManager.createQuery("select c from Comment c");
		List<Comment> resultList = query.getResultList();
		return resultList;
	}
	

	public List<Comment> findAllForDeveloper(int developerId) throws SQLException {
		Query query = entityManager.createQuery("select c from Comment c where c.task.developer.id = :developerId")
				.setParameter("developerId", developerId);
		List<Comment> resultList = query.getResultList();
		return resultList;
	}
	
	
	public Comment findById(int commentId) throws SQLException {
		return entityManager.find(Comment.class, commentId);
	}

	
	public void create(Comment comment) throws SQLException {
		entityManager.merge(comment);
	}

	
	public void update(Comment comment) throws SQLException {
		entityManager.merge(comment);
	}

	
	public void delete(int commentId) throws SQLException {
		Comment comment = findById(commentId);
		entityManager.remove(comment);
	}
	
	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
