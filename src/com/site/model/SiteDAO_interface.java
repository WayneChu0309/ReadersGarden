package com.site.model;

import java.util.List;

public interface SiteDAO_interface {
	
	public void add(SiteVO siteVO);
	public void update(SiteVO siteVO);
	public void delete(Integer siteid);
	public SiteVO findByPK(Integer siteid);
	public List<SiteVO> getAll();
	public void updateImg(Integer siteid, String path);
	
	//進階篩選
	public List<SiteVO> findOptions(Integer area, Integer daycost, Integer capacity);
	
}
