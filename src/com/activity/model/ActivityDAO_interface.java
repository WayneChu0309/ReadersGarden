package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {

	public void add(ActivityVO act);
	public void update(ActivityVO act);
	public void delete(Integer actid);
	public ActivityVO findByPK(Integer actid);
	public List<ActivityVO> getAll();
	
}
