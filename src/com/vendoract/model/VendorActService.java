package com.vendoract.model;

import java.sql.Date;
import java.util.List;

public class VendorActService {
	private VendorActDAO_interface dao;
	
	public VendorActService() {
		dao = new VendorActDAO();
	}
	
	public VendorActVO addVendorAct(String name, Integer vendorid, Date date, Integer amount, Integer siteid,
			Integer actid, Date rtlstart, Date rtlend, Date actstart, Date actend, Date tkstart,
			Date tkend, Integer price, String actcnt, byte[] img) {
		
		VendorActVO vact = new VendorActVO();
		
		vact.setName(name);
		vact.setVendorid(vendorid);
		vact.setAmount(amount);
		vact.setSiteid(siteid);
		vact.setActid(actid);
		vact.setRtlstart(rtlstart);
		vact.setRtlend(rtlend);
		vact.setActstart(actstart);
		vact.setActend(actend);
		vact.setTkstart(tkstart);
		vact.setTkend(tkend);
		vact.setPrice(price);
		vact.setActcnt(actcnt);
		vact.setImg(img);

		dao.add(vact);
		return vact;
	}
	
	public Integer addVendorAct(VendorActVO vactVO) {
		Integer id = dao.add(vactVO);
		System.out.println("service"+id);
		return id;
	}
	
	public VendorActVO updateVendorAct(Integer vactid, String name, Integer vendorid, Date date, Integer amount, Integer siteid,
			Integer actid, String progress, Date rtlstart, Date rtlend, Date actstart, Date actend, Date tkstart,
			Date tkend, Integer price, String actcnt, byte[] img) {
		
		VendorActVO vact = new VendorActVO();
		
		vact.setVactid(vactid);
		vact.setName(name);
		vact.setVendorid(vendorid);
		vact.setAmount(amount);
		vact.setSiteid(siteid);
		vact.setActid(actid);
		vact.setProgress(progress);
		vact.setRtlstart(rtlstart);
		vact.setRtlend(rtlend);
		vact.setActstart(actstart);
		vact.setActend(actend);
		vact.setTkstart(tkstart);
		vact.setTkend(tkend);
		vact.setPrice(price);
		vact.setActcnt(actcnt);
		vact.setImg(img);

		dao.update(vact);
		return vact;
	}
	
	public VendorActVO updateVendorAct(VendorActVO vact) {
		dao.update(vact);
		return vact;
	}
	
	public void deleteVendorAct(Integer vactid) {
		dao.delete(vactid);
	}
	
	public VendorActVO findOneVendorAct(Integer vactid) {
		return dao.findByPK(vactid);
	}
	
	public List<VendorActVO> getAll(){
		return dao.getAll();
	}
	
	public List<VendorActVO> findOccupied(Integer siteid, Integer year, Integer month){
		return dao.findOccupied(siteid, year, month);
	}
	
	public List<VendorActVO> findByVendorid(Integer vendorid){
		return dao.findByVendorid(vendorid);
	}
	
	public List<VendorActVO> findByTkDate(Integer vendorid, String tkstatus) {
		return dao.findByTkDate(vendorid, tkstatus);
	}
	
	public VendorActVO findLatestActivity(Integer vendorid) {
		return dao.findLatestActivity(vendorid);
	}
	
	public List<VendorActVO> findCanceledOrder(Integer vendorid) {
		return dao.findCanceledOrder(vendorid);
	}
	
	public List<VendorActVO> findByAllOnSale() {
		return dao.findByAllOnSale();
	}
	
	public void updateProgress(Integer vactid, Integer progress) {
		dao.updateProgress(vactid, progress);
	}
	
	public String checkProgress(Integer vactid) {
		return dao.checkProgress(vactid);
	}
	

	public Integer findNoCheck() {
		return dao.findNoCheck();
	}
	
	public void addNote(Integer vactid, String note) {
		dao.addNote(vactid, note);
	}
	
	public List<VendorActVO> findByNew() {
		return dao.findByNew();
	}
	
}
