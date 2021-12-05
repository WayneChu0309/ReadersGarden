package com.stock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hibernate.HibernateUtil;
import com.stock.model.*;

public class StockImgHibernate extends HttpServlet {
	private HibernateStockService stockSvc;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String action = req.getParameter("action");
		Integer stockId = Integer.parseInt(req.getParameter("ID"));
		
		if ("showImg".equals(action)) {
			StockBean bean = stockSvc.findByStockId(stockId);
			out.write(bean.getStockImg());			
		}
		if ("addImg".equals(action)) {
			if (stockId != -1) {
				HttpSession session = req.getSession();
				@SuppressWarnings("unchecked")
				List<StockBean> addStockList = (List<StockBean>) session.getAttribute("addStockList");
				out.write(addStockList.get(stockId).getStockImg());	
			}
		}
		
		if ("updateImg".equals(action)) {
			HttpSession session = req.getSession();
			StockBean updateStock = (StockBean)session.getAttribute("updateStock");
			if (updateStock.getStockImg() != null) {
				out.write(updateStock.getStockImg());				
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
