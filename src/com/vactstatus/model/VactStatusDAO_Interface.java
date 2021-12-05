package com.vactstatus.model;

import java.util.List;

public interface VactStatusDAO_Interface {
	
	public void add(VactStatusVO vs);
	public void update(VactStatusVO vs);
	public void delete(Integer listid);
	public VactStatusVO findByPK(Integer listid);
	public List<VactStatusVO> getAll();
}
