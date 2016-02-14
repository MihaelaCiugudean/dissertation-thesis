package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.SkillDao;

@Repository
@Transactional
@Component("jpaSkillDao")
public class JpaSkillDao implements SkillDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Skill> findAll() throws SQLException {
		Query query = entityManager.createQuery("select s from Skill s");
		List<Skill> resultList = query.getResultList();
		return resultList;
	}


	public List<Skill> findAllForDeveloper(int developerId) throws SQLException {
		Query query = entityManager.createQuery("select s from Skill s where s.developer.id = :developerId")
				.setParameter("developerId", developerId);
		List<Skill> resultList = query.getResultList();
		return resultList;
	}
	
	
	public Skill findById(int skillId) throws SQLException {
		return entityManager.find(Skill.class, skillId);
	}

	
	public Skill findByName(String skillName) throws SQLException {
		Query query = entityManager.createQuery("select s from Skill s where s.skillName = :skillName")
				.setParameter("skillName", skillName);
		List<Skill> skills = query.getResultList();
		if(skills.size() >0) return skills.get(0);
		return null;
	}
	

	public Skill findByNameAndDeveloper(String skillName, int developerId) throws SQLException {
		Query query = entityManager.createQuery("select s from Skill s where s.skillName = :skillName and s.developer.id = :developerId ")
				.setParameter("skillName", skillName)
				.setParameter("developerId", developerId);
		List<Skill> skills = query.getResultList();
		if(skills.size() >0) return skills.get(0);
		return null;
	}
	
	
	public void create(Skill skill) throws SQLException {
		entityManager.merge(skill);
	}

	
	public void update(Skill skill) throws SQLException {
		entityManager.merge(skill);
	}

	
	public void delete(int skillId) throws SQLException {
		Skill skill = findById(skillId);
		entityManager.remove(skill);
	}
	
	
	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
