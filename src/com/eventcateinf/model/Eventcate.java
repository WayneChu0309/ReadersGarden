package com.eventcateinf.model;

import java.sql.Date;

public class Eventcate {
	private Integer eventcateid;
	private String eventcatename;
	
	public Eventcate() {
			
	}

	public Integer geteventcateid() {
		return eventcateid;
	}

	public void seteventcateid(Integer eventcateid) {
		this.eventcateid = eventcateid;
	}

	public String geteventcatename() {
		return eventcatename;
	}

	public void seteventcatename(String eventcatename) {
		this.eventcatename = eventcatename;
	}

	public Eventcate(Integer eventcateid, String eventcatename) {
		super();
		this.eventcateid = eventcateid;
		this.eventcatename = eventcatename;
	}
	
}
