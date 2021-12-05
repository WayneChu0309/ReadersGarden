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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.stock.model.StockBean;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;

// 管理員用
public class MemberBorrowAjax extends HttpServlet {
	private HibernateStockListService listSvc;
	private HibernateBorrowService borrowSvc;
	private Gson gson;
	private MemberService memSvc;
   
	@Override
	public void init() throws ServletException {
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		memSvc = new MemberService();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String memberId = req.getParameter("memberId");
		
		Integer boStates = new Integer(req.getParameter("boStates"));
		Integer listId = 0;
		try {
			listId = new Integer(req.getParameter("listId"));			
		} catch(Exception e) {
			out.print(0); // listId 錯誤
			return;
		}
		StockListBean listBean = listSvc.findByListId(listId);
		
		
		
		
		
		String date = df.format(new java.util.Date());
		
		
		
		
		if ("add".equals(action) && boStates == 0) {
			MemberVO memVO = memSvc.getOneMember(new Integer(memberId));
			
			if (listBean == null ) {
				out.print(0); // listId 錯誤
				return;
			} else if (listBean.getListStates() != 1) {
				out.print(1); // 已被預訂
				return;
			} else if (memVO == null) {
				out.print(2);
				return;
			} else if ("管理員".equals(memVO.getStatus())) {
				out.print(3);
				return;
			}
			StockBean stockBean = listBean.getStockBean();
			java.util.Date prDate = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());
			cal.add(Calendar.MONTH, 1);
			prDate = cal.getTime();
			String preReDate = df.format(prDate);
			BorrowBean bean = new BorrowBean();
			bean.setStockBean(stockBean);
			bean.setBoStates(2);
			bean.setListId(listId);
			bean.setActBoDate(java.sql.Date.valueOf(date));
			bean.setPreReDate(java.sql.Date.valueOf(preReDate));
			bean.setMemberId(new Integer(memberId));
			listBean.setListStates(2);
			borrowSvc.add(bean);
			listSvc.update(listBean);
			out.print(gson.toJson(bean));
			return;
		}
		
		
		Integer borrowId = new Integer(req.getParameter("borrowId"));
		BorrowBean bean = borrowSvc.findByPk(borrowId);
		
		if ("order".equals(action) && (boStates == 1 || boStates == 8)) {
			if (memberId.length() == 0) {
				bean.setBoStates(8);
				bean = borrowSvc.update(bean);
				out.print(gson.toJson(bean));
				return;
			} else {
				bean.setActBoDate(java.sql.Date.valueOf(date));
				bean.setBoStates(2);
				bean = borrowSvc.update(bean);
				out.print(gson.toJson(bean));
				return;
			}
		}
		
		if ("return".equals(action) && boStates == 2) {
			bean.setActReDate(java.sql.Date.valueOf(date));
			bean.setBoStates(4);
			bean = borrowSvc.update(bean);
			listBean.setListStates(1);
			listSvc.update(listBean);
			out.print(gson.toJson(bean));
			return;
		}
		
		if ("overreturn".equals(action) && boStates == 3) {
			bean.setActReDate(java.sql.Date.valueOf(date));
			bean.setBoStates(5);
			bean = borrowSvc.update(bean);	
			listBean.setListStates(1);
			listSvc.update(listBean);
			out.print(gson.toJson(bean));
			return;
		}
		

		
		if ("confirm".equals(action) && boStates == 8) {
			bean.setActBoDate(java.sql.Date.valueOf(date));
			bean.setBoStates(2);
			bean = borrowSvc.update(bean);
			out.print(gson.toJson(bean));
			return;
		}
		
		if("cancel".equals(action) && boStates == 6) {
			bean.setBoStates(7);
			bean = borrowSvc.update(bean);
			out.print(gson.toJson(bean));
			return;
		}
		
	}

}
