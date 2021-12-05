package com.marks.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hibernate.HibernateUtil;
import com.marks.model.HibernateMarksService;
import com.marks.model.MarksBean;
import com.marks.model.MarksDAOHibernate;


public class MarksAjax extends HttpServlet {
	private HibernateMarksService markSvc;
	
	public void init() throws ServletException {
		markSvc = new HibernateMarksService(new MarksDAOHibernate(HibernateUtil.getSessionFactory()));
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		MarksBean marksBean = new MarksBean();
		
		if ("confirm".equals(action)) {
			Integer memberId = new Integer(req.getParameter("memberId"));
			Integer stockId = new Integer(req.getParameter("stockId"));
			marksBean.setMemberId(memberId);
			marksBean.setStockId(stockId);
			marksBean = markSvc.add(marksBean);
			if (marksBean != null) {
				out.print(1);
			}			
		}
		
		if ("cancel".equals(action)) {
			Integer marksId = new Integer(req.getParameter("marksId"));
			marksBean = markSvc.findByPk(marksId);
			marksBean.setMarkStatus(2);
			marksBean = markSvc.update(marksBean);
			if (marksBean != null) {
				out.print(1);
			}
		}
		
		
	}

}
