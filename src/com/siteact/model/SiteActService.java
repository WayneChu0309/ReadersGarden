package com.siteact.model;

import java.util.List;

import com.activity.model.ActivityVO;
import com.site.model.SiteVO;

public class SiteActService {
	private SiteActDAO_interface dao;
	
	public SiteActService() {
		dao = new SiteActDAO();
	}
	
	public SiteActVO addSiteAct(Integer siteid, Integer actid) {
		SiteActVO sa = new SiteActVO();
		sa.setSiteid(siteid);
		sa.setActid(actid);
		
		dao.add(sa);
		return sa;
	}
	
	public SiteActVO updateSiteAct(Integer listid, Integer siteid, Integer actid) {
		SiteActVO sa = new SiteActVO();
		sa.setListid(listid);
		sa.setSiteid(siteid);
		sa.setActid(actid);
		
		dao.update(sa);
		return sa;
	}
	
	public void deleteSiteAct(Integer listid) {
		dao.delete(listid);
	}
	
	public SiteActVO findOneSiteAct(Integer listid) {
		return dao.findByPK(listid);
	}
	
	public List<SiteActVO> getAll(){
		return dao.getAll();
	}
	
	public List<SiteVO> getOneCategory(Integer actid){
		return dao.getOneCategory(actid);
	}
}
