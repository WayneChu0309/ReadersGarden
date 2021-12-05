package com.site.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.siteact.model.*;
import com.google.gson.Gson;

public class SiteShowCate extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		
		Integer siteid = new Integer(req.getParameter("siteid"));
		SiteActService siteactSvc = new SiteActService();
		ActivityService actSvc = new ActivityService();
		List<SiteActVO> salist = siteactSvc.getAll();
		
		String category = "";
		
		for(SiteActVO saVO : salist) {
			Integer saVOsiteid = saVO.getSiteid();
			if(saVOsiteid.equals(siteid)) {
				category += (actSvc.findOneActivity(saVO.getActid())).getActtype()+ " ";
			}
		}

		Gson gson = new Gson();
		String cateJson = gson.toJson(category);
		
		PrintWriter out = res.getWriter();
		out.write(cateJson);
		
	}
}
