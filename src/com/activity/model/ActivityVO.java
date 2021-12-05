package com.activity.model;

import java.io.Serializable;

public class ActivityVO implements Serializable{
	private Integer actid;
	private String acttype;
	
	public ActivityVO() {
		
	}
	
	public ActivityVO(Integer actid, String acttype) {
		this.actid = actid;
		this.acttype = acttype;
	}
	
	
	public Integer getActid() {
		return actid;
	}
	public void setActid(Integer actid) {
		this.actid = actid;
	}
	public String getActtype() {
		return acttype;
	}
	public void setActtype(String acttype) {
		this.acttype = acttype;
	}

}
