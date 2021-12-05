package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorActProgress extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		Integer vactid = new Integer(req.getParameter("vactid"));
		Integer progress = new Integer(req.getParameter("progress"));
		VendorActService vactSvc = new VendorActService();
		vactSvc.updateProgress(vactid, progress);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String str = gson.toJson("success");
		out.write(str);
		
		return;
	}
	
}
