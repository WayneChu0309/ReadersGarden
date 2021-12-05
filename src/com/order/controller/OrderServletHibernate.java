package com.order.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hibernate.HibernateUtil;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.order.model.HibernateOrderService;
import com.order.model.OrderBean;
import com.order.model.OrderDAOHibernate;
import com.orderdetail.model.HibernateOrderDetailService;
import com.orderdetail.model.OrderDetailBean;
import com.orderdetail.model.OrderDetailDAOHibernate;
import com.vendoract.model.VendorActService;

public class OrderServletHibernate extends HttpServlet {
	private HibernateOrderService orderSvc;
	private HibernateOrderDetailService orderDetailSvc;
	private VendorActService vactSvc;
	private MemberService memberSvc;

	@Override
	public void init() throws ServletException {
		orderSvc = new HibernateOrderService(new OrderDAOHibernate(HibernateUtil.getSessionFactory()));
		orderDetailSvc = new HibernateOrderDetailService(new OrderDetailDAOHibernate(HibernateUtil.getSessionFactory()));
		vactSvc = new VendorActService();
	
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doPost(req, res);
	}
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		String url = "/order/orderMember.jsp";
		String url2 = "/login/login.jsp";
		String url3 = "/order/oneOrder.jsp";
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("get_one".equals(action)) {
			Integer orderId = new Integer(req.getParameter("orderId"));
			
			List<OrderDetailBean> orderAllList = orderDetailSvc.findByOrderId(orderId);
			req.setAttribute("orderdetail", orderAllList);
			req.setAttribute("total", req.getParameter("total"));
			RequestDispatcher successView = req.getRequestDispatcher(url3); 
			successView.forward(req, res);
			return;
		}
		
		if ("get_all".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("member");
			List<OrderBean> memberAllboList = orderSvc.findByMemberPay(member.getNumber());
			req.setAttribute("memberAllboList", memberAllboList);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}

		
	}
}




