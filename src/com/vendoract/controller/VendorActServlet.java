package com.vendoract.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vendor.model.VendorVO;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorActServlet extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		VendorActService vactSvc = new VendorActService();
		HttpSession session = req.getSession();
		
		if("vendor_Order".equals(action)) { //來自vendorOrder.jsp
			VendorVO vendorVO = (VendorVO) session.getAttribute("vendorVO");
			Integer vendorid = null;
			try {
				vendorid  = new Integer(vendorVO.getVendorid());
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
			
			List<VendorActVO> vactlist = vactSvc.findByVendorid(vendorid);
			req.setAttribute("vactlist", vactlist);
			
			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vendorOrder.jsp");
			successView.forward(req, res);
		}
		
		if("vendor_Activity".equals(action)) { //來自vendorActivity.jsp
			VendorVO vendorVO = (VendorVO) session.getAttribute("vendorVO");
			Integer vendorid = null;
			try {
				vendorid  = new Integer(vendorVO.getVendorid());
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
			VendorActVO vact = vactSvc.findLatestActivity(vendorid);
			req.setAttribute("vact", vact);
			
			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vendorActivity.jsp");	
			successView.forward(req, res);
		}
		
		if("getOne_VendorAct".equals(action)) { //來自vendorOrder.jsp
			Integer vactid = new Integer(req.getParameter("vactid"));
			VendorActVO vact = vactSvc.findOneVendorAct(vactid);
			req.setAttribute("vact", vact);
			
			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vendorActivity.jsp");	
			successView.forward(req, res);
		}
		
		if("getAll".equals(action)) { //來自vactBackend.jsp
			List<VendorActVO> vactlist = vactSvc.getAll();
			req.setAttribute("vactlist", vactlist);
			
			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vactBackend.jsp");	
			successView.forward(req, res);
			
		}
		
		if("getOne_For_Display".equals(action)) { //來自vactBackend.jsp
			String errorMsgs = null;
			try {
				Integer vactid = new Integer(req.getParameter("vactid"));
				VendorActVO vact = vactSvc.findOneVendorAct(vactid);
				req.setAttribute("vact", vact);
			} catch(NumberFormatException e) {
				errorMsgs = "請輸入訂單編號";
				req.setAttribute("errorMsgs", errorMsgs);
			}
			
			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vactBackend.jsp");	
			successView.forward(req, res);
		}
		
	}
}
