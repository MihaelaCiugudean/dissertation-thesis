package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.HistoryItemDao;

@Repository
@Transactional
@Component("jpaHistoryItemDao")
public class JpaHistoryItemDao implements HistoryItemDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<HistoryItem> findAll() throws SQLException {
		Query query = entityManager.createQuery("select h from HistoryItem h");
		List<HistoryItem> resultList = query.getResultList();
		return resultList;
	}

	
	public HistoryItem findById(int historyItemId) throws SQLException {
		return entityManager.find(HistoryItem.class, historyItemId);
	}
	

	public void create(HistoryItem historyItem) throws SQLException {
		entityManager.persist(historyItem);
	}

	
	public void update(HistoryItem historyItem) throws SQLException {
		entityManager.merge(historyItem);
	}

	
	public void delete(int historyItemId) throws SQLException {
		HistoryItem historyItem = findById(historyItemId);
		entityManager.remove(historyItem);
	}
	
	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
