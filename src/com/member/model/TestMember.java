package com.member.model;

import java.util.List;

//import JDBC_MEMBER.Member;



public class TestMember {

	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();

		// 新增
//		MemberVO mem1 = new MemberVO();
//		
//		mem1.setEmail("bbbb1234@gmail.com");
//		mem1.setName("林又家");
//		mem1.setBirthday(java.sql.Date.valueOf("1996-05-03"));
//		mem1.setPhoneNumber("0955648723");
//		mem1.setAddress("台北市北投區大業路40號");
//		mem1.setID("H156743219");
//		mem1.setPassword("cf56789");
//		mem1.setStatus("停用");
//		dao.add(mem1);
		
		// 修改
		
//		MemberVO mem2 = new MemberVO();
//		mem2.setNumber(6);
//		mem2.setEmail("vkj123@gmail.com");
//		mem2.setName("羅志揚");
//		mem2.setBirthday(java.sql.Date.valueOf("1996-01-01"));
//		mem2.setPhoneNumber("0912345678");
//		mem2.setAddress("台北市士林區仰德大道三段468號");
//		mem2.setID("S123456789");
//		mem2.setPassword("z123456");
//		mem2.setStatus("正常");
//		dao.update(mem2);
		
		// 刪除
//				dao.delete(6);
//				dao.delete(7);
		
		// 查詢
//				MemberVO mem3 = dao.findByPK(5);
//				System.out.print(mem3.getEmail() + ",");
//				System.out.print(mem3.getName() + ",");
//				System.out.print(mem3.getBirthday() + ",");
//				System.out.print(mem3.getPhoneNumber() + ",");
//				System.out.print(mem3.getAddress() + ",");
//				System.out.print(mem3.getID() + ",");
//				System.out.println(mem3.getPassword());
//				System.out.println(mem3.getStatus());
//				System.out.println("---------------------");

		
		// 查詢
				List<MemberVO> memList = dao.getAll();
				for (MemberVO mem : memList) {
					System.out.print(mem.getNumber() + ",");
					System.out.print(mem.getEmail() + ",");
					System.out.print(mem.getName() + ",");
					System.out.print(mem.getBirthday() + ",");
					System.out.print(mem.getPhoneNumber() + ",");
					System.out.print(mem.getAddress() + ",");
					System.out.print(mem.getID()+ ",");
					System.out.print(mem.getPassword()+ ",");
					System.out.print(mem.getStatus()+ ",");
					System.out.println();
				}
				

	
	}

}