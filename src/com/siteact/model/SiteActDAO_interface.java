package com.siteact.model;

import java.util.List;

import com.activity.model.ActivityVO;
import com.site.model.SiteVO;

public interface SiteActDAO_interface {

	public void add(SiteActVO sa);
	public void update(SiteActVO sa);
	public void delete(Integer listid);
	public SiteActVO findByPK(Integer listid);
	public List<SiteActVO> getAll();
	
	//用建議活動找場地
	public List<SiteVO> getOneCategory(Integer actid);
	
}
