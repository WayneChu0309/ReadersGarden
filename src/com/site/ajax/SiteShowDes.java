package com.site.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.site.model.SiteService;
import com.site.model.SiteVO;

public class SiteShowDes extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		
		Integer siteid = new Integer(req.getParameter("siteid"));
		SiteService siteSvc = new SiteService();
		SiteVO siteVO = siteSvc.findOneSite(siteid);
		siteVO.setImg(new byte[0]);
		
		Gson gson = new Gson();
		String siteJson = gson.toJson(siteVO);

		PrintWriter out = res.getWriter();
		out.print(siteJson);		
	}
}
