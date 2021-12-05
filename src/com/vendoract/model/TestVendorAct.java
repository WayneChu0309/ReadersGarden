package com.vendoract.model;

import java.util.Date;
import java.util.List;

public class TestVendorAct {
	public static void main(String[] args) {
		
		VendorActDAO_interface dao = new VendorActDAO();		
		System.out.println(dao.findNoCheck());
		// 新增add
		VendorActVO vact = new VendorActVO();
//		vact.setName("華格納歌劇音樂會 《唐懷瑟》");
//		vact.setVendorid(2);
//		vact.setAmount(45000);
//		vact.setSiteid(11);
//		vact.setActid(6);
//		vact.setProgress("完成付款");
//		vact.setRtlstart(java.sql.Date.valueOf("2021-11-19"));
//		vact.setRtlend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setActstart(java.sql.Date.valueOf("2021-11-19"));
//		vact.setActend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setTkstart(java.sql.Date.valueOf("2021-08-15"));
//		vact.setTkend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setPrice(150);
//		vact.setImg("C:\\Users\\Tibame_T14\\OneDrive\\桌面\\activity\\GetImg.jfif");
//		vact.setActcnt("來自海內外的創作者透過1秒24格的連續圖像，以3~20分鐘不等的短片，訴說溫馨童趣、奇思妙想的個人故事，到家庭關懷、社會、生態的議題。秋季的RG放映室帶你看見動畫的多元面向。");
//		Integer vactid = dao.add(vact);
//		VendorActService vactSvc = new VendorActService();
//		Integer vactid = vactSvc.addVendorAct(vact);
//		System.out.println(vactid);
		
		//更新update
//		VendorAct vact = new VendorAct();
//		vact.setVactid(10);
//		vact.setName("《唐懷瑟》");
//		vact.setVendorid(3);
//		vact.setAmount(65000);
//		vact.setSiteid(11);
//		vact.setActid(4);
//		vact.setProgress("完成");
//		vact.setRtlstart(java.sql.Date.valueOf("2021-11-19"));
//		vact.setRtlend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setActstart(java.sql.Date.valueOf("2021-11-19"));
//		vact.setActend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setTkstart(java.sql.Date.valueOf("2021-08-15"));
//		vact.setTkend(java.sql.Date.valueOf("2021-11-21"));
//		vact.setPrice(150);
//		vact.setActcnt("來自海內外的創作者透過1秒24格的連續圖像，以3~20分鐘不等的短片，訴說溫馨童趣、奇思妙想的個人故事，到家庭關懷、社會、生態的議題。秋季的RG放映室帶你看見動畫的多元面向。");
//		vact.setImg("src/img/site02.jpg");
//		
//		dao.update(vact);
		
		//刪除
//		dao.delete(11);
		
		//查詢
//		VendorActVO vact = dao.findByPK(1);
//		
//		System.out.print(vact.getVactid() + ",");
//		System.out.print(vact.getName() + ",");
//		System.out.print(vact.getVendorid() + ",");
//		System.out.print(vact.getDate() + ",");
//		System.out.print(vact.getAmount() + ",");
//		System.out.print(vact.getSiteid() + ",");
//		System.out.print(vact.getActid() + ",");
//		System.out.print(vact.getProgress() + ",");
//		System.out.print(vact.getRtlstart() + ",");
//		System.out.print(vact.getRtlend() + ",");
//		System.out.print(vact.getActstart() + ",");
//		System.out.print(vact.getActend() + ",");
//		System.out.print(vact.getTkstart() + ",");
//		System.out.print(vact.getTkend() + ",");
//		System.out.print(vact.getPrice() + ",");
//		System.out.print(vact.getActcnt() + ",");
//		System.out.print(vact.getImg());
//		System.out.println();
//		System.out.println("-----------------------------------------------------------");
//		
		//查詢全部
//		List<VendorActVO> list = dao.getAll();
//		
//		for(VendorActVO v : list) {
//			System.out.print(v.getVactid() + ",");
//			System.out.print(v.getName() + ",");
//			System.out.print(v.getVendorid() + ",");
//			System.out.print(v.getDate() + ",");
//			System.out.print(v.getAmount() + ",");
//			System.out.print(v.getSiteid() + ",");
//			System.out.print(v.getActid() + ",");
//			System.out.print(v.getProgress() + ",");
//			System.out.print(v.getRtlstart() + ",");
//			System.out.print(v.getRtlend() + ",");
//			System.out.print(v.getActstart() + ",");
//			System.out.print(v.getActend() + ",");
//			System.out.print(v.getTkstart() + ",");
//			System.out.print(v.getTkend() + ",");
//			System.out.print(v.getPrice() + ",");
//			System.out.print(v.getActcnt() + ",");
//			System.out.print(v.getImg());
//			System.out.println();
//			System.out.println("-----------------------------------------------------------");
//		
//		}
		
//		更新圖片
		dao.updateImg(1, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract01.jpg");
		dao.updateImg(2, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract02.jpg");
		dao.updateImg(3, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract03.jpg");
		dao.updateImg(4, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract04.jpg");
		dao.updateImg(5, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract05.png");
		dao.updateImg(6, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract06.jpg");
		dao.updateImg(7, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract07.jfif");
		dao.updateImg(8, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract08.jpg");
		dao.updateImg(9, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract09.jpg");
		dao.updateImg(10, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract10.jpg");
		dao.updateImg(11, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract11.jpg");
		dao.updateImg(12, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract12.jpg");
		dao.updateImg(13, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract13.jpg");
		dao.updateImg(14, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract14.jpg");
		dao.updateImg(15, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract15.jpg");
		dao.updateImg(16, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract16.jpg");
		dao.updateImg(17, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract17.jpg");
		dao.updateImg(18, "C:\\TFA103_Workspace\\TFA103_01\\WebContent\\vendor\\image\\vendoract\\vendoract18.jpg");
//		
		
		//根據siteid & 年月 查詢
//		List<VendorActVO> vactlist = dao.findOccupied(7, 2021, 11);
//		for(VendorActVO vactVO : vactlist) {
//			System.out.println(vactVO.toString());
//		}
		
		//根據vendorid & tkdate 查詢
//		List<VendorActVO> vactlist = dao.findByTkDate(1, "已結束");
//		for(VendorActVO vactVO : vactlist) {
//			System.out.println(vactVO.toString());
//		}		
		
		//根據vendorid 查最新一筆活動
//		VendorActVO vact = dao.findLatestActivity(1);
//		System.out.println(vact.toString());
		
		//根據vendorid 查取消訂單
//		List<VendorActVO> vact = dao.findCanceledOrder(1);
//		System.out.println(vact.toString());
		
		//更新訂單進度
//		dao.updateProgress(1,1);

		
		//增加訂單備註
		dao.addNote(1, "新訂單");

	}

}
