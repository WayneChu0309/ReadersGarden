package com.bulletin.model;

import java.text.SimpleDateFormat;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;

public class BulletinDAOHibernate implements BulletinDAO {
	private SessionFactory sessionFactory;
	
	public BulletinDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public BulletinBean add(BulletinBean buBean) {
		if (buBean != null) {
			this.getSession().save(buBean);
			return buBean;
		}
		return null;
	}

	@Override
	public BulletinBean findByPk(Integer bulletinId) {
		if (bulletinId != null) {
			return (BulletinBean) this.getSession().get(BulletinBean.class, bulletinId);
		}
		return null;
	}
	
	@Override
	public BulletinBean findByNew() {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BulletinBean> criteriaQuery = criteriaBuilder.createQuery(BulletinBean.class);
		Root<BulletinBean> root = criteriaQuery.from(BulletinBean.class);
		Order order = criteriaBuilder.desc(root.get("bulletinId"));
		criteriaQuery = criteriaQuery.orderBy(order);
		TypedQuery<BulletinBean> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setMaxResults(1);
		return typedQuery.getSingleResult();
	}
	
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new java.util.Date()); 
		
		BulletinDAO dao = new BulletinDAOHibernate(sessionFactory);
		BulletinBean bean = new BulletinBean();
//		bean.setMemberId(1);
//		bean.setBuContent("I'm test");
//		bean.setBuDate(java.sql.Date.valueOf(date));
//		bean = dao.add(bean);
		
		bean = dao.findByNew();
		System.out.println(bean);
		
		
		
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
		
	}

	
}
