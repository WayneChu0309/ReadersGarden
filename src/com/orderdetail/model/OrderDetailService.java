package com.orderdetail.model;

import java.util.List;
import java.util.Set;

public class OrderDetailService {


		private OrderDetailDAOJDBC dao;

		public OrderDetailService() {
			dao = new OrderDetailDAOImpl();
		}

		public OrderDetail addOrderDetail(Integer orderid, Integer actno) {

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setORDERID(orderid);
			orderDetail.setACTNO(actno);
			dao.add(orderDetail);
			return orderDetail;
		}

		public OrderDetail updateOrderDetail(Integer ticketid, Integer orderid, Integer actno) {

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setTICKETID(ticketid);
			orderDetail.setORDERID(orderid);
			orderDetail.setACTNO(actno);
			dao.update(orderDetail);

			return orderDetail;
		}

		public void deleteOrderDetail(Integer ticketid) {
			dao.delete(ticketid);
		}

		public OrderDetail getOneOrderDetail(Integer ticketid) {
			return dao.findByPK(ticketid);
		}

		public List<OrderDetail> getAll() {
			return dao.getAll();
		}
}

