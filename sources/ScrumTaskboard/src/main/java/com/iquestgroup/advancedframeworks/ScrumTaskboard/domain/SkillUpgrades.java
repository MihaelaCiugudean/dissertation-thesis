package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import java.util.Map;

public class SkillUpgrades {

	private int id;
	
	private Task task;
	private Map<String, SkillPercentage> upgrades;
	
	
	public SkillUpgrades() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}


	public Map<String, SkillPercentage> getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(Map<String, SkillPercentage> upgrades) {
		this.upgrades = upgrades;
	}
}
