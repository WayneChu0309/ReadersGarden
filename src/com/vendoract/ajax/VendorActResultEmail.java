package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vendor.model.VendorEmailService;
import com.vendor.model.VendorService;
import com.vendoract.model.VendorActService;

public class VendorActResultEmail extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setHeader("Content-Type", "text/json; charset=UTF-8");
		
		Integer vendorid = new Integer(req.getParameter("vendorid"));
		Integer vactid = new Integer(req.getParameter("vactid"));
		String result = req.getParameter("result").trim();
		
		VendorActService vactSvc = new VendorActService();
		VendorService venSvc = new VendorService();
		String email = venSvc.findOneVendor(vendorid).getEmail();
		
		String subject = "《Reader's Garden》訂單編號" + vactid + "審核結果通知";
		String messageText = "";
		
		if("通過".equals(result)) {
			messageText ="您申請的訂單編號:" + vactid + "已通過審核，請依如下付款訊息安排付款，謝謝。"+ "\n" + "巴拉巴拉巴拉";
		} else {
			vactSvc.addNote(vactid, result);
			messageText = "很抱歉，您申請的訂單編號:" + vactid + "已被取消，原因為:《" + result + "》, 請調整後再進行申請，謝謝。";
		}
		System.out.println(messageText);
		VendorEmailService emailService = new VendorEmailService();
		emailService.sendMail(email, subject, messageText);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String str = gson.toJson("success");
		out.write(str);
		
		return;
	}

}
