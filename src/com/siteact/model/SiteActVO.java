package com.siteact.model;

import java.io.Serializable;

public class SiteActVO implements Serializable{
	
	private Integer listid;
	private Integer siteid;
	private Integer actid;
	
	@Override
	public String toString() {
		return "SiteActVO [listid=" + listid + ", siteid=" + siteid + ", actid=" + actid + "]";
	}

	public SiteActVO() {
		
	}
	
	public SiteActVO(Integer listid, Integer siteid, Integer actid) {
		this.listid = listid;
		this.actid = actid;
	}
	
	public Integer getListid() {
		return listid;
	}
	public void setListid(Integer listid) {
		this.listid = listid;
	}
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}
	public Integer getActid() {
		return actid;
	}
	public void setActid(Integer actid) {
		this.actid = actid;
	}
	
}
