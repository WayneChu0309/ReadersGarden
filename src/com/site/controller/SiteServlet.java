package com.site.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.site.model.SiteDAO;
import com.site.model.SiteService;
import com.site.model.SiteVO;
import com.siteact.model.SiteActService;
import com.vendoract.model.VendorActVO;

public class SiteServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getAll".equals(action)) { //來自siteDescription.jsp的請求
			SiteDAO dao = new SiteDAO();
			List<SiteVO> list = dao.getAll();
			req.setAttribute("list", list);
			
			// Send the Success view
			String url = "/site/siteDescription.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if("getOne_Category".equals(action)) { //來自siteDescription.jsp的請求
			
			String id = req.getParameter("actid");
			Integer actid = new Integer(id);
			
			SiteActService saSvc = new SiteActService();
			List<SiteVO> sitelist = saSvc.getOneCategory(actid);
			req.setAttribute("sitelist", sitelist);
			
			// Send the Success view
			String url = "/site/listOneCategory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if("getOne_For_Query".equals(action)) { //來自siteDescription.jsp的請求
			
			String siteidSelected = req.getParameter("siteid");
			Integer siteid = new Integer(siteidSelected);
			
			SiteService siteSvc = new SiteService();
			SiteVO siteVO = siteSvc.findOneSite(siteid);
			req.setAttribute("siteVO", siteVO);
			
			String url = "/site/siteQuery.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
		}
		
		if("getOne_For_Apply".equals(action)) { //來自siteQuery.jsp的請求
			
			HttpSession session = req.getSession();
			VendorActVO vactVO = (VendorActVO) session.getAttribute("vactVO");
			if (vactVO == null) {
				vactVO = new VendorActVO();
			} else {
				session.removeAttribute("vactVO");
				vactVO = new VendorActVO();

			}
			
			//場地名稱
			String sitename = req.getParameter("siteName");
			session.setAttribute("sitename", sitename);
			
			//總天數
			String dayTotal = req.getParameter("dayTotal");
			session.setAttribute("dayTotal", dayTotal);
			
			//場地代號
			String siteid = req.getParameter("siteid");
			vactVO.setSiteid(new Integer(siteid));
			
			//單日費用
			String daycost = req.getParameter("daycost");
			session.setAttribute("daycost", daycost);

			//租借日期 (轉交+轉換格式)
			String rntStart = req.getParameter("rntStart");
			String rntEnd = req.getParameter("rntEnd");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date rntStartDate = null;
			Date rntEndDate = null;
			
			try {
				rntStartDate = new Date(sdf.parse(rntStart).getTime());
				rntEndDate = new Date(sdf.parse(rntEnd).getTime());

			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
			vactVO.setRtlstart(new java.sql.Date(rntStartDate.getTime()));
			vactVO.setRtlend(new java.sql.Date(rntEndDate.getTime()));
			
			session.setAttribute("vactVO", vactVO);
			
			//Date轉回 "yyyy 年 MM 月 dd 日" 秀在畫面上
			SimpleDateFormat sdfShow = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
			String rntStartFormat = sdfShow.format(rntStartDate);
			String rntEndFormat = sdfShow.format(rntEndDate);
			
			session.setAttribute("rntStartFormat", rntStartFormat);
			session.setAttribute("rntEndFormat", rntEndFormat);
			
			//取得星期幾
			SimpleDateFormat sdfWeek = new SimpleDateFormat("EEE");
			String rntStartWeek = "( " +sdfWeek.format(rntStartDate)+ " )";
			String rntEndWeek = "( " + sdfWeek.format(rntEndDate) + " )";
			
			session.setAttribute("rntStartWeek", rntStartWeek);
			session.setAttribute("rntEndWeek", rntEndWeek);
			
			String url = "/site/siteApply.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
