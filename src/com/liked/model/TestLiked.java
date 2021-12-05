package com.liked.model;

import java.util.List;

public class TestLiked {

	public static void main(String[] args) {
		LikedDAO dao = new LikedDAOImpl();

		// 新增
//		Liked lk1 = new Liked();
//		lk1.setLIKEID(5);
//		lk1.setACCTID(10);
//		lk1.setAID(15);
//	
//		dao.add(lk1);

		// 修改
		Liked lk2 = new Liked();
		lk2.setLIKEID(6);
		lk2.setACCTID(9);
		lk2.setAID(16);
		dao.update(lk2);

		// 刪除
		dao.delete(6);

		// 查詢
		Liked lk3 = dao.findByPK(3);
		System.out.print(lk3.getLIKEID() + ",");
		System.out.print(lk3.getACCTID() + ",");
		System.out.print(lk3.getAID() + ",");
		System.out.println("---------------------");

		// 查詢
		List<Liked> list = dao.getAll();
		for (Liked lk : list) {
			System.out.print(lk.getLIKEID() + ",");
			System.out.print(lk.getACCTID() + ",");
			System.out.print(lk.getAID() + ",");
	
			System.out.println();
		}
	}

}
