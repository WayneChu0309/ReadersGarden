package com.markRecord.model;

import java.util.List;




public class TestMarkRecord {

	public static void main(String[] args) {
		MarkRecordDAO dao = new MarkRecordDAO();

		// 新增
//		MarkRecordVO markrecord1 = new MarkRecordVO();
//		
//		markrecord1.setNumber(10);
//		markrecord1.setStockID(3);
//		markrecord1.setVacID(4);
//		;
//		
		
//		dao.add(markrecord1);
		
		// 修改
		
//		MarkRecordVO markrecord2 = new MarkRecordVO();
//		markrecord2.setSerialNumber(9);
//		markrecord2.setNumber(4);
//		markrecord2.setStockID(3);
//		markrecord2.setVacID(3);
//		
//		dao.update(markrecord2);
////		
//		// 刪除
//				dao.delete(9);

		
		// 查詢
				MarkRecordVO markrecord3 = dao.findByPK(5);
				System.out.print(markrecord3.getNumber() + ",");
				System.out.print(markrecord3.getStockID() + ",");
				System.out.print(markrecord3.getVacID() + ",");


		
		// 查詢
//				List<MarkRecordVO> markrecordList = dao.getAll();
//				for (MarkRecordVO markrecord : markrecordList) {
//					System.out.print(markrecord.getSerialNumber() + ",");
//					System.out.print(markrecord.getNumber() + ",");
//					System.out.print(markrecord.getStockID() + ",");
//					System.out.print(markrecord.getVacID() + ",");
//					
//					System.out.println();
//				}
				

	
	}

}