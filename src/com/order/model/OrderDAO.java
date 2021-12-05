package com.order.model;

import java.util.List;

public interface OrderDAO {
	public abstract OrderBean add(OrderBean orderBean);
	public abstract OrderBean update(OrderBean orderBean);
	public abstract OrderBean findByPk(Integer orderId);
	public abstract List<OrderBean> findByMemberNotPay(Integer memberId);
	public abstract List<OrderBean> findByMemberPay(Integer memberId);
}
