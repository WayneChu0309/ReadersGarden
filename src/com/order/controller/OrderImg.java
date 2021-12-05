package com.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hibernate.HibernateUtil;
import com.orderdetail.model.HibernateOrderDetailService;
import com.orderdetail.model.OrderDetailBean;
import com.orderdetail.model.OrderDetailDAOHibernate;

public class OrderImg extends HttpServlet {
	private HibernateOrderDetailService orderDetailSvc;
	
	@Override
	public void init() throws ServletException {
		orderDetailSvc = new HibernateOrderDetailService(new OrderDetailDAOHibernate(HibernateUtil.getSessionFactory()));
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer detailId = Integer.parseInt(req.getParameter("ID"));
			
		OrderDetailBean orderdetailbean = orderDetailSvc.findByPk(detailId);
		out.write(orderdetailbean.getQrcode());			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
