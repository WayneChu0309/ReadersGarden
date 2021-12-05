package com.bulletin.model;


public class HibernateBulletinService {
	private BulletinDAO dao;
	
	public HibernateBulletinService(BulletinDAO dao) {
		this.dao = dao;
	}
	
	public BulletinBean add(BulletinBean buBean) {
		return dao.add(buBean);
	}
	
	public BulletinBean findByPk(Integer bulletinId) {
		return dao.findByPk(bulletinId);
	}
	
	public BulletinBean findByNew() {
		return dao.findByNew();
	}
}
