package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.ScrumMasterDao;

@Repository
@Transactional
@Component("jpaScrumMasterDao")
public class JpaScrumMasterDao implements ScrumMasterDao {

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<ScrumMaster> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select sm from ScrumMaster sm");
	     List<ScrumMaster> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public ScrumMaster findById(int scrumMasterId) throws SQLException {
		return entityManager.find(ScrumMaster.class, scrumMasterId);
	}
	
	
	public void create(ScrumMaster scrumMaster) throws SQLException {
		entityManager.persist(scrumMaster);
	}

	
	public void update(ScrumMaster scrumMaster) throws SQLException {
		entityManager.merge(scrumMaster);
	}
	
	
	public void delete(int scrumMasterId) throws SQLException {
		ScrumMaster scrumMaster = findById(scrumMasterId);
		entityManager.remove(scrumMaster);
	}
	

	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
