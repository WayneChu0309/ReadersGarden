package com.siteact.model;

import java.util.ArrayList;
import java.util.List;

import com.site.model.SiteVO;

public class TestSiteAct {
	
	public static void main(String[] args) {
		
		SiteActDAO_interface dao = new SiteActDAO();
		SiteActVO sa = new SiteActVO();
		
		// 新增
//		sa.setSiteid(12);
//		sa.setActid(1);
//		dao.add(sa);
		
		// 更新
//		sa.setSiteid(12);
//		sa.setActid(3);
//		sa.setListid(23);
//		
//		dao.update(sa);
		
		// 刪除
//		dao.delete(23);
		
//		// 查詢單筆
//		SiteActVO sa1 = dao.findByPK(22);
//		System.out.print(sa1.getListid()+", ");
//		System.out.print(sa1.getSiteid()+", ");
//		System.out.print(sa1.getActid());
//		System.out.println();
//		System.out.println("==============================");
		
////		// 查詢多筆
//		List <SiteActVO> sa2 = dao.getAll();
//		for(SiteActVO a : sa2) {
//			System.out.print(a.getListid()+", ");
//			System.out.print(a.getSiteid()+", ");
//			System.out.print(a.getActid());
//			System.out.println();
//			System.out.println("==============================");
//		}
		
		//依活動類型查詢場地
		List<SiteVO> sitelist = new ArrayList<>();
		sitelist = dao.getOneCategory(1);
		for(int i = 0; i < sitelist.size(); i++) {
			System.out.println(sitelist.get(i).getName());
		}
	}
}
