package com.stockType.model;

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

public class StockTypeDAOHibernate implements StockTypeDAO {
	private SessionFactory sessionFactory;
	
	public StockTypeDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public StockTypeBean add(StockTypeBean stockType) {
		if (stockType != null) {
			this.getSession().save(stockType);
			return stockType;
		}
		return null;
	}

	@Override
	public StockTypeBean findByTypeId(Integer typeId) {
		if (typeId != null) {
			return this.getSession().get(StockTypeBean.class, typeId);
		}
		return null;
	}

	@Override
	public List<StockTypeBean> getBook() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockTypeBean> criteriaQuery = criteriaBuilder.createQuery(StockTypeBean.class);
		Root<StockTypeBean> root = criteriaQuery.from(StockTypeBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("kind"), "書籍");
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<StockTypeBean> typedQuery = session.createQuery(criteriaQuery);
		
		List<StockTypeBean> result = typedQuery.getResultList();
		
		return result;
	}

	@Override
	public List<StockTypeBean> getMovie() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockTypeBean> criteriaQuery = criteriaBuilder.createQuery(StockTypeBean.class);
		Root<StockTypeBean> root = criteriaQuery.from(StockTypeBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("kind"), "電影");
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<StockTypeBean> typedQuery = session.createQuery(criteriaQuery);
		
		List<StockTypeBean> result = typedQuery.getResultList();
		
		return result;
	}

	@Override
	public List<StockTypeBean> getAll() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<StockTypeBean> criteriaQuery = criteriaBuilder.createQuery(StockTypeBean.class);
		Root<StockTypeBean> root = criteriaQuery.from(StockTypeBean.class);
		TypedQuery<StockTypeBean> typedQuery = session.createQuery(criteriaQuery);
		List<StockTypeBean> result = typedQuery.getResultList();
		
		return result;
	}
	
	@Override
	public List<StockBean> findByTypeIdTotal(Integer typeId) {
		if (typeId != null) {
			return this.getSession().get(StockTypeBean.class, typeId).getStocks();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		StockTypeDAO dao = new StockTypeDAOHibernate(sessionFactory);
		
		StockTypeBean bean = new StockTypeBean();
		bean.setKind("書籍");
		bean.setTypeName("測試用");
		bean = dao.add(bean);
		System.out.println(bean);
//		List<StockBean> beans = dao.findByTypeIdTotal(16);
//		String list = gson.toJson(beans);
//		System.out.println(list);
//		System.out.println(beans);
//		for (int i = 0; i < 30; i++) {
//			System.out.println(beans.get(i));
//		}
		
		
		
//		StockTypeBean findByPk = dao.findByTypeId(1);
//		System.out.println("findByPk"+findByPk);
//		System.out.println("==================");
//		List<StockTypeBean> bookList = dao.getBook();
//		System.out.println("bookList"+bookList);
//		System.out.println("==================");
//		
//		List<StockTypeBean> movieList = dao.getMovie();
//		System.out.println("movieList"+movieList);
//		System.out.println("==================");
//		
//		List<StockTypeBean> allList = dao.getAll();
//		String list = gson.toJson(allList);
//		System.out.println("allList"+allList);
//		
//		System.out.println("==================");
//		System.out.println(list);
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	

	
	
}
