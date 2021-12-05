package com.borrow.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.hibernate.HibernateUtil;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;

public class BorrowScheduleServlet extends HttpServlet {
	private HibernateBorrowService borrowSvc;
	private HibernateStockListService listSvc;
	Timer timer;

	@Override
	public void init() throws ServletException {
		timer = new Timer();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(sessionFactory));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(sessionFactory));
		Calendar cal = new GregorianCalendar(2021, Calendar.NOVEMBER, 5, 10, 50, 0);
		TimerTask task = new TimerTask() {

			public void run() {				
				Transaction transaction = null;
				try {
					
					Session session = sessionFactory.getCurrentSession();
					transaction = session.beginTransaction();
					
					List<BorrowBean> result = borrowSvc.findByPreOrderNoBorrow();
					for (BorrowBean beans : result) {
						System.out.println(beans);
						StockListBean listBean = listSvc.findByListId(beans.getListId());
						listBean.setListStates(1);
						beans.setBoStates(6);
						borrowSvc.update(beans);
						listSvc.update(listBean);
					}
					
					List<BorrowBean> result2 = borrowSvc.findByOverDate();
					for (BorrowBean beans : result2) {
						beans.setBoStates(3);
						borrowSvc.update(beans);
					}
					
					transaction.commit();
					session.close();
				} catch (Exception e) {
					transaction.rollback();
					e.printStackTrace();
				}

			}
		};
		
		timer.scheduleAtFixedRate(task, cal.getTime(), 1 * 60 * 1000);
	}

	public void destroy() {
		timer.cancel();
		System.out.println("ohohoh");
	}

}
