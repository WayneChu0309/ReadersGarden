package com.statusname.model;

import java.util.List;

public class StatusNameService {
	
	private StatusNameDAO_Interface dao;
	
	public StatusNameService() {
		dao = new StatusNameDAO();
	}
	
	public StatusNameVO findOneStatusName(Integer statusid) {
		return dao.findByPK(statusid);
	}
	public List<StatusNameVO> getAll(){
		return dao.getAll();
	}
}
