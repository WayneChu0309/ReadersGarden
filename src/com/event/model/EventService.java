package com.event.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EventService {

	private EventDAO_interface dao;

	public EventService() {
		dao = new EventDAO();
	}

	public Event addEvent(Integer eventcateid, Integer memberid, Integer capacity, 
						String eventname, String eventdescription, Timestamp eventstart, Timestamp eventend, 
						Timestamp eventappstart, Timestamp eventappend, Integer eventstatus) {

		Event event = new Event();

		event.setEventcateid(eventcateid);
		event.setMemberid(memberid);
		event.setCapacity(capacity);
		event.setEventname(eventname);
		event.setEventdescription(eventdescription);
		event.setEventstart(eventstart);
		event.setEventend(eventend);
		event.setEventappstart(eventappstart);
		event.setEventappend(eventappend);
		event.setEventstatus(eventstatus);	
		dao.add(event);

		return event;
	}

	public Event updateEvent(Integer eventid, Integer eventcateid, Integer memberid, Integer capacity, 
			String eventname, String eventdescription, Timestamp eventstart, Timestamp eventend, 
			Timestamp eventappstart, Timestamp eventappend, Integer eventstatus) {

		Event event = new Event();

		event.setEventid(eventid);
		event.setEventcateid(eventcateid);
		event.setMemberid(memberid);
		event.setCapacity(capacity);
		event.setEventname(eventname);
		event.setEventdescription(eventdescription);
		event.setEventstart(eventstart);
		event.setEventend(eventend);
		event.setEventappstart(eventappstart);
		event.setEventappend(eventappend);
		event.setEventstatus(eventstatus);
		dao.update(event);

		return event;
	}

	public void deleteEvent(Integer eventid) {
		dao.delete(eventid);
	}

	public Event getOneEvent(Integer eventid) {
		return dao.findByPK(eventid);
	}

	public List<Event> getAll() {
		return dao.getAll();
	}
	
	public List<Event> getAllByCate(Integer eventcateid) {
		return dao.getAllByCate(eventcateid);
	}
	
	public List<Event> getAllActiveDesc(){
		return dao.getAllActiveDesc();
	}
	
	public List<Event> getAllActiveAsc(){
		return dao.getAllActiveAsc();
	}
	
	public List<Event> findByNew() {
		return dao.findByNew();
	}
	
	public List<Event> findByMember(Integer memberid){
		return dao.findByMember(memberid);
	}

}
