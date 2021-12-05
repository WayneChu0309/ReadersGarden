package com.stock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.marks.model.HibernateMarksService;
import com.marks.model.MarksDAOHibernate;
import com.stock.model.HibernateStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListDAOHibernate;
import com.stockType.model.HibernateStockTypeService;
import com.stockType.model.StockTypeBean;
import com.stockType.model.StockTypeDAOHibernate;


public class StockServletHibernate extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateStockTypeService typeSvc;
	private HibernateStockListService listSvc;
	private HibernateBorrowService borrowSvc;
	private HibernateMarksService markSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		typeSvc = new HibernateStockTypeService(new StockTypeDAOHibernate(HibernateUtil.getSessionFactory()));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
		markSvc = new HibernateMarksService(new MarksDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 取得 書籍類別清單
		List<StockTypeBean> bookType = typeSvc.getBook();
		// 取得 電影類別清單
		List<StockTypeBean> movieType = typeSvc.getMovie();
		
		String stockInf = "{}";
		Integer typeId = 0;
		
		String url = "/Stock/stockHibernate.jsp";
		
		req.setAttribute("typeId", typeId);
		req.setAttribute("bookType", bookType);
		req.setAttribute("movieType", movieType);
		req.setAttribute("borrowSvc", borrowSvc);
		req.setAttribute("listSvc", listSvc);
		req.setAttribute("markSvc", markSvc);
		
		if ("first".equals(action)) {
			typeId = new Integer(req.getParameter("typeId"));
			int page = new Integer(req.getParameter("page"));
			// 取得 類別總數
			int totalForType = stockSvc.getTypeIdTotal(typeId);
			// 計算總頁數
			int total = totalForType % 30 != 0 ? (totalForType / 30) + 1 : totalForType / 30 ;
			List<Integer> totalpage = new ArrayList<>();
			for (int i = 1; i <= total; i++) {
				totalpage.add(i);
			}
			// 取得該頁數內容
			List<StockBean> stockList = stockSvc.getPage(typeId, page);
			stockInf = gson.toJson(stockList);
			
			req.setAttribute("typeId", typeId);
			req.setAttribute("stockList", stockList);
			req.setAttribute("totalpage", totalpage);
			req.setAttribute("nowPage", page);
			req.setAttribute("stockInf", stockInf);
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 stock.jsp 
			successView.forward(req, res);
			return;
		}
		
		if ("search".equals(action)) {
			String keyword = req.getParameter("search");
			if (keyword != null & keyword.length() != 0) {
				List<StockBean> stockList = new ArrayList<>();
				HttpSession session = req.getSession();
				Map<String, List<StockBean>> keywordList = new HashMap<>();
				Object record = session.getAttribute("keywordList");
				
				if (record != null) {
					keywordList = (Map<String, List<StockBean>>) record;
					
					if (keywordList.containsKey(keyword)) {
						stockList = keywordList.get(keyword);
						stockInf = gson.toJson(stockList);	
					} else {
						stockList = stockSvc.getKeyword(keyword);
						if (stockList != null & stockList.size() != 0) {
							stockInf = gson.toJson(stockList);	
							keywordList.put(keyword, stockList);
						}
					}
				} else {
					stockList = stockSvc.getKeyword(keyword);
					if (stockList != null & stockList.size() != 0) {
						stockInf = gson.toJson(stockList);					
						keywordList.put(keyword, stockList);
					}
				}
	
				
				
				session.setAttribute("keywordList", keywordList);
				req.setAttribute("stockList", stockList);
				req.setAttribute("typeId", typeId);
				req.setAttribute("stockInf", stockInf);
			}
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 stock.jsp 
			successView.forward(req, res);
			return;
		}
		
		if ("quick".equals(action)) {
			int id = new Integer(req.getParameter("ID"));
			List<StockBean> stockList = new ArrayList<>();
			stockList.add(stockSvc.findByStockId(id));
			stockInf = gson.toJson(stockList);
			req.setAttribute("stockList", stockList);
			req.setAttribute("typeId", typeId);
			req.setAttribute("stockInf", stockInf);
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 stock.jsp 
			successView.forward(req, res);
			return;
		}
		
		
	}
}
