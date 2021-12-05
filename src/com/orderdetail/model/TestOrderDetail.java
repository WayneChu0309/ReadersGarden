package com.orderdetail.model;

import java.util.List;

public class TestOrderDetail {

	public static void main(String[] args) {
		OrderDetailDAOJDBC dao = new OrderDetailDAOImpl();

		// 新增
//		OrderDetail od1 = new OrderDetail();
//		od1.setTICKETID(50);
//		od1.setORDERID(204);
//		od1.setACTNO(303);
//
//		
//		dao.add(od1);

		// 修改
		OrderDetail od2 = new OrderDetail();
		od2.setTICKETID(60);
		od2.setORDERID(205);
		od2.setACTNO(304);
		dao.update(od2);

		// 刪除
		dao.delete(60);

		// 查詢
		OrderDetail od3 = dao.findByPK(30);
		System.out.print(od3.getTICKETID() + ",");
		System.out.print(od3.getORDERID() + ",");
		System.out.print(od3.getACTNO() + ",");
	
		System.out.println("---------------------");

		// 查詢
		List<OrderDetail> list = dao.getAll();
		for (OrderDetail od : list) {
			System.out.print(od.getTICKETID() + ",");
			System.out.print(od.getORDERID() + ",");
			System.out.print(od.getACTNO() + ",");
			
			System.out.println();
		}
	}

}

