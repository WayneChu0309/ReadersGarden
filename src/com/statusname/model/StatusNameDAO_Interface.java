package com.statusname.model;

import java.util.List;

public interface StatusNameDAO_Interface {

//	public void add(StatusNameVO snVO);
//	public void update(StatusNameVO snVO);
//	public void delete(Integer statusid);
	public StatusNameVO findByPK(Integer statusid);
	public List<StatusNameVO> getAll();
	
}
