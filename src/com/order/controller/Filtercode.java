package com.order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hibernate.HibernateUtil;
import com.orderdetail.model.HibernateOrderDetailService;
import com.orderdetail.model.OrderDetailBean;
import com.orderdetail.model.OrderDetailDAOHibernate;
import com.vendoract.model.VendorActService;

@WebServlet("/filtercode")
public class Filtercode extends HttpServlet {
	private HibernateOrderDetailService detailSvc;
	private VendorActService venSvc;

	@Override
	public void init() throws ServletException {
		detailSvc = new HibernateOrderDetailService(new OrderDetailDAOHibernate(HibernateUtil.getSessionFactory()));
		venSvc = new VendorActService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer detailId = new Integer(req.getParameter("detailId"));
		OrderDetailBean detailBean = detailSvc.findByPk(detailId);
		String actname = venSvc.findOneVendorAct(detailBean.getVactId()).getName();
		long start = detailBean.getStartdate().getTime();
		long end = detailBean.getStopdate().getTime();
		String url = "/order/orderCode.jsp";
		
		String message = "活動已開始 歡迎入場";
				
				
		if (System.currentTimeMillis() > start) {
			message = "還沒開始入場喔"; // 
		}

		if (System.currentTimeMillis() > end) {
			message = "活動已截止";// 
		}
		req.setAttribute("actstart", detailBean.getStartdate());
		req.setAttribute("actend", detailBean.getStopdate());
		req.setAttribute("actname", actname);
		req.setAttribute("message", message);
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
		
	}

}
