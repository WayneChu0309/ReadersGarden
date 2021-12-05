package com.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bulletin.model.BulletinBean;
import com.bulletin.model.BulletinDAOHibernate;
import com.bulletin.model.HibernateBulletinService;
import com.event.model.Event;
import com.event.model.EventService;
import com.hibernate.HibernateUtil;
import com.stock.model.HibernateStockService;
import com.stock.model.NewStock;
import com.stock.model.NewStockDAOHibernate;
import com.stock.model.NewStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

//@WebServlet("/ReadersGarden")
public class ReadersGarden extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateBulletinService buSvc;
	private EventService eventSvc;
	private VendorActService vaSvc;
	private NewStockService newstockSvc;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		buSvc = new HibernateBulletinService(new BulletinDAOHibernate(HibernateUtil.getSessionFactory()));
		newstockSvc = new NewStockService(new NewStockDAOHibernate(HibernateUtil.getSessionFactory()));
		vaSvc = new VendorActService();
		eventSvc = new EventService();	
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		String url = "/home/home.jsp";
		HttpSession session = req.getSession();
//		if (session.isNew()) {
			System.out.println("start loading");
			List<Event> newEvent = eventSvc.findByNew();
			System.out.println("event find by new");
			List<VendorActVO> newAct = vaSvc.findByNew();
			System.out.println("act find by new");
			List<StockBean> newStock = new ArrayList<>();
			List<NewStock> findnew = newstockSvc.findNew();
			newStock.add(stockSvc.findByStockId(findnew.get(0).getStockId()));
			newStock.add(stockSvc.findByStockId(findnew.get(1).getStockId()));
			
//			newStock.add(stockSvc.getNewBook());
			System.out.println("book find by new");
//			newStock.add(stockSvc.getNewMovie());
			System.out.println("movie find by new");
			BulletinBean newBulletin = buSvc.findByNew();
			session.setAttribute("newStock", newStock);
			session.setAttribute("newBulletin", newBulletin);
			session.setAttribute("newAct", newAct);
			session.setAttribute("newEvent", newEvent);
//		}
		System.out.println("end loading");
		String url = req.getContextPath();
//		System.out.println(url);
		res.sendRedirect(url + "/home/home.jsp");
		return;
//		RequestDispatcher successView = req.getRequestDispatcher(url);
//		successView.forward(req, res);
//		return;

	}

}
