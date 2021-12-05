package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.statusname.model.StatusNameService;
import com.statusname.model.StatusNameVO;
import com.vendoract.model.VendorActService;

public class CheckVendorActProgress extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Integer vactid = new Integer(req.getParameter("vactid"));
		VendorActService vactSvc = new VendorActService();
		String progress = vactSvc.checkProgress(vactid);
		
		StatusNameService snSvc = new StatusNameService();
		StatusNameVO snVO = snSvc.findOneStatusName(new Integer(progress));
		
		Map<String, String> progressMap = new HashMap<>();
		progressMap.put("progress", progress);
		progressMap.put("progressStr", snVO.getStatus());
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String progressMapStr = gson.toJson(progressMap);
		out.write(progressMapStr);
		
		return;
	}
	
}
