package com.site.model;

import java.util.List;

public class TestSite {
	
	public static void main(String[] args) {
		
		SiteDAO_interface dao = new SiteDAO();
		SiteVO siteVO = new SiteVO();
		
		// 新增
//		site.setName("第一廣場");
//		site.setArea(50);
//		site.setCapacity(100);
//		site.setDaycost(50000);
//		site.setImg("src/img/site01.jpg");
//		
//		dao.add(site);
		
		// 更新
//		site.setSiteid(12);
//		site.setName("第二廣場");
//		site.setArea(25);
//		site.setCapacity(100);
//		site.setDaycost(50000);
//		site.setImg("src/img/site11.jpg");
//		
//		dao.update(site);
		
		// 刪除
//		dao.delete(13);
		
//		// 查詢單筆
//		Site site1 = dao.findByPK(12);
//		System.out.print(site1.getSiteid()+ ", ");
//		System.out.print(site1.getName()+ ", ");
//		System.out.print(site1.getArea()+ ", ");
//		System.out.print(site1.getCapacity()+ ", ");
//		System.out.print(site1.getDaycost()+ ", ");
//		System.out.print(site1.getImg());
//		System.out.println();
//		System.out.println("==============================");
//		
////		// 查詢多筆
		List <SiteVO> site2 = dao.getAll();
//		for(SiteVO s : site2) {
//			System.out.print(s.getSiteid()+ ", ");
//			System.out.print(s.getName()+ ", ");
//			System.out.print(s.getArea()+ ", ");
//			System.out.print(s.getCapacity()+ ", ");
//			System.out.print(s.getDaycost()+ ", ");
//			System.out.print(s.getImg());
//			System.out.println();
//			System.out.println("==============================");
//		}
//		
		// 更新圖片
		for(int i = 1; i <= site2.size(); i++) {
			if(i < 10) {

				dao.updateImg(i, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\site\\image\\site\\site0"+ i +".jpg");
			} else {
				dao.updateImg(i, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\site\\image\\site\\site"+ i +".jpg");

			}
		}
		
		// 進階篩選
//		List <SiteVO> site3 = dao.findOptions(30, 200000, 30);
//		for(SiteVO s : site3) {

////			System.out.print(s.getSiteid()+ ", ");
////			System.out.print(s.getName()+ ", ");
////			System.out.print(s.getArea()+ ", ");
////			System.out.print(s.getCapacity()+ ", ");
////			System.out.print(s.getDaycost()+ ", ");
////			System.out.print(s.getImg());
////			System.out.println();

//			System.out.print(s.getSiteid()+ ", ");
//			System.out.print(s.getName()+ ", ");
//			System.out.print(s.getArea()+ ", ");
//			System.out.print(s.getCapacity()+ ", ");
//			System.out.print(s.getDaycost()+ ", ");
//			System.out.print(s.getImg());
//			System.out.println();

//			System.out.println("==============================");
//			System.out.println(s.toString());
//		}
		
	}
}
