//package com.articleclass.model;
//
//import java.util.List;
//
//public class TestArticle_Class {
//
//	public static void main(String[] args) {
//		Article_ClassDAO dao = new Article_ClassDAOImpl();
//
//		// 新增
////		Article_Class ac1 = new Article_Class();
////		ac1.setACID(104);
////		ac1.setCLASSNAME("fanatsy");
////		
////		dao.add(ac1);
//
//		// 修改
//		Article_Class ac2 = new Article_Class();
//		ac2.setACID(105);
//		ac2.setCLASSNAME("fansy");
//
//		dao.update(ac2);
//
//		// 刪除
//		dao.delete(105);
//
//		// 查詢
//		Article_Class ac3 = dao.findByPK(100);
//		System.out.print(ac3.getACID() + ",");
//		System.out.print(ac3.getCLASSNAME() + ",");
//
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Article_Class> list = dao.getAll();
//		for (Article_Class ac : list) {
//			System.out.print(ac.getACID() + ",");
//			System.out.print(ac.getCLASSNAME() + ",");
//
//			System.out.println();
//		}
//	}
//
//}
//
