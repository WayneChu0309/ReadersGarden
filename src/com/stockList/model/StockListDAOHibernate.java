package com.stockList.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stock.model.StockBean;

public class StockListDAOHibernate implements StockListDAO {
	private SessionFactory sessionFactory;
	private StockBean stockBean;
	
	public StockListDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.stockBean = new StockBean();
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public StockListBean add(StockListBean listBean) {
		if (listBean != null) {
			this.getSession().save(listBean);
			return listBean;
		}
		return null;
	}

	@Override
	public StockListBean update(StockListBean listBean) {
		if (listBean != null) {
			StockListBean temp = this.getSession().get(StockListBean.class, listBean.getListId());
			if (temp != null) {
				return (StockListBean) this.getSession().merge(listBean);
			}
		}
		return null;
	}

	@Override
	public int availStock(Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<StockListBean> root = criteriaQuery.from(StockListBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		Predicate p2= criteriaBuilder.equal(root.get("listStates"), 1);
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.multiselect(criteriaBuilder.count(root.get("listId")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		Number result = (Number) typedQuery.getSingleResult();
		return result.intValue();
	}

	@Override
	public StockListBean findByListId(Integer listId) {
		if (listId != null) {
			return (StockListBean) this.getSession().get(StockListBean.class, listId);
		}
		return null;
	}
	
	@Override
	public List<StockListBean> findByAvailList(Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockListBean> criteriaQuery = criteriaBuilder.createQuery(StockListBean.class);
		Root<StockListBean> root = criteriaQuery.from(StockListBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<StockListBean> typedQuery = session.createQuery(criteriaQuery);
		List<StockListBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public int findByAvailListId(Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<StockListBean> root = criteriaQuery.from(StockListBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		Predicate p2 = criteriaBuilder.equal(root.get("listStates"), 1);
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.select(root.get("listId"));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);
		Number result = 0;
		try {
			result = (Number) typedQuery.getSingleResult();
			return result.intValue();			
		} catch(Exception e) {}
		return result.intValue();
	}
	
	@Override
	public List<StockListBean> findByAllList(Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockListBean> criteriaQuery = criteriaBuilder.createQuery(StockListBean.class);
		Root<StockListBean> root = criteriaQuery.from(StockListBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<StockListBean> typedQuery = session.createQuery(criteriaQuery);
		List<StockListBean> result = typedQuery.getResultList();
		return result;
	}
	
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StockListDAO dao = new StockListDAOHibernate(sessionFactory);
		
		StockListBean bean = new StockListBean();
		StockBean stockBean = new StockBean();
		
		List<StockListBean> result = dao.findByAllList(1);
		System.out.println(result.size());
		// add
//		stockBean.setStockId(6223);
//		bean.setListStates(1);
//		bean.setStockBean(stockBean);
//		System.out.println(bean);
//		bean = dao.add(bean);
//		System.out.println(bean);
		
		// update 
//		for (int i = 4000; i < 4030; i++) {
//			stockBean.setStockId(i);
//			bean = dao.findByListId(i*2);
//			bean.setListStates(3);
//			dao.update(bean);
//		}
//		bean.setStockBean(stockBean);
//		bean = dao.update(bean);
//		System.out.println(bean);
		
		// availStock
//		int avail = dao.availStock(10000);
//		System.out.println(avail);
		
		// findByPk
//		bean = dao.findByListId(7);
//		stockBean = bean.getStockBean();
//		System.out.println(bean);
//		System.out.println(stockBean);
//		System.out.println(bean.getStockBean().getStockId());
	
		// findByAvailList
//		List<StockListBean> result = dao.findByAvailList(219);
//		System.out.println(result);
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}


}
