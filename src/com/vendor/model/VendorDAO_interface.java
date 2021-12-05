package com.vendor.model;

import java.util.*;

public interface VendorDAO_interface {
	
	public void add(VendorVO vendorVO);
	public void update(VendorVO vendorVO);
	public void delete(Integer vendorid);
	public VendorVO findByPK(Integer vendorid);
	public List<VendorVO> getAll();
	public List<VendorVO> findByKeyword(String keyword);
	
	
//  更新帳戶狀態
	public void updateStatus(Integer vendorid, String status);
	
//  用EMAIL找VENDOR
	public VendorVO findByEmail(String email);
	
}
