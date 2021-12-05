package com.partiinf.model;

public class Partiinf {

	private Integer eventid;
	private Integer memberid;
	
	public Partiinf() {			
	}
	
	
	public Integer geteventid() {
		return eventid;
	}

	public void seteventid(Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getmemberid() {
		return memberid;
	}

	public void setmemberid(Integer memberid) {
		this.memberid = memberid;
	}


	public Partiinf(Integer eventid, Integer memberid) {
		super();
		this.eventid = eventid;
		this.memberid = memberid;
	}
	
}
