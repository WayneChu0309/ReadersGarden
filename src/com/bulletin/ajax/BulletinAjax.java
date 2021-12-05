package com.bulletin.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bulletin.model.BulletinBean;
import com.bulletin.model.BulletinDAOHibernate;
import com.bulletin.model.HibernateBulletinService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;

public class BulletinAjax extends HttpServlet {
	private HibernateBulletinService buSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		buSvc = new HibernateBulletinService(new BulletinDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String buContent = req.getParameter("buContent");
		BulletinBean buBean = new BulletinBean();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new java.util.Date()); 
		buBean.setBuContent(buContent);
		buBean.setBuDate(java.sql.Date.valueOf(date));
		buBean.setMemberId(1);
		buBean = buSvc.add(buBean);
		out.print(gson.toJson(buBean));
	}

}
