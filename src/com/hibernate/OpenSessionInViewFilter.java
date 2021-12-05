package com.hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bulletin.model.BulletinDAOHibernate;
import com.bulletin.model.HibernateBulletinService;
import com.event.model.Event;
import com.event.model.EventService;
import com.stock.model.HibernateStockService;
import com.stock.model.NewStock;
import com.stock.model.NewStockDAOHibernate;
import com.stock.model.NewStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;


public class OpenSessionInViewFilter implements Filter {
	private HibernateStockService stockSvc;
	private HibernateBulletinService buSvc;
	private EventService eventSvc;
	private VendorActService vaSvc;
	private NewStockService newstockSvc;

    public OpenSessionInViewFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession httpSession = req.getSession();
			if (httpSession.isNew()) {
				eventSvc = new EventService();	
				newstockSvc = new NewStockService(new NewStockDAOHibernate(HibernateUtil.getSessionFactory()));
				vaSvc = new VendorActService();
				stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
				buSvc = new HibernateBulletinService(new BulletinDAOHibernate(HibernateUtil.getSessionFactory()));
				List<StockBean> newStock = new ArrayList<>();
				List<NewStock> findnew = newstockSvc.findNew();
				newStock.add(stockSvc.findByStockId(findnew.get(0).getStockId()));
				newStock.add(stockSvc.findByStockId(findnew.get(1).getStockId()));
				List<Event> newEvent = eventSvc.findByNew();
				List<VendorActVO> newAct = vaSvc.findByNew();
				httpSession.setAttribute("newAct", newAct);
				httpSession.setAttribute("newStock", newStock);
				httpSession.setAttribute("newBulletin", buSvc.findByNew());
				httpSession.setAttribute("newEvent", newEvent);
			}
			
			chain.doFilter(request, response); // 呼叫後面的元件

			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();

			chain.doFilter(request, response); // 呼叫後面的元件
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
