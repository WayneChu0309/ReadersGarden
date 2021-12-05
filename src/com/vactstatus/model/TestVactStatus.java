package com.vactstatus.model;

import java.sql.Timestamp;
import java.util.List;

public class TestVactStatus {
	
public static void main(String[] args) {
		
	VactStatusDAO_Interface dao = new VactStatusDAO();
	VactStatusVO vs = new VactStatusVO();
	Long datetime = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(datetime);
		
		// 新增
//		vs.setVactid(12);
//		vs.setStatusid(1);
//		dao.add(vs);
		
		// 更新
//		vs.setVactid(10);
//		vs.setStatusid(1);
//		vs.setListid(22);
//		vs.setDate(timestamp);
//		
//		dao.update(vs);
		
		// 刪除
//		dao.delete(22);
		
//		// 查詢單筆
//		vs = dao.findByPK(21);
//		System.out.print(vs.getListid()+", ");
//		System.out.print(vs.getVactid()+", ");
//		System.out.print(vs.getStatusid()+", ");
//		System.out.println(vs.getDate());
//		System.out.println();
//		System.out.println("==============================");
		
////		// 查詢多筆
		List <VactStatusVO> sa2 = dao.getAll();
		for(VactStatusVO a : sa2) {
			System.out.print(a.getListid()+", ");
			System.out.print(a.getVactid()+", ");
			System.out.print(a.getStatusid()+", ");
			System.out.println(a.getDate());
			System.out.println();
			System.out.println("==============================");
		}
		
	}
	
}
