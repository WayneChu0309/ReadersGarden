package com.vendor.model;

import java.util.List;

public interface VendorDAOHibernate_interface {
	
	public abstract VendorBean add(VendorBean vendorBean);
	public abstract VendorBean update(VendorBean vendorBean);
	public abstract boolean delete(Integer vendorid);
	public abstract VendorBean findByVendorid(Integer vendorid);
	public abstract List<VendorBean> getAll();
	public abstract List<VendorBean> findByKeyword();
	
	
}
