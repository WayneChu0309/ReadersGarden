package com.library.model;

import static java.lang.System.out;

import java.util.List;

public class TestJDBCLibrary {

	public static void main(String[] args) {
		LibraryJDBCDAO dao = new LibraryJDBCDAO();
		
		// 新增
//		LibraryVO lib1 = new LibraryVO();
//		lib1.setLibraryName("Reader's Garden");
//		lib1.setLibraryAddress("台北市中山區南京東路三段219號5樓");
//		lib1.setLibraryTime("全年無休");
//		lib1.setLibraryTel("02-2712-0589");
//		lib1.setLibraryEmail("tibame@gmail.com");
//		dao.add(lib1);
		
		// 修改
//		LibraryVO lib2 = new LibraryVO();
//		lib2.setLibraryId(2);
//		lib2.setLibraryName("TFA103 第一組");
//		lib2.setLibraryAddress("富士山");
//		lib2.setLibraryTime("見紅休");
//		lib2.setLibraryTel("0987-654-321");
//		lib2.setLibraryEmail("TFA103@gmail.com");
//		dao.update(lib2);
		
		// 刪除
//		dao.delete(2);
		
		// 單筆查詢
		LibraryVO lib3 = dao.findByLibraryId(1);
		out.println("圖書館編號 : " + lib3.getLibraryId());
		out.println("圖書館名稱 : " + lib3.getLibraryName());
		out.println("圖書館地址 : " + lib3.getLibraryAddress());
		out.println("圖書館開放時間 : " + lib3.getLibraryTime());
		out.println("圖書館聯絡電話 : " + lib3.getLibraryTel());
		out.println("圖書館聯絡信箱 : " + lib3.getLibraryEmail());

		// 全部查詢
		List<LibraryVO> libList = dao.getAll();
		for(LibraryVO lib4 : libList) {
			out.println("圖書館編號 : " + lib4.getLibraryId());
			out.println("圖書館名稱 : " + lib4.getLibraryName());
			out.println("圖書館地址 : " + lib4.getLibraryAddress());
			out.println("圖書館開放時間 : " + lib4.getLibraryTime());
			out.println("圖書館聯絡電話 : " + lib4.getLibraryTel());
			out.println("圖書館聯絡信箱 : " + lib4.getLibraryEmail());
		}
	}
}
