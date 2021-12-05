package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.vendoract.controller.VendorActApply;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class NewVendorAct extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		PrintWriter out = res.getWriter();

		HttpSession session = req.getSession();
		VendorActVO vactVO = (VendorActVO) session.getAttribute("vactVO");
		
		// 新增前再次確認場地未預定
		Integer siteid = vactVO.getSiteid();
		System.out.println(siteid);
		Date rtlstart = vactVO.getRtlstart();
		Date rtlend = vactVO.getRtlend();
		
		VendorActApply vactApply = new VendorActApply();
		Boolean occupied = vactApply.checkIfOccupied(siteid, rtlstart, rtlend);
		
		if(occupied) {
			System.out.println("occupied");
			session.removeAttribute("sitename");
			session.removeAttribute("daycost");
			vactVO.setSiteid(null);
			out.write("errorMsgs");
			return;
		}
		
		
		// 開始新增
		VendorActService vactSvc = new VendorActService();
		String vactid = (vactSvc.addVendorAct(vactVO)).toString();
		
		//新增完成, 刪除session裡的資料
		session.removeAttribute("vactVO");
		session.removeAttribute("sitename");
		session.removeAttribute("daycost");
		session.removeAttribute("rntStartFormat");
		session.removeAttribute("rntStartWeek");
		session.removeAttribute("rntEndFormat");
		session.removeAttribute("rntEndWeek");
		session.removeAttribute("dayTotal");
		
		out.write(vactid);
		
		return;
	}
}