package com.orderdetail.model;

import java.util.List;


public interface OrderDetailDAO {
			// 此介面定義對資料庫的相關存取抽象方法
	public abstract OrderDetailBean add(OrderDetailBean detailBean);
	public abstract OrderDetailBean update(OrderDetailBean detailBean);
	public abstract OrderDetailBean findByPk(Integer detailId);
	public abstract List<OrderDetailBean> findByOrderId(Integer orderId);
	// 找出過期票券, 給排程器用
	public abstract List<OrderDetailBean> findByOverDate();
	public abstract byte[] getqrcode(String url);

}

