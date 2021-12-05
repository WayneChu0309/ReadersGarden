package com.site.model;

import java.util.List;

public class SiteService {
	
	private SiteDAO_interface dao;
	
	public SiteService() {
		dao = new SiteDAO();
	}
	
	public SiteVO addSite(String name, Integer area, Integer daycost, Integer capacity, byte[] img) {
		SiteVO siteVO = new SiteVO();
		siteVO.setName(name);
		siteVO.setArea(area);
		siteVO.setDaycost(daycost);
		siteVO.setCapacity(capacity);
		siteVO.setImg(img);
		
		dao.add(siteVO);
		return siteVO;
	}
	
	public SiteVO updateSite(Integer siteid ,String name, Integer area, Integer daycost, Integer capacity, byte[] img) {
		SiteVO siteVO = new SiteVO();
		siteVO.setSiteid(siteid);
		siteVO.setName(name);
		siteVO.setArea(area);
		siteVO.setDaycost(daycost);
		siteVO.setCapacity(capacity);
		siteVO.setImg(img);
		
		dao.update(siteVO);
		return siteVO;
	}
	
	public void deleteSite(Integer siteid) {
		dao.delete(siteid);
	}
	
	public SiteVO findOneSite(Integer siteid) {
		return dao.findByPK(siteid);
	}
	
	public List<SiteVO> getAll(){
		return dao.getAll();
	}
	
	public List<SiteVO> findOptions(Integer area, Integer daycost, Integer capacity) {
		return dao.findOptions(area, daycost, capacity);
	}
	
}
