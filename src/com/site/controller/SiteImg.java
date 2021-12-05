package com.site.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.site.model.SiteService;
import com.site.model.SiteVO;

public class SiteImg extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpg");
		
		ServletOutputStream out = res.getOutputStream();
		SiteService siteSvc = new SiteService();
		Integer siteId = Integer.parseInt(req.getParameter("ID"));
		SiteVO site = siteSvc.findOneSite(siteId);
		out.write(site.getImg());
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		doGet(req, res);
		
	}
}
