package com.borrow.model;

import static java.lang.System.out;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestJDBCBorrow {
	
	public static void main(String[] args) throws ParseException {
		BorrowJDBCDAO dao = new BorrowJDBCDAO();
		
//      測試 notOrder for interface
//		Integer a = dao.notOrder(2);
//		System.out.println(a);
//		BorrowVO vo = dao.findByNumberPreOrder(1, 1);
//		System.out.println(vo);
		
//		String date = "2021-02-3";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		// 30天, 最後要加上 L 否則超過 int 上限會變負數
//		long thirtyDays = 30 * 24 * 60 * 60 * 1000L;
//		
//		// 預訂日
//		long preBoDate = df.parse(date).getTime();
//		// 轉成SQL格式
//		java.sql.Date formatDate = new java.sql.Date(preBoDate);
//		
//		// 預計歸還日, 預訂日 + 30天
//		long preReDate = preBoDate + thirtyDays;
//		// 轉成SQL格式
//		java.sql.Date formatDate2 = new java.sql.Date(preReDate);
//		// 新增
//		for (int i = 4; i <= 5; i++) {
//			for (int j = 60; j < 80;j++) {
//				BorrowVO bo1 = new BorrowVO();
//				bo1.setMemberId(i);
//				bo1.setStockId(j);
//				bo1.setPreBoDate(formatDate);
//				bo1.setPreReDate(formatDate2);
//				bo1.setBoStates(1);
//				dao.add(bo1);							
//			}
//		}
		
//		bo1.setListId(2);
//     假資料先塞以前日期
//		bo1.setActBoDate(java.sql.Date.valueOf("2016-01-01"));
//		bo1.setPreReDate(java.sql.Date.valueOf("2016-01-01"));
//		bo1.setActReDate(java.sql.Date.valueOf("2016-01-01"));
//		bo1.setScoreContent("Fantastic");
//		bo1.setScoreDate(java.sql.Date.valueOf("2016-01-01"));
//		bo1.setScore(1);
		
		
		// 修改
//		BorrowVO bo2 = new BorrowVO();
//		bo2.setMemberId(2);
//		bo2.setStockId(2);
//		bo2.setListId(2);
//		// 假資料先塞以前日期
//		bo2.setPreBoDate(java.sql.Date.valueOf("2016-01-01"));
//		bo2.setActBoDate(java.sql.Date.valueOf("2016-01-01"));
//		bo2.setPreReDate(java.sql.Date.valueOf("2016-01-01"));
//		bo2.setActReDate(java.sql.Date.valueOf("2016-01-01"));
//		bo2.setScoreContent("Fantastic");
//		bo2.setScoreDate(java.sql.Date.valueOf("2016-01-01"));
//		bo2.setScore(1);
//		bo2.setBoStates(3);
//		bo2.setBorrowId(1);
//		dao.update(bo2);
		
		// 刪除
//		dao.delete(2);
		
		// 單筆查詢
//		BorrowVO bo3 = dao.findByBorrowId(3);
//		out.println("借閱ID : " + bo3.getBorrowId());
//		out.println("會員ID : " + bo3.getMemberId());
//		out.println("館藏ID : " + bo3.getStockId());
//		out.println("館藏清單ID : " + bo3.getListId());
//		out.println("預訂日 : " + bo3.getPreBoDate());
//		out.println("借閱日 : " + bo3.getActBoDate());
//		out.println("預還日 : " + bo3.getPreReDate());
//		out.println("歸還日 : " + bo3.getActReDate());
//		out.println("評價 : "  + bo3.getScoreContent());
//		out.println("評分 : " + bo3.getScore());
//		out.println("評分日 : " + bo3.getScoreDate());
//		out.println("借閱狀態 : " + bo3.getBoStates());
//		
//		// 全部查詢
//		List<BorrowVO> boList = dao.getAll();
//		for (BorrowVO bo4 : boList) {
//			out.println("借閱ID : " + bo4.getBorrowId());
//			out.println("會員ID : " + bo4.getMemberId());
//			out.println("館藏ID : " + bo4.getStockId());
//			out.println("館藏清單ID : " + bo4.getListId());
//			out.println("預訂日 : " + bo4.getPreBoDate());
//			out.println("借閱日 : " + bo4.getActBoDate());
//			out.println("預還日 : " + bo4.getPreReDate());
//			out.println("歸還日 : " + bo4.getActReDate());
//			out.println("評價 : "  + bo4.getScoreContent());
//			out.println("評分 : " + bo4.getScore());
//			out.println("評分日 : " + bo4.getScoreDate());
//			out.println("借閱狀態 : " + bo4.getBoStates());
//		}
	}
}
