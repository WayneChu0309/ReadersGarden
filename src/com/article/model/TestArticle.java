package com.article.model;

import java.util.List;

public class TestArticle {

	public static void main(String[] args) {
		ArticleDAO dao = new ArticleDAOImpl();

		// 新增
//		Article art1 = new Article();
//		art1.setAID(9);
//		art1.setACID(8);
//		art1.setACCTID(7);
//		art1.setANAME(new String("E"));
//		art1.setADESCRIPT(new String("AAA"));
//		art1.setAPD(105);
//		dao.add(art1);

		// 修改
//		Article art2 = new Article();
//		art2.setAID(99);
//		art2.setACID(88);
//		art2.setACCTID(87);
//		art2.setANAME(new String("F"));
//		art2.setADESCRIPT(new String("BBB"));
//		art2.setAPD(java.sql.Date.valueOf("2020-11-19"));
//		dao.update(art2);

		// 刪除
		dao.delete(99);

		// 查詢
		Article art3 = dao.findByPK(30);
		System.out.print(art3.getAID() + ",");
		System.out.print(art3.getACID() + ",");
		System.out.print(art3.getACCTID() + ",");
		System.out.print(art3.getANAME() + ",");
		System.out.print(art3.getADESCRIPT() + ",");
		System.out.print(art3.getAPD() + ",");
		System.out.println("---------------------");

		// 查詢
		List<Article> list = dao.getAll();
		for (Article art : list) {
			System.out.print(art.getAID() + ",");
			System.out.print(art.getACID() + ",");
			System.out.print(art.getACCTID() + ",");
			System.out.print(art.getANAME() + ",");
			System.out.print(art.getADESCRIPT() + ",");
			System.out.print(art.getAPD() + ",");
			System.out.println();
		}
	}

}


