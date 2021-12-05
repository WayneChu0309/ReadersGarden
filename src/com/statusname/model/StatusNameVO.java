package com.statusname.model;

import java.io.Serializable;

public class StatusNameVO implements Serializable{
	
	protected Integer statusid;
	protected String status;
	
	public StatusNameVO() {
		
	}
	
	public StatusNameVO(Integer statusid, String status) {
		super();
		this.statusid = statusid;
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusNameVO [statusid=" + statusid + ", status=" + status + "]";
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
