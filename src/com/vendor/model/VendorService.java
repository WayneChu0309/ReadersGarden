package com.vendor.model;

import java.util.List;

public class VendorService {
	private VendorDAO_interface dao;
	
	public VendorService() {
		dao = new VendorDAO();
	}
	
	public VendorVO addVendor(String company, Integer taxid, String name, String email, String password, String tel, String mobile, String addr) {
		VendorVO vendorVO = new VendorVO();
		vendorVO.setCompany(company);
		vendorVO.setTaxid(taxid);
		vendorVO.setName(name);
		vendorVO.setEmail(email);
		vendorVO.setPassword(password);
		vendorVO.setTel(tel);
		vendorVO.setMobile(mobile);
		vendorVO.setAddr(addr);
		dao.add(vendorVO);
		
		return vendorVO;
	}
	
	public VendorVO updateVendor(Integer vendorid, String company, Integer taxid, String name, String email, String password, String tel, String mobile, String addr) {
		VendorVO vendorVO = new VendorVO();
		vendorVO.setVendorid(vendorid);
		vendorVO.setCompany(company);
		vendorVO.setTaxid(taxid);
		vendorVO.setName(name);
		vendorVO.setEmail(email);
		vendorVO.setPassword(password);
		vendorVO.setTel(tel);
		vendorVO.setMobile(mobile);
		vendorVO.setAddr(addr);
		dao.update(vendorVO);
		
		return vendorVO;
	}
	
	public VendorVO updateVendor(VendorVO vendorVO) {
		dao.update(vendorVO);
		return vendorVO;
	}
	
	public void deleteVendor(Integer vendorid) {
		dao.delete(vendorid);
	}
	
	public VendorVO findOneVendor(Integer vendorid) {
		return dao.findByPK(vendorid);
	}
	
	public List<VendorVO> getAll(){
		return dao.getAll();
	}
	
	public List<VendorVO> findByKeyword(String keyword) {
		return dao.findByKeyword(keyword);
	}
	
	public void updateStatus(Integer vendorid, String status) {
		dao.updateStatus(vendorid, status);
	}
	
	public VendorVO findByEmail(String email) {
		return dao.findByEmail(email);
	}
}
