package com.stockList.model;

import java.util.List;

import static java.lang.System.out;


public class TestJDBCStockList {
	
	public static void main(String[] args) {
		StockListJDBCDAO dao = new StockListJDBCDAO();
		
//		Integer a = dao.availStock(14);
//		System.out.println(a);
		// 總共 6222筆 book & movie data , 隨機產生各筆 1~3本紀錄 
		// 改成固定產生各筆2本
		for (int i = 1; i < 6223; i++) {
			for (int j = 0; j < 3; j++) {
				StockListVO st1 = new StockListVO();
				st1.setStockId(i);
				st1.setListStates(1);
				dao.add(st1);				
			}
		}
		
//		// 新增
//		StockListVO st1 = new StockListVO();
//		st1.setStockId(1);
//		st1.setListStates(1);
//		dao.add(st1);
		
//		// 修改
//		StockListVO st2 = new StockListVO();
//		st2.setListId(1);
//		st2.setStockId(1);
//		st2.setListStates(1);
//		dao.update(st2);
//		
//		// 刪除
//		dao.delete(10);
//		
//		// 單筆查詢
//		StockListVO st3 = dao.findByListId(1);
//		out.println("listId : " + st3.getListId());
//		out.println("stockId : " + st3.getStockId());
//		out.println("listStates" + st3.getListStates());
//		
//		// 全部查詢
//		List<StockListVO> stList = dao.getAll();
//		for (StockListVO st4 : stList) {
//			out.println("listId : " + st4.getListId());
//			out.println("stockId : " + st4.getStockId());
//			out.println("listStates" + st4.getListStates());
//		}
	}
}
