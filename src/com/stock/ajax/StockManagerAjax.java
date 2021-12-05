package com.stock.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stock.model.HibernateStockService;
import com.stock.model.StockDAOHibernate;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;


public class StockManagerAjax extends HttpServlet {
	private HibernateStockListService listSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String stock = req.getParameter("stockId");
		
		if (stock == null) {
			HttpSession session = req.getSession();
			session.removeAttribute("updateStock");
			return;			
		} else {
			int stockId = new Integer(stock);
			System.out.println(stockId);
			List<StockListBean> stockListForStockId = listSvc.findByAllList(stockId);
			System.out.println(stockListForStockId);
			out.print(gson.toJson(stockListForStockId));
			return;
		}
		
	}

}
