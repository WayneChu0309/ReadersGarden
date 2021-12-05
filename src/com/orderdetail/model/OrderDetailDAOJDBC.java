package com.orderdetail.model;

import java.util.List;

public interface OrderDetailDAOJDBC {
	void add(OrderDetail order_detail);
	void update(OrderDetail order_detail);
	void delete(int TICKETID);
	OrderDetail findByPK(int TICKETID);
	List<OrderDetail> getAll();
}
