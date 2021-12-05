package com.vendoract.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorActImg extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpg");
		
		String action = req.getParameter("action");
		ServletOutputStream out = res.getOutputStream();

		
		if("previewImg".equals(action)) {
			HttpSession session = req.getSession();
			VendorActVO vactVO = (VendorActVO) session.getAttribute("vactVO");
			if(vactVO != null && vactVO.getImg() != null) {
				out.write(vactVO.getImg());
			} else {
				return;
			}
		}
		
		if("showImg".equals(action)) {

			Integer vactid = new Integer(req.getParameter("vactid"));
			
			VendorActService vactSvc = new VendorActService();
			VendorActVO vactVO = vactSvc.findOneVendorAct(vactid);
			out.write(vactVO.getImg());
		}
		
	}
}
