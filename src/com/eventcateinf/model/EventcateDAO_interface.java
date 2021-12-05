package com.eventcateinf.model;

import java.util.List;

public interface EventcateDAO_interface {
	
	public void add(Eventcate eventcate);
	public void update(Eventcate eventcate);
	public void delete(Integer eventcateid);
	public Eventcate findByPK(Integer eventcateid);
	public List<Eventcate> getAll();

}
