package com.vactstatus.model;

import java.sql.Timestamp;

public class VactStatusVO {
	
	private Integer listid;
	private Timestamp date;
	private Integer vactid;
	private Integer statusid;
	
	public VactStatusVO(Integer listid, Timestamp date, Integer vactid, Integer statusid) {
		super();
		this.listid = listid;
		this.date = date;
		this.vactid = vactid;
		this.statusid = statusid;
	}

	public VactStatusVO() {
		super();
	}

	@Override
	public String toString() {
		return "VactStatusVO [listid=" + listid + ", date=" + date + ", vactid=" + vactid + ", statusid=" + statusid
				+ "]";
	}

	public Integer getListid() {
		return listid;
	}

	public void setListid(Integer listid) {
		this.listid = listid;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getVactid() {
		return vactid;
	}

	public void setVactid(Integer vactid) {
		this.vactid = vactid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	
}
