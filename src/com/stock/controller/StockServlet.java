package com.stock.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.stock.model.*;

/**
 * Servlet implementation class StockServlet
 */
//@WebServlet("/Stock/StockServlet.page")
public class StockServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String search = req.getParameter("search");
		StockService stockSvc = new StockService();
		if (search != null) {
			List<StockVO> keywordList = stockSvc.getKeyword(search);
			HttpSession session = req.getSession();
			session.setAttribute("stockList", keywordList);
			session.setAttribute("typeId", 0);
			String url = "/Stock/stock.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 stock.jsp 
			successView.forward(req, res);
			return;
		}
		if ("getAll".equals(action)) {
			Integer typeId = Integer.parseInt(req.getParameter("typeId"));
			List<StockVO> stockList = stockSvc.getAll(typeId);
			HttpSession session = req.getSession();
			session.setAttribute("stockList", stockList);
			session.setAttribute("typeId", typeId);
			String url = "/Stock/stock.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 stock.jsp 
			successView.forward(req, res);
			return;
		}
		
	}

}
