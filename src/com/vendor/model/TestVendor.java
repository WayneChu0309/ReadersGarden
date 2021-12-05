package com.vendor.model;

import java.util.List;

public class TestVendor {
	
	public static void main(String[] args) {
		
		VendorDAO_interface dao = new VendorDAO();
		
		
		// 新增add
//		VendorVO vd1 = new VendorVO();
//		vd1.setCompany("大世界股份有限公司");
//		vd1.setStatus("正常");
//		vd1.setAddr("台北市內湖區內湖路一段118號");
//		vd1.setEmail("taipei@gmail.com");
//		vd1.setName("台北人");
//		vd1.setPassword("123456");
//		vd1.setTaxid(22596822);
//		vd1.setTel("27930333");
//		vd1.setMobile("0972394957");
//		vd1.setVendorid(6);
//		dao.add(vd1);
		
		//更新update
//		VendorVO vd2 = new VendorVO();
//		vd2.setCompany("新世界有限公司");
//		vd2.setAddr("台北市內湖區內湖路一段118號");
//		vd2.setStatus("正常");
//		vd2.setEmail("taipei@gmail.com");
//		vd2.setName("台北人");
//		vd2.setPassword("123456");
//		vd2.setTaxid(22596822);
//		vd2.setTel("27930333");
//		vd2.setMobile("0972394957");
//		vd2.setVendorid(6);
//		dao.update(vd2);
		
		//刪除
//		dao.delete(7);
		
		//查詢
//		VendorVO vd3 = dao.findByPK(4);
//		
//		System.out.print(vd3.getVendorid() + ",");
//		System.out.print(vd3.getCompany() + ",");
//		System.out.print(vd3.getTaxid() + ",");
//		System.out.print(vd3.getName() + ",");
//		System.out.print(vd3.getEmail() + ",");
//		System.out.print(vd3.getPassword() + ",");
//		System.out.print(vd3.getTel() + ",");
//		System.out.print(vd3.getMobile() + ",");
//		System.out.println(vd3.getAddr());
//		System.out.println("---------------------");
//		
		//查詢全部
//		List<VendorVO> list = dao.getAll();
//		for(VendorVO v : list) {
//			System.out.print(v.getVendorid() + ",");
//			System.out.print(v.getCompany() + ",");
//			System.out.println(v.getStatus() + ",");
//			System.out.print(v.getTaxid() + ",");
//			System.out.print(v.getName() + ",");
//			System.out.print(v.getEmail() + ",");
//			System.out.print(v.getPassword() + ",");
//			System.out.print(v.getTel() + ",");
//			System.out.print(v.getMobile() + ",");
//			System.out.println(v.getAddr());
//			System.out.println("---------------------");
//		}
		
		//關鍵字查詢
		 
//		String keyword = "公司";
//		List<VendorVO> venlist = dao.findByKeyword(keyword);
//		System.out.println(venlist.toString());
		
		//變更帳戶狀態
		
//		dao.updateStatus(1, "停權");
		
		//find by email
		
		VendorVO vendorVO = dao.findByEmail("222@gmail.com");
		System.out.println(vendorVO.toString());
	}
}