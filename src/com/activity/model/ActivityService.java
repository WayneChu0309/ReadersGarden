package com.activity.model;

import java.util.List;

public class ActivityService{
	
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao = new ActivityDAO();
	}
	
	public ActivityVO addActivity(String acttype) {
		
		ActivityVO act = new ActivityVO();
		act.setActtype(acttype);
		dao.add(act);
		
		return act;
	}
	
	public ActivityVO updateActivity(Integer actid, String acttype) {
		ActivityVO act = new ActivityVO();
		
		act.setActid(actid);
		act.setActtype(acttype);
		dao.add(act);
		
		return act;
	}
	
	public void deleteActivity(Integer actid) {
		dao.delete(actid);
	}
	
	public ActivityVO findOneActivity(Integer actid) {
		return dao.findByPK(actid);
	}
	
	public List<ActivityVO> getAll(){
		return dao.getAll();
	}
	
	
	

}
