package com.stock.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stockType.model.StockTypeBean;

public class StockDAOHibernate implements StockDAO{
	private SessionFactory sessionFactory;
	private StockTypeBean typeBean;
	
	public StockDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.typeBean = new StockTypeBean();
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public StockBean add(StockBean stockBean) {
		if (stockBean != null) {
			this.getSession().save(stockBean);
			return stockBean;
		}
		return null;
	}

	@Override
	public StockBean update(StockBean stockBean) {
		if (stockBean != null) {
			StockBean temp = this.getSession().get(StockBean.class, stockBean.getStockId());
			if (temp != null) {
				return (StockBean) this.getSession().merge(stockBean);				
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer stockId) {
		if (stockId != null) {
			StockBean temp = this.getSession().get(StockBean.class, stockId);
			if (temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public StockBean findByStockId(Integer stockId) {
		if (stockId != null) {
			StockBean temp = this.getSession().get(StockBean.class, stockId);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<StockBean> getKeyword(String keyword) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockBean> criteriaQuery = criteriaBuilder.createQuery(StockBean.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		Predicate p1 = criteriaBuilder.like(root.get("stockName"), "%" + keyword + "%");
		criteriaQuery = criteriaQuery.where(p1);		
		TypedQuery<StockBean> typedQuery = session.createQuery(criteriaQuery);
		List<StockBean> result = typedQuery.getResultList();
		return result;
	}

	@Override
	public List<StockBean> getAll(Integer typeId) {
		this.typeBean.setTypeId(typeId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockBean> criteriaQuery = criteriaBuilder.createQuery(StockBean.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("typeBean"), this.typeBean);
		Order order = criteriaBuilder.desc(root.get("stockId"));
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<StockBean> typedQuery = session.createQuery(criteriaQuery);
		List<StockBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<StockBean> getPage(Integer typeId, Integer pageNumber) {
		this.typeBean.setTypeId(typeId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockBean> criteriaQuery = criteriaBuilder.createQuery(StockBean.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get("typeBean"), this.typeBean));
		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("stockId")));
		TypedQuery<StockBean> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setFirstResult((pageNumber - 1) * 30);
		typedQuery.setMaxResults(30);
		List<StockBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public int getTypeIdTotal(Integer typeId) {
		this.typeBean.setTypeId(typeId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("typeBean"), this.typeBean);
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.multiselect(criteriaBuilder.count(root.get("stockId")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		// 回傳為 Long 型別, 不能直接轉成 int / Integer, 先轉成Number => int
		Number result = (Number) typedQuery.getSingleResult();
		return result.intValue();
	}
	
	@Override
	public StockBean getNewBook() {
		this.typeBean.setTypeId(9);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockBean> criteriaQuery = criteriaBuilder.createQuery(StockBean.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("typeBean"), 9);
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("stockId")));
		TypedQuery<StockBean> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);
		return typedQuery.getSingleResult();
	}

	@Override
	public StockBean getNewMovie() {
		this.typeBean.setTypeId(10);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockBean> criteriaQuery = criteriaBuilder.createQuery(StockBean.class);
		Root<StockBean> root = criteriaQuery.from(StockBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("typeBean"), 17);
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("stockId")));
		TypedQuery<StockBean> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);
		return typedQuery.getSingleResult();
	}
	
	

	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];  
		fis.read(buffer);  // 因為來源為硬碟, 不需使用buffer分段處理  // fis.read 回傳值為int, buffer的byte數量
		fis.close();
		return buffer;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StockDAO dao = new StockDAOHibernate(sessionFactory);
		
		StockBean bean = new StockBean();
		List<StockBean> list = new ArrayList<>();
		
		
		bean = dao.getNewBook();
		System.out.println(bean.getStockId());
		
		
		// 分頁
//		list = dao.getPage(1, 8);
//		System.out.println(list.size());
//		System.out.println(list.get(0));
//		System.out.println(list.get(29));
		
		// add
//		bean.setStockName("tibame");
//		StockTypeBean type10 = new StockTypeBean();
//		type10.setTypeId(10);
//		bean.setTypeBean(type10);
//		bean = dao.add(bean);
//		System.out.println(bean);
		
		// update
//		bean = session.get(StockBean.class, 6381);
//		bean.setStockImg(getPictureByteArray("C:/Users/User/Desktop/測試用/吳永志老師.jpeg"));
//		bean.setAuthor("hahaha");
//		bean = dao.update(bean);
//		System.out.println(bean);
		
		// delete
//		boolean result = dao.delete(6382);
//		System.out.println(result);
		
		// findByStockId
//		bean = dao.findByStockId(1);
//		System.out.println(bean.getStockName().substring(0, 3));
		
		
		
		// getKeyword
//		list = dao.getKeyword("黑色");
//		for (StockBean stock : list) {
//			System.out.println(stock);
//		}
//		System.out.println(list.size());
		
		//getAll
//		list = dao.getAll(1);
//		for (StockBean stock : list) {
//			System.out.println(stock);
//		}
//		String lists = gson.toJson(list);
//		System.out.println(lists);
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	

	

	

}
