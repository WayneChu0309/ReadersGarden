package com.vendoract.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hibernate.HibernateUtil;
import com.member.model.MemberVO;
import com.order.model.HibernateOrderService;
import com.order.model.OrderBean;
import com.order.model.OrderDAOHibernate;
import com.orderdetail.model.HibernateOrderDetailService;
import com.orderdetail.model.OrderDetailBean;
import com.orderdetail.model.OrderDetailDAOHibernate;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorTicketServlet extends HttpServlet {
	private HibernateOrderService orderSvc;
	private VendorActService vactSvc;
	private HibernateOrderDetailService detailSvc;
	
	@Override
	public void init() throws ServletException {
		orderSvc = new HibernateOrderService(new OrderDAOHibernate(HibernateUtil.getSessionFactory()));
		detailSvc = new HibernateOrderDetailService(new OrderDetailDAOHibernate(HibernateUtil.getSessionFactory()));
		vactSvc = new VendorActService();
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if ("getOne_For_Display".equals(action)) { // 來自/source/WebContent/vendor_ticketHome.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("vactid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/vendor_ticketHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer vactid = null;
				try {
					vactid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/vendor_ticketHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				VendorActService vaSvc = new VendorActService();
				VendorActVO vactVO = vaSvc.findOneVendorAct(vactid);
				if (vactVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/vendor_ticketHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("vactVO", vactVO); // 資料庫取出的vactVO物件,存入req
				String url = "/ticket/ticketDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/vendor_ticketHome.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		if ("view".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer vactid = new Integer(req.getParameter("vactid"));
				
				/***************************2.開始檢視資料***************************************/
				VendorActService vaSvc = new VendorActService();
				VendorActVO vactVO = vaSvc.findOneVendorAct(vactid);
				req.setAttribute("vactVO", vactVO);
				
				/***************************3.檢視完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/ticketDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 檢視成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("檢視資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/ticketDetail.jsp");
				failureView.forward(req, res);
			}
		}	
	
	
		
		
		if ("add".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer vactid = new Integer(req.getParameter("vactid"));	
				Integer ordercount = new Integer(req.getParameter("count"));
				VendorActVO vact = vactSvc.findOneVendorAct(vactid);
				OrderBean orderBean = new OrderBean();
				orderBean.setMemberId(member.getNumber());
				orderBean.setOrdercount(ordercount);
				orderBean.setVactId(vactid);
				orderBean.setTotal(vact.getPrice() * ordercount);
				orderSvc.add(orderBean);
				List<OrderBean> orderList = orderSvc.findByMemberNotPay(member.getNumber());
				
				/***************************2.開始新增資料***************************************/
				req.setAttribute("orderBean", orderBean);
				session.setAttribute("orderList", orderList);
				/***************************3.檢視完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/ticketCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 檢視成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("新增活動失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/ticketDetail.jsp");
				failureView.forward(req, res);
			}
		}			
		

		
		if ("buy".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
//				Integer vactid = new Integer(req.getParameter("vactid"));
//				String name = req.getParameter("name");
//				String actcnt = req.getParameter("actcnt");
//				VendorActVO vact = new VendorActVO();
//				vact.setVactid(vactid);	
//				vact.setName(name);
//				vact.setActcnt(actcnt);
				@SuppressWarnings("unchecked")
				List<OrderBean> orderList = (List<OrderBean>) session.getAttribute("orderList");
				int totalCount = 0;
				for (int i = 0; i < orderList.size(); i++) {
					OrderBean order = orderList.get(i);
					totalCount += order.getTotal();
				}
				req.setAttribute("totalCount", totalCount);
				
				/***************************2.開始新增資料***************************************/
//				VendorActService vaSvc = new VendorActService();
//				VendorActVO vactVO = vaSvc.findOneVendorAct(vactid);
//				req.setAttribute("vactVO", vactVO);
	
				/***************************3.檢視完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/buyTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 檢視成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("購買失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/buyTicket.jsp");
				failureView.forward(req, res);
			}
		}			
	
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				
				
				/***************************2.開始刪除資料***************************************/
				OrderBean orderBean = orderSvc.findByPk(orderId);
				orderBean.setOrderStatus(3);
				orderSvc.update(orderBean);
				
				List<OrderBean> orderList = orderSvc.findByMemberNotPay(member.getNumber());
				session.setAttribute("orderList", orderList);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/ticketCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除活動失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/ticketDetail.jsp");
				failureView.forward(req, res);
			}
		}			
		
		
		
		if ("checkout".equals(action)) { 
			System.out.println(1);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數***************************************/
//				Integer ordercount = new Integer(req.getParameter("count"));
//				Integer orderId = new Integer(req.getParameter("orderId"));
//				Integer vactid = new Integer(req.getParameter("vactid"));	
//				VendorActVO vact = vactSvc.findOneVendorAct(vactid);		
				
				/***************************2.開始付款資料***************************************/
//				OrderBean orderBean = orderSvc.findByPk(orderId);
//				orderBean.setOrderStatus(1);
//				orderSvc.update(orderBean);
				List<OrderBean> orderList = orderSvc.findByMemberNotPay(member.getNumber());
				session.setAttribute("orderList", orderList);

				for (int i = 0; i < orderList.size(); i++) {
					OrderBean order = orderList.get(i);
					String url = req.getContextPath();
					for (int j = 0; j < order.getOrdercount(); j++) {
						OrderDetailBean detailBean = new OrderDetailBean();
						VendorActVO va = vactSvc.findOneVendorAct(order.getVactId());
						detailBean.setOrderId(order.getOrderId());
						detailBean.setVactId(order.getVactId());
						detailBean.setStartdate(va.getActstart());
						detailBean.setStopdate(va.getActend());
						detailBean.setDetailStatus(1);
						detailBean = detailSvc.add(detailBean);
						detailBean.setQrcode(detailSvc.getqrcode("http://34.81.128.226" + url + "/filtercode?detailId=" + detailBean.getDetailId()));
						System.out.println(detailBean);
						detailSvc.update(detailBean);
					}
					order.setOrderStatus(2);
					orderSvc.update(order);
				}
				
				req.removeAttribute("totalCount");
				session.removeAttribute("orderList");
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/successQR.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 付款成功後,轉交回送出付款的來源網頁
				successView.forward(req, res);
		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("付款失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/successQR.jsp");
				failureView.forward(req, res);
			}
		}			
				
	}
}