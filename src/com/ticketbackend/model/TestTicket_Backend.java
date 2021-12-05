package com.ticketbackend.model;

import java.util.List;

public class TestTicket_Backend {

	public static void main(String[] args) {
		Ticket_BackendDAO dao = new Ticket_BackendDAOImpl();

		// 新增
//		Ticket_Backend tb1 = new Ticket_Backend();
//		tb1.setTICKETID(50);
//		tb1.setTOTALNUMT(100);
//		tb1.setTLEFT(26);
//		tb1.setTPRICE(300);
//		tb1.setACTID(44);
//
//		
//		dao.add(tb1);

		// 修改
		Ticket_Backend tb2 = new Ticket_Backend();
		tb2.setTICKETID(60);
		tb2.setTOTALNUMT(150);
		tb2.setTLEFT(24);
		tb2.setTPRICE(350);
		tb2.setACTID(45);
		dao.update(tb2);

		// 刪除
		dao.delete(60);

		// 查詢
		Ticket_Backend tb3 = dao.findByPK(10);
		System.out.print(tb3.getTICKETID() + ",");
		System.out.print(tb3.getTOTALNUMT() + ",");
		System.out.print(tb3.getTLEFT() + ",");
		System.out.print(tb3.getTPRICE() + ",");
		System.out.print(tb3.getACTID() + ",");

		System.out.println("---------------------");

		// 查詢
		List<Ticket_Backend> list = dao.getAll();
		for (Ticket_Backend tb : list) {
			System.out.print(tb.getTICKETID() + ",");
			System.out.print(tb.getTOTALNUMT() + ",");
			System.out.print(tb.getTLEFT() + ",");
			System.out.print(tb.getTPRICE() + ",");
			System.out.print(tb.getACTID() + ",");

			System.out.println();
		}
	}

}
