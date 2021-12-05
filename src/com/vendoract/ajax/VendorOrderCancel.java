package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorOrderCancel extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Integer vactid = new Integer(req.getParameter("vactid"));
		System.out.println(vactid);
		
		VendorActService vactSvc = new VendorActService();
		VendorActVO vactVO = vactSvc.findOneVendorAct(vactid);
		vactVO.setProgress("99");
		vactSvc.updateVendorAct(vactVO);
		out.print(1);
		
		return;
	}
}