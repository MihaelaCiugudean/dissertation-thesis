package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;

@Repository
@Transactional
@Component("jpaBurndownChartDao")
public class JpaBurndownChartDao implements BurndownChartDao {
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<BurndownChart> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select b from BurndownChart b");
	     List<BurndownChart> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public BurndownChart findById(int burndownChartId)
			throws SQLException {
		return entityManager.find(BurndownChart.class, burndownChartId);
	}

	
	public void create(BurndownChart burndownChart) {
		entityManager.persist(burndownChart);
	}

	
	public void update(BurndownChart burndownChart) {
		entityManager.merge(burndownChart);
	}

	
	public void delete(int burndownChartId) throws SQLException {
		BurndownChart burndownChart = findById(burndownChartId);
		entityManager.remove(burndownChart);
	}
	
	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
