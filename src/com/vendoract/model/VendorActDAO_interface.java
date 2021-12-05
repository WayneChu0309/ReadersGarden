package com.vendoract.model;

import java.util.List;

public interface VendorActDAO_interface {
	
	public Integer add(VendorActVO vact);
	public void update(VendorActVO vact);
	public void delete(Integer vactid);
	public VendorActVO findByPK(Integer vactid);
	public List<VendorActVO> getAll();
	public void updateImg(Integer vactid, String path);
	
	//給月曆標示已租借:  根據場地(siteid) & 時間, 找廠商活動
	public List<VendorActVO> findOccupied(Integer siteid, Integer year, Integer month);

	//用廠商編號(vendorid)找活動 (時間排序desc)
	public List<VendorActVO> findByVendorid(Integer vendorid);
	
	//用售票日期找活動 (區分上架狀態)
	public List<VendorActVO> findByTkDate(Integer vendorid, String tkstatus);

	//用廠商編號找最新一筆活動
	public VendorActVO findLatestActivity(Integer vendorid);
	
	//用廠商編號找取消的訂單
	public List<VendorActVO> findCanceledOrder(Integer vendorid);
	
	//找所有售票中的活動
	public List<VendorActVO> findByAllOnSale();
	
	//更新訂單狀態
	public void updateProgress(Integer vactid,Integer progress);
	
	//抓單筆訂單狀態
	public String checkProgress(Integer vactid);
	

	// 抓管理員未審核訂單數量
	public Integer findNoCheck();
	

	//增加訂單備註
	public void addNote(Integer vactid, String note);
	
	// 最新兩筆, slide用
	public List<VendorActVO> findByNew();

}
