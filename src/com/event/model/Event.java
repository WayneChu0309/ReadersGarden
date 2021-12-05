package com.event.model;

import java.sql.Date;
import java.io.Serializable;

public class Event implements Serializable{
	private Integer eventid;
	private Integer eventcateid;
	private Integer memberid;
	private Integer capacity;
	private String eventname;
	private String eventdescription;
	private java.sql.Timestamp eventstart;
	private java.sql.Timestamp eventend;
	private java.sql.Timestamp eventappstart;
	private java.sql.Timestamp eventappend;
	private Integer eventstatus;
	
	
	public Event() {
		super();
	}


	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", eventcateid=" + eventcateid + ", memberid=" + memberid + ", capacity="
				+ capacity + ", eventname=" + eventname + ", eventdescription=" + eventdescription + ", eventstart="
				+ eventstart + ", eventend=" + eventend + ", eventappstart=" + eventappstart + ", eventappend="
				+ eventappend + ", eventstatus=" + eventstatus + "]";
	}


	public Event(Integer eventid, Integer eventcateid, Integer memberid, Integer capacity, String eventname,
			String eventdescription, java.sql.Timestamp eventstart, java.sql.Timestamp eventend, java.sql.Timestamp eventappstart, java.sql.Timestamp eventappend,
			Integer eventstatus) {
		super();
		this.eventid = eventid;
		this.eventcateid = eventcateid;
		this.memberid = memberid;
		this.capacity = capacity;
		this.eventname = eventname;
		this.eventdescription = eventdescription;
		this.eventstart = eventstart;
		this.eventend = eventend;
		this.eventappstart = eventappstart;
		this.eventappend = eventappend;
		this.eventstatus = eventstatus;
	}


	public Integer getEventid() {
		return eventid;
	}


	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}


	public Integer getEventcateid() {
		return eventcateid;
	}


	public void setEventcateid(Integer eventcateid) {
		this.eventcateid = eventcateid;
	}


	public Integer getMemberid() {
		return memberid;
	}


	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public String getEventname() {
		return eventname;
	}


	public void setEventname(String eventname) {
		this.eventname = eventname;
	}


	public String getEventdescription() {
		return eventdescription;
	}


	public void setEventdescription(String eventdescription) {
		this.eventdescription = eventdescription;
	}


	public java.sql.Timestamp getEventstart() {
		return eventstart;
	}


	public void setEventstart(java.sql.Timestamp eventstart) {
		this.eventstart = eventstart;
	}


	public java.sql.Timestamp getEventend() {
		return eventend;
	}


	public void setEventend(java.sql.Timestamp eventend) {
		this.eventend = eventend;
	}


	public java.sql.Timestamp getEventappstart() {
		return eventappstart;
	}


	public void setEventappstart(java.sql.Timestamp eventappstart) {
		this.eventappstart = eventappstart;
	}


	public java.sql.Timestamp getEventappend() {
		return eventappend;
	}


	public void setEventappend(java.sql.Timestamp eventappend) {
		this.eventappend = eventappend;
	}


	public Integer getEventstatus() {
		return eventstatus;
	}


	public void setEventstatus(Integer eventstatus) {
		this.eventstatus = eventstatus;
	}




}
