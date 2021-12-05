package com.stock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stock.model.StockService;
import com.stock.model.StockVO;

//@WebServlet("/stock/StockImg")
public class StockImg extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String action = req.getParameter("action");
		Integer stockId = Integer.parseInt(req.getParameter("ID"));
		
		if ("showImg".equals(action)) {
			StockService stockSvc = new StockService();
			StockVO vo = stockSvc.getOneStock(stockId);
			out.write(vo.getStockImg());			
		}
		if ("addImg".equals(action)) {
			if (stockId != -1) {
				HttpSession session = req.getSession();
				@SuppressWarnings("unchecked")
				List<StockVO> addStockList = (List<StockVO>) session.getAttribute("addStockList");
				out.write(addStockList.get(stockId).getStockImg());	
			}
		}
		
		if ("updateImg".equals(action)) {
			HttpSession session = req.getSession();
			StockVO updateStock = (StockVO)session.getAttribute("updateStock");
			if (updateStock.getStockImg() != null) {
				out.write(updateStock.getStockImg());				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
