package com.borrow.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borrow.model.BorrowBean;
import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.member.model.MemberService;

public class BorrowContentAjax extends HttpServlet {
	private HibernateBorrowService borrowSvc;
	private Gson gson;
	private MemberService memSvc;
	
	
	@Override
	public void init() throws ServletException {
		borrowSvc = new HibernateBorrowService(new BorrowDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		memSvc = new MemberService();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Integer stockId = new Integer(req.getParameter("stockId"));
		List<BorrowBean> boList = borrowSvc.findAllByScore(stockId);

		if (boList.isEmpty()) {
			out.print(0);
			return;
		} else {
			LinkedHashMap<Integer, Map<String, BorrowBean>> scoreContent = new LinkedHashMap<>();
			for (Integer i = 0; i < boList.size(); i++) {
				Map<String, BorrowBean> outMap = new HashMap<String, BorrowBean>();
				BorrowBean bean = boList.get(i);
				BorrowBean outBean = new BorrowBean();
				outBean.setScore(bean.getScore());
				outBean.setScoreContent(bean.getScoreContent());
				outBean.setScoreDate(bean.getScoreDate());
				String member = memSvc.getOneMember(bean.getMemberId()).getName();
				String outmember = member.charAt(0) + "*****" + member.charAt(member.length() - 1);
				outMap.put(outmember, outBean);
				scoreContent.put(i, outMap);
			}			
			out.print(gson.toJson(scoreContent));
			return;
		}
	}

}
