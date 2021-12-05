package com.order.model;

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

import com.hibernate.HibernateUtil;

public class OrderDAOHibernate implements OrderDAO {
	private SessionFactory sessionFactory;
	
	public OrderDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public OrderBean add(OrderBean orderBean) {
		if (orderBean != null) {
			this.getSession().save(orderBean);
			return orderBean;
		}
		return null;
	}

	@Override
	public OrderBean update(OrderBean orderBean) {
		if (orderBean != null) {
			OrderBean temp = this.getSession().get(OrderBean.class, orderBean.getOrderId());
			if (temp != null) {
				return (OrderBean) this.getSession().merge(orderBean);				
			}
		}
		return null;
	}
	
	@Override
	public OrderBean findByPk(Integer orderId) {
		if (orderId != null) {
			OrderBean temp = this.getSession().get(OrderBean.class, orderId);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<OrderBean> findByMemberNotPay(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OrderBean> criteriaQuery = criteriaBuilder.createQuery(OrderBean.class);
		Root<OrderBean> root = criteriaQuery.from(OrderBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("orderStatus"), 1);
		Order order = criteriaBuilder.desc(root.get("orderId"));
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<OrderBean> typedQuery = session.createQuery(criteriaQuery);
		List<OrderBean> result = typedQuery.getResultList();
		return result;
	}

	@Override
	public List<OrderBean> findByMemberPay(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OrderBean> criteriaQuery = criteriaBuilder.createQuery(OrderBean.class);
		Root<OrderBean> root = criteriaQuery.from(OrderBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("orderStatus"), 2);
		Order order = criteriaBuilder.desc(root.get("orderId"));
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<OrderBean> typedQuery = session.createQuery(criteriaQuery);
		List<OrderBean> result = typedQuery.getResultList();
		return result;
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		OrderDAO dao = new OrderDAOHibernate(sessionFactory);
		
		OrderBean orderBean = new OrderBean();
		List<OrderBean> result = new ArrayList<>();
		//add
//		orderBean.setMemberId(1);
//		orderBean.setVactId(1);
//		orderBean.setOrdercount(3);
//		orderBean.setTotal(3000);
//		orderBean = dao.add(orderBean);
		
		//update
//		orderBean = dao.findByPk(1);
//		orderBean.setOrdertime(new java.sql.Timestamp(System.currentTimeMillis()));
//		orderBean.setOrderStatus(2);
//		
//		orderBean = dao.update(orderBean);
		result = dao.findByMemberPay(2);
		
		System.out.println(result);
		
		
		
		
		
		
		
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	

	

}
