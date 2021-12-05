package com.event.model;

import java.util.List;

public interface EventDAO_interface {
	
	public void add(Event event);
	public void update(Event event);
	public void delete(Integer eventid);
	public Event findByPK(Integer eventid);
	public List<Event> getAll();
	public List<Event> getAllByCate(Integer eventcateid);
	// 未過期活動, 由新到舊
	public List<Event> getAllActiveDesc();
	// 未過期活動, 由舊到新
	public List<Event> getAllActiveAsc();
	// 找最新兩筆
	public List<Event> findByNew();
	// 找會員發起
	public List<Event> findByMember(Integer memberid);

}
