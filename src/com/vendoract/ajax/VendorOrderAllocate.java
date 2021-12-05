package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorOrderAllocate extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Type", "text/json; charset=UTF-8");

		String tkstatus = req.getParameter("tkstatus");
		Integer vendorid = new Integer(req.getParameter("vendorid"));
				
		VendorActService vactSvc = new VendorActService();
		List<VendorActVO> vactlist = new ArrayList<>();
		
		if ("所有活動".equals(tkstatus)) {
			vactlist = vactSvc.findByVendorid(vendorid);
			
		} else if ("申請中".equals(tkstatus)) {
			List<VendorActVO> vactlistAll = vactSvc.findByVendorid(vendorid);
			
			for(VendorActVO vactVO :  vactlistAll) {
				Integer progress = new Integer(vactVO.getProgress());
				if(progress < 3) {
					vactlist.add(vactVO);
				}
			}
		} else if("已取消".equals(tkstatus)){
			vactlist = vactSvc.findCanceledOrder(vendorid);
		} else {
			vactlist = vactSvc.findByTkDate(vendorid, tkstatus);
		}
		
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		
		String vactlistJson = gson.toJson(vactlist);
		out.write(vactlistJson);
	}
}
