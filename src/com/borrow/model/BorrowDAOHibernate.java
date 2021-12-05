package com.borrow.model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.NoResultException;
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
import com.stock.model.StockBean;

public class BorrowDAOHibernate implements BorrowDAO {
	private SessionFactory sessionFactory;
	private StockBean stockBean;

	public BorrowDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.stockBean = new StockBean();
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public BorrowBean add(BorrowBean borrowBean) {
		if (borrowBean != null) {
			this.getSession().save(borrowBean);
			return borrowBean;
		}
		return null;
	}

	@Override
	public BorrowBean update(BorrowBean borrowBean) {
		if (borrowBean != null) {
			BorrowBean temp = this.getSession().get(BorrowBean.class, borrowBean.getBorrowId());
			if (temp != null) {
				return (BorrowBean) this.getSession().merge(borrowBean);
			}
		}
		return null;
	}

	@Override
	public int notOrder(Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		Predicate p2 = criteriaBuilder.lessThan(root.get("boStates"), 4);
		Predicate p3 = criteriaBuilder.equal(root.get("boStates"), 8);
		Predicate p4 = criteriaBuilder.or(p2, p3);
		criteriaQuery = criteriaQuery.where(p1, p4);
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root.get("borrowId")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		Number result = (Number) typedQuery.getSingleResult();
		return result.intValue();
	}

	@Override
	public BorrowBean findByPk(Integer borrowId) {
		if (borrowId != null) {
			return (BorrowBean) this.getSession().get(BorrowBean.class, borrowId);
		}
		return null;
	}

	@Override
	public BorrowBean findByNumberPreOrder(Integer memberId, Integer stockId) {
		this.stockBean.setStockId(stockId);
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		Predicate p2 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p3 = criteriaBuilder.lessThan(root.get("boStates"), 4);
		Predicate p4 = criteriaBuilder.equal(root.get("boStates"), 8);
		Predicate p5 = criteriaBuilder.or(p3, p4);
		criteriaQuery = criteriaQuery.where(p1, p2, p5);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		BorrowBean result = null;
		// 無符合條件會發生 NoResultException 例外
		try {
			result = typedQuery.getSingleResult();			
		} catch (NoResultException e){
		}
		return result;
	}
	
	@Override
	public List<BorrowBean> findByNumberBorrow(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("boStates"), 8);
		Predicate p3 = criteriaBuilder.lessThan(root.get("boStates"), 4);
		Predicate p4 = criteriaBuilder.or(p2, p3);
		Order order = criteriaBuilder.desc(root.get("borrowId"));
		Order order2 = criteriaBuilder.desc(root.get("boStates"));
		criteriaQuery = criteriaQuery.where(p1, p4);
		criteriaQuery = criteriaQuery.orderBy(order2, order);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findByNumberAllBorrow(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Order order = criteriaBuilder.desc(root.get("borrowId"));
		Order order2 = criteriaBuilder.asc(root.get("score"));
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.orderBy(order2, order);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findByOverDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date nowDate = java.sql.Date.valueOf(df.format(new java.util.Date()));
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("boStates"), 2);
		Predicate p2 = criteriaBuilder.lessThan(root.get("preReDate"), nowDate);
		Predicate p3 = criteriaBuilder.isNull(root.get("actReDate"));
		criteriaQuery = criteriaQuery.where(p1, p2, p3);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findByPreOrderNoBorrow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date nowDate = java.sql.Date.valueOf(df.format(new java.util.Date()));
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("boStates"), 1);
		Predicate p2 = criteriaBuilder.equal(root.get("boStates"), 8);
		Predicate p3 = criteriaBuilder.lessThan(root.get("preBoDate"), nowDate);
		Predicate p4 = criteriaBuilder.isNull(root.get("actBoDate"));
		Predicate p5 = criteriaBuilder.or(p1, p2);
		criteriaQuery = criteriaQuery.where(p3, p4, p5);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findAllOverDate() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("boStates"), 3);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findByNumberIlleglBorrow(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.lessThan(root.get("boStates"), 8);
		Predicate p3 = criteriaBuilder.greaterThan(root.get("boStates"), 2);
		Predicate p4 = criteriaBuilder.notEqual(root.get("boStates"), 4);
		Order order = criteriaBuilder.desc(root.get("borrowId"));
		criteriaQuery = criteriaQuery.where(p1, p2, p3, p4);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public int getIlleglNumber(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.lessThan(root.get("boStates"), 8);
		Predicate p3 = criteriaBuilder.greaterThan(root.get("boStates"), 2);
		Predicate p4 = criteriaBuilder.notEqual(root.get("boStates"), 4);
		criteriaQuery = criteriaQuery.where(p1, p2, p3, p4);
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root.get("borrowId")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		Number result = (Number) typedQuery.getSingleResult();
		return result.intValue();
	}

	

	@Override
	public List<BorrowBean> findByNumberSuccessBorrow(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("boStates"), 4);
		Order order = criteriaBuilder.desc(root.get("borrowId"));
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public int getSuccessNumber(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("boStates"), 4);
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root.get("borrowId")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		Number result = (Number) typedQuery.getSingleResult();
		return result.intValue();
	}
	
	@Override
	public List<BorrowBean> findByAllOrder() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("boStates"), 1);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public List<BorrowBean> findByAllCancel() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("boStates"), 6);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public Double findByStockScore(Integer stockId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		this.stockBean.setStockId(stockId);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		criteriaQuery = criteriaQuery.where(p1);
		criteriaQuery = criteriaQuery.select(criteriaBuilder.avg(root.get("score")));
		TypedQuery<Object> typedQuery = session.createQuery(criteriaQuery);
		try {
			Number result = (Number) typedQuery.getSingleResult();
			return result.doubleValue();			
		} catch(Exception e) {}
		
		return null;
	}
	

	@Override
	public List<BorrowBean> findAllByScore(Integer stockId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BorrowBean> criteriaQuery = criteriaBuilder.createQuery(BorrowBean.class);
		Root<BorrowBean> root = criteriaQuery.from(BorrowBean.class);
		this.stockBean.setStockId(stockId);
		Predicate p1 = criteriaBuilder.equal(root.get("stockBean"), this.stockBean);
		Predicate p2 = criteriaBuilder.isNotNull(root.get("score"));
		Predicate p3 = criteriaBuilder.isNotNull(root.get("scoreContent"));
		Predicate p4 = criteriaBuilder.isNotNull(root.get("scoreDate"));
		Order order = criteriaBuilder.desc(root.get("borrowId"));
		criteriaQuery = criteriaQuery.where(p1, p2, p3, p4);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<BorrowBean> typedQuery = session.createQuery(criteriaQuery);
		List<BorrowBean> result = typedQuery.getResultList();
		return result;
	}

	



	
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		BorrowDAO dao = new BorrowDAOHibernate(sessionFactory);
		BorrowBean bean = new BorrowBean();
		
		// findAllByScore
//		List<BorrowBean> result = dao.findAllByScore(100);
//		System.out.println(result.isEmpty());
		
		
		// findByStockScore
//		Double result = dao.findByStockScore(217);
//		System.out.println(result);
		
		
		// findByNumberAllBorrow
//		List<BorrowBean> result = dao.findByNumberAllBorrow(2);
//		System.out.println(result.size());
		
		
		
//		result = dao.findByNumberIllgelBorrow(1);
//		System.out.println(result.size());
		
//		int illgel = dao.getIllgelNumber(1);
//		
//		int success = dao.getSuccessNumber(1);
//		System.out.println("illgel=" + illgel);
//		System.out.println("success=" +success);
		
		// findByPreOrderNoBorrow
//		List<BorrowBean> result = dao.findByPreOrderNoBorrow();
//		System.out.println(result.size());
//		for (BorrowBean beans : result) {
//			System.out.println(beans);
//			beans.setBoStates(6);
//			dao.update(beans);
//		}
		
		
		
//		 findByOverDate
//		List<BorrowBean> result = dao.findByOverDate();
//		System.out.println(result.size());
//		for (BorrowBean beans : result) {
//			beans.setBoStates(3);
//			dao.update(beans);
//		}
		
		
		// findByNumberBorrow
//		List<BorrowBean> result = dao.findByNumberBorrow(1);
//		System.out.println(result.size());
//		for (BorrowBean beans : result) {
//			String s = gson.toJson(beans);
//			System.out.println(s);
//		}
		
		
		String date[] = {"","2021-09-10", "2021-09-10", "2021-09-10"};
		String date1[] = {"","2021-09-10", "2021-09-10", "2021-09-10"};
		String date2[] = {"","2021-11-2", "2021-09-10", "2021-09-10"};
		// add
		for (int i = 1; i <= 10; i ++) {
			bean = new BorrowBean();
			StockBean stockBean = new StockBean();
			stockBean.setStockId(i);
			bean.setMemberId(2);
			bean.setPreBoDate(java.sql.Date.valueOf("2021-10-02"));
			bean.setActBoDate(java.sql.Date.valueOf("2021-10-01"));
			bean.setPreReDate(java.sql.Date.valueOf("2021-11-02"));
			bean.setListId(i*3);
			bean.setBoStates(3);
			bean.setStockBean(stockBean);
			bean = dao.add(bean);
			System.out.println(bean);
		}
//		
//		for (int i = 5000; i < 5030; i ++) {
//			BorrowBean bean = new BorrowBean();
//			StockBean stockBean = new StockBean();
//			stockBean.setStockId(i);
//			bean.setMemberId(1);
//			bean.setPreBoDate(java.sql.Date.valueOf("2021-10-13"));
//			bean.setPreReDate(java.sql.Date.valueOf("2021-11-13"));
//			bean.setBoStates(1);
//			bean.setStockBean(stockBean);
//			bean = dao.add(bean);
//			System.out.println(bean);
//		}

		
		// update
//		bean = dao.findByPk(5);
//		bean.setActBoDate(java.sql.Date.valueOf("2021-10-12"));
//		bean.setListId(199);
//		bean = dao.update(bean);
//		System.out.println(bean);
		
		
		// notOrder
//		int notOrder = dao.notOrder(4029);
//		System.out.println(notOrder);
		
		
		// findByPk
//		bean = dao.findByPk(310);
//		System.out.println(bean);

		
		// findByNumberPreOrder
//		bean = dao.findByNumberPreOrder(1, 218);
//		System.out.println(bean);
//		int i = dao.findByNumberSuccessBorrow(1);
//		System.out.println(i);
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}


}
