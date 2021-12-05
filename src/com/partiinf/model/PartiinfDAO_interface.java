package com.partiinf.model;

import java.util.List;

public interface PartiinfDAO_interface {
	
	public void add(Partiinf partiinf);
	public void update(Partiinf partiinf);
	public void delete(Integer eventid);
	public Partiinf findByPK(Integer eventid);
	public List<Partiinf> getAll();
	
	// 找報名人數
	public Integer findByJoin(Integer eventid);
	
	// 找會員參加的揪團
	public List<Partiinf> findByMember(Integer memberid);
	
}
