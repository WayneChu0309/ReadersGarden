package com.ticket.model;

import java.util.List;

public class TestTicket {

	public static void main(String[] args) {
		TicketDAO dao = new TicketDAOImpl();

		// 新增
//		Ticket tk1 = new Ticket();
//		tk1.setORDERID(209);
//		tk1.setCUSTOMERID(34);
//		tk1.setTOTALCOST(2500);
//		tk1.setACTNO(304);
//		tk1.setORDERDATE(721);
//
//
//		dao.add(tk1);

		// 修改
		Ticket tk2 = new Ticket();
		tk2.setORDERID(205);
		tk2.setCUSTOMERID(35);
		tk2.setTOTALCOST(3000);
		tk2.setACTNO(305);
		tk2.setORDERDATE(722);
		dao.update(tk2);

		// 刪除
		dao.delete(205);

		// 查詢
		Ticket tk3 = dao.findByPK(202);
		System.out.print(tk3.getORDERID() + ",");
		System.out.print(tk3.getCUSTOMERID() + ",");
		System.out.print(tk3.getTOTALCOST() + ",");
		System.out.print(tk3.getACTNO() + ",");
		System.out.print(tk3.getORDERDATE() + ",");

		System.out.println("---------------------");

		// 查詢
		List<Ticket> list = dao.getAll();
		for (Ticket tk : list) {
			System.out.print(tk.getORDERID() + ",");
			System.out.print(tk.getCUSTOMERID() + ",");
			System.out.print(tk.getTOTALCOST() + ",");
			System.out.print(tk.getACTNO() + ",");
			System.out.print(tk.getORDERDATE() + ",");
	
			System.out.println();
		}
	}

}

