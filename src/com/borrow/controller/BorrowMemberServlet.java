package com.borrow.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.hibernate.HibernateUtil;
import com.member.model.MemberVO;

public class BorrowMemberServlet extends HttpServlet {
//	private HibernateStockListService listSvc;
	private HibernateBorrowService borrowSvc;
//	private HibernateStockService stockSvc;

	@Override
	public void init() throws ServletException {
//		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
//		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String url = "/borrow/borrowMember.jsp";
		String url2 = "/login/login.jsp";
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		try {
			MemberVO member = (MemberVO) session.getAttribute("member");
			List<BorrowBean> memberAllboList = borrowSvc.findByNumberAllBorrow(member.getNumber());
			req.setAttribute("memberAllboList", memberAllboList);
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		} catch(Exception e) {
			res.sendRedirect(req.getContextPath() + url2);
			return;
		}
			
		
		
	}

}
