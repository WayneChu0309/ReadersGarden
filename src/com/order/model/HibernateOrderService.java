package com.order.model;

import java.util.List;

public class HibernateOrderService {
	private OrderDAO dao;
	
	public HibernateOrderService(OrderDAO dao) {
		this.dao = dao;
	}
	
	public OrderBean add(OrderBean orderBean) {
		return dao.add(orderBean);
	}
	
	public OrderBean update(OrderBean orderBean) {
		return dao.update(orderBean);
	}
	
	public OrderBean findByPk(Integer orderId) {
		return dao.findByPk(orderId);
	}
	
	public List<OrderBean> findByMemberNotPay(Integer memberId){
		return dao.findByMemberNotPay(memberId);
	}
	
	public List<OrderBean> findByMemberPay(Integer memberId){
		return dao.findByMemberPay(memberId);
	}
	
}
