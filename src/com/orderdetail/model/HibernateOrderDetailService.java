package com.orderdetail.model;

import java.util.List;

public class HibernateOrderDetailService {
	private OrderDetailDAO dao;

	public HibernateOrderDetailService(OrderDetailDAO dao) {
		this.dao = dao;
	}
	
	public OrderDetailBean add(OrderDetailBean detailBean) {
		return dao.add(detailBean);
	}
	
	public OrderDetailBean update(OrderDetailBean detailBean) {
		return dao.update(detailBean);
	}
	
	public OrderDetailBean findByPk(Integer detailId) {
		return dao.findByPk(detailId);
	}
	
	public List<OrderDetailBean> findByOrderId(Integer orderId) {
		return dao.findByOrderId(orderId);
	}
	
	public byte[] getqrcode(String url) {
		return dao.getqrcode(url);
	}
	
}
