package com.hibernate;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import connectionpool.JedisUtil;
@WebListener
public class SessionFactoryListener implements ServletContextListener {
	ServletContext context;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		context = sce.getServletContext();
		context.setAttribute("capacity", 500);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.closeSessionFactory();
		JedisUtil.shutdownJedisPool();
		context = sce.getServletContext();
		context.removeAttribute("capacity");
	}
}
