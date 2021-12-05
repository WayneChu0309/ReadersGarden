package com.vendor.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class VendorDAOHibernate implements VendorDAOHibernate_interface{

	private SessionFactory sessionFactory;
	
	public VendorDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public VendorBean add(VendorBean vendorBean) {
		if(vendorBean != null) {
			this.getSession().save(vendorBean);
			return vendorBean;
		}
		return null;
	}

	@Override
	public VendorBean update(VendorBean vendorBean) {
		if(vendorBean != null) {
			VendorBean temp = this.getSession().get(VendorBean.class, vendorBean.getVendorid());
			if(temp != null){
				return (VendorBean) this.getSession().merge(vendorBean);
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer vendorid) {
		if(vendorid != null) {
			VendorBean temp = this.getSession().get(VendorBean.class, vendorid);
			if(temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		} 
		return false;
	}

	@Override
	public VendorBean findByVendorid(Integer vendorid) {
		if(vendorid != null) {
			VendorBean temp = this.getSession().get(VendorBean.class, vendorid);
			if(temp != null) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<VendorBean> getAll() {
		
		return null;
	}

	@Override
	public List<VendorBean> findByKeyword() {
		// TODO Auto-generated method stub
		return null;
	}
}
