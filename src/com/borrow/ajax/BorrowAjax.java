package com.borrow.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.hibernate.HibernateUtil;
import com.stock.model.StockBean;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;

// 館藏頁面預訂用
public class BorrowAjax extends HttpServlet {
	private HibernateStockListService listSvc;
	private HibernateBorrowService borrowSvc;
	
	@Override
	public void init() throws ServletException {
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		Integer stockId = Integer.parseInt(req.getParameter("stockId"));
		String preBoDate = req.getParameter("preBoDate");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		
		// 檢查剩餘數量
		int availNumber = listSvc.availStock(stockId);
		// 檢查此項會員有無預訂
		BorrowBean borrowBean = borrowSvc.findByNumberPreOrder(memberId, stockId);
		
		if (availNumber == 0) {
			out.print(0);
			return;
		}
		
		if (availNumber != 0 && borrowBean == null) {
			try {
				date = df.parse(preBoDate);
			} catch (Exception e) {}
			
			// 預訂日 加 1個月 為預計歸還日
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
			date = cal.getTime();
			String preReDate = df.format(date);
			// 找可借閱的編號
			int listId = listSvc.findByAvailListId(stockId);
			StockListBean listBean = listSvc.findByListId(listId);
			StockBean stockBean = new StockBean();
			stockBean.setStockId(stockId);
			borrowBean = new BorrowBean();
			borrowBean.setListId(listId);
			borrowBean.setMemberId(memberId);
			borrowBean.setStockBean(stockBean);
			borrowBean.setPreBoDate(java.sql.Date.valueOf(preBoDate));
			borrowBean.setPreReDate(java.sql.Date.valueOf(preReDate));
			// 1 為預訂
			borrowBean.setBoStates(1);
			listBean.setListStates(2);
			borrowSvc.add(borrowBean);
			listSvc.update(listBean);
			out.print(1);
			return;
		}

	}

}
