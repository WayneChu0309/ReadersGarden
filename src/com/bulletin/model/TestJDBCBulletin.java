package com.bulletin.model;

import static java.lang.System.out;
import java.util.List;

public class TestJDBCBulletin {
	
	public static void main(String[] args) {
		
		BulletinJDBCDAO dao = new BulletinJDBCDAO();
		
		// 新增
//		BulletinVO bul1 = new BulletinVO();
//		bul1.setMemberId(1);
//		bul1.setBulletinDate(java.sql.Date.valueOf("2016-01-01"));
//		dao.add(bul1);
		
		// 修改
//		BulletinVO bul2 = new BulletinVO();
//		bul2.setBulletinId(1);
//		bul2.setMemberId(1);
//		bul2.setBulletinContent("PETER個人展已經結束, 下次請早!!!");
//		bul2.setBulletinDate(java.sql.Date.valueOf("2016-01-01"));
//		dao.update(bul2);
		
		// 刪除
//		dao.delete(6);
		
		// 單筆查詢
		BulletinVO bul3 = dao.findByBulletinId(1);
		out.println("公告編號 : " + bul3.getBulletinId());
		out.println("管理員編號 : " + bul3.getMemberId());
		out.println("公告內容 : " + bul3.getBulletinContent());
		out.println("公告日期 : " + bul3.getBulletinDate());
		
		// 全部查詢
		List<BulletinVO> bulList = dao.getAll();
		for (BulletinVO bul4 : bulList) {
			out.println("公告編號 : " + bul4.getBulletinId());
			out.println("管理員編號 : " + bul4.getMemberId());
			out.println("公告內容 : " + bul4.getBulletinContent());
			out.println("公告日期 : " + bul4.getBulletinDate());
		}
	}
}
