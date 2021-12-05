package com.borrow.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stock.model.HibernateStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;


public class BorrowScoreAjax extends HttpServlet {
	private HibernateBorrowService borrowSvc;
	private HibernateStockService stockSvc;
	private Gson gson;
	
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String scoreContent = req.getParameter("scoreContent");
		try {
			int borrowId = new Integer(req.getParameter("borrowId"));
			int score = new Integer(req.getParameter("score"));
			BorrowBean borrowBean = borrowSvc.findByPk(borrowId);
			if (borrowBean != null) {
				borrowBean.setScore(score);
				borrowBean.setScoreContent(scoreContent);
				borrowBean.setScoreDate(new java.sql.Date(System.currentTimeMillis()));
				borrowBean = borrowSvc.update(borrowBean);
				StockBean stockBean = borrowBean.getStockBean();
				Double stockScore = borrowSvc.findByStockScore(stockBean.getStockId());
				if (stockScore != null) {
					stockBean.setStockScore(stockScore);
					stockSvc.update(stockBean);
					out.print(gson.toJson(borrowBean));
					return;
				}
			}
			out.print(0);
			return;
		} catch(Exception e) {
			out.print(0);
			return;
		}
		
		
		
		
		
		
		
		
	}

}
