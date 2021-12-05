package com.borrow.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.hibernate.HibernateUtil;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.stock.model.HibernateStockService;
import com.stock.model.StockDAOHibernate;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListDAOHibernate;


public class BorrowServlet extends HttpServlet {
	private HibernateStockListService listSvc;
	private HibernateBorrowService borrowSvc;
	private HibernateStockService stockSvc;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		String url = "/borrow/borrowManager.jsp";
		List<BorrowBean> boList = null;
		
		if ("selectall".equals(action)) {
			req.removeAttribute("memberId");
			System.out.println("selectall="+action);
			boList = borrowSvc.findAllOverDate();
			req.setAttribute("boList", boList);
			req.setAttribute("listSvc", listSvc);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		
		if("orderselect".equals(action)) {
			req.removeAttribute("memberId");
			System.out.println("orderselect="+action);
			boList = borrowSvc.findByAllOrder();
			req.setAttribute("boList", boList);
			req.setAttribute("listSvc", listSvc);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		if ("cancelselect".equals(action)) {
			req.removeAttribute("memberId");
			System.out.println("cancelselect="+action);
			boList = borrowSvc.findByAllCancel();
			req.setAttribute("boList", boList);
			req.setAttribute("listSvc", listSvc);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		int memberId = new Integer(req.getParameter("memberId"));
		MemberService memSvc = new MemberService();
		MemberVO selMember = memSvc.getOneMember(memberId);
		req.setAttribute("selMember", selMember);
		
		if (selMember == null) {
			req.setAttribute("memberId", "0");
			req.setAttribute("selMember", selMember);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		
		if("illegltotal".equals(action)) {
			Map<String, Integer> memberBorrow = new HashMap<>();
			Integer illgelNumber;
			Integer successNumber;
			boList = borrowSvc.findByNumberIlleglBorrow(memberId);
			illgelNumber = borrowSvc.getIlleglNumber(memberId);
			successNumber = borrowSvc.getSuccessNumber(memberId);
			memberBorrow.put("illgelNumber", illgelNumber);
			memberBorrow.put("successNumber", successNumber);
			req.setAttribute("memberBorrow", memberBorrow);
			req.setAttribute("memberId", memberId);
			req.setAttribute("boList", boList);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
			
		}
			
		if ("normaltotal".equals(action)) {
			Map<String, Integer> memberBorrow = new HashMap<>();
			Integer illgelNumber;
			Integer successNumber;
			boList = borrowSvc.findByNumberSuccessBorrow(memberId);
			illgelNumber = borrowSvc.getIlleglNumber(memberId);
			successNumber = borrowSvc.getSuccessNumber(memberId);
			memberBorrow.put("illgelNumber", illgelNumber);
			memberBorrow.put("successNumber", successNumber);
			req.setAttribute("memberBorrow", memberBorrow);
			req.setAttribute("memberId", memberId);
			req.setAttribute("boList", boList);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}

		Map<String, Integer> memberBorrow = new HashMap<>();
		Integer illgelNumber;
		Integer successNumber;
		boList = borrowSvc.findByNumberBorrow(memberId);
		illgelNumber = borrowSvc.getIlleglNumber(memberId);
		successNumber = borrowSvc.getSuccessNumber(memberId);
		memberBorrow.put("illgelNumber", illgelNumber);
		memberBorrow.put("successNumber", successNumber);
		req.setAttribute("memberBorrow", memberBorrow);
		req.setAttribute("memberId", memberId);
		req.setAttribute("boList", boList);
		
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
		
		
	}

}
