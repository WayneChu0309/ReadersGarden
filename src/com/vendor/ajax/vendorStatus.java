package com.vendor.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vendor.model.VendorService;

public class vendorStatus extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");

		Integer vendorid = new Integer(req.getParameter("vendorid"));
		String status = req.getParameter("status");
		VendorService vendorSvc = new VendorService();
		vendorSvc.updateStatus(vendorid, status);
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String str = gson.toJson("success");
		out.write(str);
		
		return;
	}
	
}
