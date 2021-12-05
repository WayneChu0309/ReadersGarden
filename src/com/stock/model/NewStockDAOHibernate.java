package com.stock.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NewStockDAOHibernate implements NewStockDAO {
	private SessionFactory sessionFactory;
	
	public NewStockDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<NewStock> findNew() {
		List<NewStock> result = new ArrayList<>();
		NewStock temp = this.getSession().get(NewStock.class, 1);
		NewStock temp2 = this.getSession().get(NewStock.class, 2);
		result.add(temp);
		result.add(temp2);
		return result;
	}

	@Override
	public void update(NewStock newstock) {
		if (newstock != null) {
			NewStock temp = this.getSession().get(NewStock.class, newstock.getNewId());
			if (temp != null) {
				this.getSession().merge(newstock);				
			}	
		}
	}

	@Override
	public NewStock findByPk(Integer newId) {
		if (newId != null) {
			NewStock temp = this.getSession().get(NewStock.class, newId);
			if (temp != null) {
				return temp;				
			}	
		}
		return null;
	}

}
