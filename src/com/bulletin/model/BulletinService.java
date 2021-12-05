package com.bulletin.model;

import java.sql.Date;
import java.util.List;

public class BulletinService {
	
	private BulletinDAO_interface dao;
	
	public BulletinService () {
		dao = new BulletinJNDIDAO();
	}
	
	public BulletinVO addBulletin(Integer memberId, String buContent, Date buDate) {
		BulletinVO bulletinVO = new BulletinVO();
		bulletinVO.setMemberId(memberId);
		bulletinVO.setBulletinContent(buContent);
		bulletinVO.setBulletinDate(buDate);
		dao.add(bulletinVO);
		return bulletinVO;
	}
	
	public BulletinVO updateBulletin(Integer bulletinId, Integer memberId, String buContent, Date buDate) {
		BulletinVO bulletinVO = new BulletinVO();
		bulletinVO.setBulletinId(bulletinId);
		bulletinVO.setMemberId(memberId);
		bulletinVO.setBulletinContent(buContent);
		bulletinVO.setBulletinDate(buDate);
		dao.add(bulletinVO);
		return bulletinVO;
	}
	
	public void deleteBulletin(Integer bulletinId) {
		dao.delete(bulletinId);
	}
	
	public BulletinVO getOneBulletin(Integer bulletinId) {
		return dao.findByBulletinId(bulletinId);
	}
	
	public List<BulletinVO> getAll() {
		return dao.getAll();
	}
}
