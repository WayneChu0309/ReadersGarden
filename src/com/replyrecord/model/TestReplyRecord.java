package com.replyrecord.model;

import java.util.List;

public class TestReplyRecord {

	public static void main(String[] args) {
		ReplyRecordDAO dao = new ReplyRecordDAOImpl();

		// 新增
//		Reply_Record rr1 = new Reply_Record();
//		rr1.setSENUM(5);
//		rr1.setACCTID(9);
//		rr1.setAID(50);
//		rr1.setREPDESCRIPT(("OPQ"));
//		
//		dao.add(rr1);

		// 修改
		Reply_Record rr1 = new Reply_Record();
		rr1.setSENUM(3);
		rr1.setACCTID(11);
		rr1.setAID(60);
		rr1.setREPDESCRIPT(("UDE"));
		
		dao.update(rr1);

		// 刪除
		dao.delete(6);

		// 查詢
		Reply_Record rr3 = dao.findByPK(1);
		System.out.print(rr3.getSENUM() + ",");
		System.out.print(rr3.getACCTID() + ",");
		System.out.print(rr3.getAID() + ",");
		System.out.print(rr3.getREPDESCRIPT() + ",");

		System.out.println("---------------------");

		// 查詢
		List<Reply_Record> list = dao.getAll();
		for (Reply_Record rr : list) {
			System.out.print(rr.getSENUM() + ",");
			System.out.print(rr.getACCTID() + ",");
			System.out.print(rr.getAID() + ",");
			System.out.print(rr.getREPDESCRIPT() + ",");
			
			System.out.println();
		}
	}

}
