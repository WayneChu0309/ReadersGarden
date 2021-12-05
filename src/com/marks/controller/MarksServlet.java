package com.marks.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hibernate.HibernateUtil;
import com.marks.model.HibernateMarksService;
import com.marks.model.MarksBean;
import com.marks.model.MarksDAOHibernate;
import com.member.model.MemberVO;
import com.stock.model.HibernateStockService;
import com.stock.model.StockDAOHibernate;

public class MarksServlet extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateMarksService markSvc;
	
	public void init() throws ServletException {
		markSvc = new HibernateMarksService(new MarksDAOHibernate(HibernateUtil.getSessionFactory()));
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String url = "/marks/marks.jsp"; 
		String url2 = "/login/login.jsp";
		HttpSession session = req.getSession();
		try {
			MemberVO memVO = (MemberVO)session.getAttribute("member");
			List<MarksBean> markList = markSvc.findMemberAll(memVO.getNumber());
			req.setAttribute("markList", markList);
			req.setAttribute("stockSvc", stockSvc);
			RequestDispatcher successView = req.getRequestDispatcher(url);  
			successView.forward(req, res);
			return;
		} catch(Exception e) {
			res.sendRedirect(req.getContextPath() + url2);
			return;
		}
		
		
	}

	

}
