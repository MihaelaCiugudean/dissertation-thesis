package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.SkillItemDao;

@Repository
@Transactional
@Component("jpaSkilltemDao")
public class JpaSkillItemDao implements SkillItemDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<SkillItem> findAll() throws SQLException {
		Query query = entityManager.createQuery("select si from SkillItem si");
		List<SkillItem> resultList = query.getResultList();
		return resultList;
	}

	
	public SkillItem findById(int skillItemId) throws SQLException {
		return entityManager.find(SkillItem.class, skillItemId);
	}

	
	public SkillItem findByName(String skillItemName) throws SQLException {
		Query query = entityManager.createQuery("select si from SkillItem si where si.skillItemName = :skillItemName")
				.setParameter("skillItemName", skillItemName);
		List<SkillItem> skillItems = query.getResultList();
		if(skillItems.size() >0) return skillItems.get(0);
		return null;
	}
	

	public void create(SkillItem skillItem) throws SQLException {
		entityManager.merge(skillItem);
	}

	
	public void update(SkillItem skillItem) throws SQLException {
		entityManager.merge(skillItem);
	}
	

	public void delete(int skillItemId) throws SQLException {
		SkillItem skillItem = findById(skillItemId);
		entityManager.remove(skillItem);
	}
	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
