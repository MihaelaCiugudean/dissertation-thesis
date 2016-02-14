package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.MetaTagDao;

@Repository
@Transactional
@Component("jpaMetaTagDao")
public class JpaMetaTagDao implements MetaTagDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<MetaTag> findAll() throws SQLException {
		Query query = entityManager.createQuery("select mt from MetaTag mt");
		List<MetaTag> resultList = query.getResultList();
		return resultList;
	}

	
	public MetaTag findById(int metaTagId) throws SQLException {
		return entityManager.find(MetaTag.class, metaTagId);
	}


	public MetaTag findByName(String metaTagName) throws SQLException {
		Query query = entityManager.createQuery("select mt from MetaTag mt where mt.metaTagName = :metaTagName")
				.setParameter("metaTagName", metaTagName);
		List<MetaTag> metaTags = query.getResultList();
		if(metaTags.size() >0) return metaTags.get(0);
		return null;
	}

	
	public MetaTag findByNameAndTask(String metaTagName, int taskId) throws SQLException {
		Query query = entityManager.createQuery("select mt from MetaTag mt where mt.metaTagName = :metaTagName and mt.task.id = :taskId ")
				.setParameter("metaTagName", metaTagName)
				.setParameter("taskId", taskId);
		List<MetaTag> metaTags = query.getResultList();
		if(metaTags.size() > 0) return metaTags.get(0);
		return null;
	}
	
	
	public void create(MetaTag metaTag) throws SQLException {
		entityManager.merge(metaTag);
	}

	
	public void update(MetaTag metaTag) throws SQLException {
		entityManager.merge(metaTag);
	}

	
	public void delete(int metaTagId) throws SQLException {
		MetaTag metaTag = findById(metaTagId);
		entityManager.remove(metaTag);
	}
}
