package com.stockList.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hibernate.HibernateUtil;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;

public class StockListAjax extends HttpServlet {
	private HibernateStockListService listSvc;
	
	@Override
	public void init() throws ServletException {
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		Integer listId = new Integer(req.getParameter("listId"));
		StockListBean listBean = listSvc.findByListId(listId);
		listBean.setListStates(3);
		listBean = listSvc.update(listBean);
		if (listBean != null) {
			out.print(1);
		}
	}

}
