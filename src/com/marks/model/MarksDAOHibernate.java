package com.marks.model;

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

import com.hibernate.HibernateUtil;


public class MarksDAOHibernate implements MarksDAO {
	private SessionFactory sessionFactory;
		
	public MarksDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public MarksBean add(MarksBean marksBean) {
		if (marksBean != null) {
			this.getSession().save(marksBean);
			return marksBean;
		}
		return null;
	}

	@Override
	public MarksBean update(MarksBean marksBean) {
		if (marksBean != null) {
			MarksBean temp = this.getSession().get(MarksBean.class, marksBean.getMarksId());
			if (temp != null) {
				return (MarksBean) this.getSession().merge(marksBean);				
			}
		}
		return null;
	}
	
	@Override
	public MarksBean findByPk(Integer marksId) {
		if (marksId != null) {
			MarksBean temp = this.getSession().get(MarksBean.class, marksId);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}
	
	@Override
	public boolean findByMember(Integer memberId, Integer stockId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MarksBean> criteriaQuery = criteriaBuilder.createQuery(MarksBean.class);
		Root<MarksBean> root = criteriaQuery.from(MarksBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("stockId"), stockId);
		Predicate p3 = criteriaBuilder.equal(root.get("markStatus"), 1);
		criteriaQuery = criteriaQuery.where(p1, p2, p3);
		TypedQuery<MarksBean> typedQuery = session.createQuery(criteriaQuery);
		MarksBean result = null;
		try {
			result = typedQuery.getSingleResult();
			return true;
		} catch(NoResultException e) {
			return false;			
		}
	}

	@Override
	public List<MarksBean> findMemberAll(Integer memberId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MarksBean> criteriaQuery = criteriaBuilder.createQuery(MarksBean.class);
		Root<MarksBean> root = criteriaQuery.from(MarksBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("memberId"), memberId);
		Predicate p2 = criteriaBuilder.equal(root.get("markStatus"), 1);
		Order order = criteriaBuilder.desc(root.get("marksId"));
		criteriaQuery = criteriaQuery.where(p1, p2);
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<MarksBean> typedQuery = session.createQuery(criteriaQuery);
		List<MarksBean> result = typedQuery.getResultList();
		return result;
	}
	
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		MarksDAO dao = new MarksDAOHibernate(sessionFactory);
		
//		MarksBean marksBean = dao.findByPk(1);
//		marksBean.setMemberId(1);
//		marksBean.setStockId(20);
//		marksBean.setMarkStatus(2);
//		marksBean = dao.update(marksBean);
//		System.out.println(marksBean);
		
		List<MarksBean> result = dao.findMemberAll(2);
		System.out.println(result);
		
//		boolean s = dao.findByMember(null, 220);
//		System.out.println(s);
		
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	

	

}
