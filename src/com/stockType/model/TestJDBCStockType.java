package com.stockType.model;

import java.io.File;
import java.util.List;

import static java.lang.System.out;

public class TestJDBCStockType {
	
	public static void main(String[] args) {
		StockTypeJDBCDAO dao = new StockTypeJDBCDAO();
		
		// 新增
//		StockTypeVO type1 = new StockTypeVO();
//		type1.setTypeName("測試類別");
//		dao.add(type1);
		
		// 修改
//		StockTypeVO type2 = new StockTypeVO();
//		type2.setTypeId(18);
//		type2.setTypeName("測試類別二");
//		dao.update(type2);
		
		// 刪除
//		dao.delete(18);
		
		// 單筆查詢
//		StockTypeVO type3 = dao.findByTypeId(1);
//		out.println("類別編號 : " + type3.getTypeId());
//		out.println("類別編號 : " + type3.getTypeName());
//		out.println("種類 : " + type3.getKind());
//		
//		// 全部查詢
//		List<StockTypeVO> typeList = dao.getMovie();
//		for (StockTypeVO type4 : typeList) {
//			out.println("類別編號 : " + type4.getTypeId());
//			out.println("類別編號 : " + type4.getTypeName());
//			out.println("種類 : " + type4.getKind());
//		}
		
		
		
		// 新增 類別進 MySQL , 結束後執行 TestJDBCStock新增資料
		String bPath = "C:\\博客來資料\\書籍資料";
		String mPath = "C:\\博客來資料\\電影資料";
		File bf = new File(bPath);
		File mf = new File(mPath);
		String bfdirs[] = bf.list();
		String mfdirs[] = mf.list();
		
		for (String book : bfdirs) {
			StockTypeVO type = new StockTypeVO();
			type.setTypeName(book);
			type.setKind("書籍");
			dao.add(type);
		}
		
		for (String movie : mfdirs) {
			StockTypeVO type = new StockTypeVO();
			type.setTypeName(movie);
			type.setKind("電影");
			dao.add(type);
		}
		
		
		
	}
	
}
