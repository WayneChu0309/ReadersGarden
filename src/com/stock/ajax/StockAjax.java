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
import com.stock.model.*;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListDAOHibernate;

//@WebServlet("/Stock/StockAjax")
public class StockAjax extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateStockListService listSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Integer typeId = Integer.parseInt(req.getParameter("typeId"));
		String keyword = req.getParameter("keyword");
		if (keyword.length() != 0) {
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<StockVO> keywordList = (List<StockVO>) session.getAttribute("stockList");
			String stJson = gson.toJson(keywordList);
			out.print(stJson);
			return;
		}
		if (typeId != 0) {
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<StockVO> list = (List<StockVO>) session.getAttribute("stockList");
			String stJson = gson.toJson(list);
			out.print(stJson);
			return;
		}
	}

}
