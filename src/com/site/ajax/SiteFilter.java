package com.site.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.site.model.SiteService;
import com.site.model.SiteVO;

public class SiteFilter extends HttpServlet{
	
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setCharacterEncoding("UTF-8");
		Integer area = new Integer(req.getParameter("area"));
		Integer daycost = new Integer(req.getParameter("daycost"));
		Integer capacity = new Integer(req.getParameter("capacity"));
		
		SiteService siteSvc = new SiteService();
		List<SiteVO> sitelist = siteSvc.findOptions(area, daycost, capacity);
		
		Gson gson = new Gson();
		String listJson = gson.toJson(sitelist);
		PrintWriter out = res.getWriter();
		out.write(listJson);
		
	}

}
