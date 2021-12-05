package com.report.model;

import java.util.List;

public class TestReport {

	public static void main(String[] args) {
		ReportDAO dao = new ReportDAOImpl();

		// 新增
//		Report rep1 = new Report();
//		rep1.setREPID(5);
//		rep1.setACCTID(10);
//		rep1.setAID(15);
//	
//		dao.add(rep1);

		// 修改
		Report rep2 = new Report();
		rep2.setREPID(6);
		rep2.setACCTID(9);
		rep2.setAID(16);
		dao.update(rep2);

		// 刪除
		dao.delete(6);

		// 查詢
		Report rep3 = dao.findByPK(11);
		System.out.print(rep3.getREPID() + ",");
		System.out.print(rep3.getACCTID() + ",");
		System.out.print(rep3.getAID() + ",");
		System.out.println("---------------------");

		// 查詢
		List<Report> list = dao.getAll();
		for (Report lk : list) {
			System.out.print(lk.getREPID() + ",");
			System.out.print(lk.getACCTID() + ",");
			System.out.print(lk.getAID() + ",");
	
			System.out.println();
		}
	}

}
