package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.google.gson.Gson;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class VendorActCheck extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Type", "text/json; charset=UTF-8");

		
		//============ 抓request資料&查詢 ============//

		Integer vactid = new Integer(req.getParameter("vactid"));
		VendorActService vactSvc = new VendorActService();
		VendorService venSvc = new VendorService();
		VendorActVO vactVO = vactSvc.findOneVendorAct(vactid);
		
		
		//============ 轉換成需要的格式, 丟到Map裡 ============//

		Map<String, String> vactInfo = new HashMap<>();

		//活動名稱
		vactInfo.put("actname", vactVO.getName());
		
		//活動進度
		vactInfo.put("progress", vactVO.getProgress());
		
		//訂單編號
		vactInfo.put("vactid", Integer.toString(vactVO.getVactid()));
		
		//活動類型
		ActivityService actSvc = new ActivityService();
		vactInfo.put("vacttype", actSvc.findOneActivity(vactVO.getActid()).getActtype());

		//活動日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String actstart = sdf.format(vactVO.getActstart());
		String actend = sdf.format(vactVO.getActend());
		vactInfo.put("actstart", actstart);
		vactInfo.put("actend", actend);
		
		//售票日期
		String tkstart = sdf.format(vactVO.getTkstart());
		String tkend = sdf.format(vactVO.getTkend());
		vactInfo.put("tkstart", tkstart);
		vactInfo.put("tkend", tkend);

		//票價
		vactInfo.put("price", Integer.toString(vactVO.getPrice()));
		
		//活動內容
		vactInfo.put("actcnt", vactVO.getActcnt());
		
		//訂單備註
		vactInfo.put("note", vactVO.getNote());
		System.out.println(vactVO.getNote());
		
		//廠商資訊
		Integer vendorid = vactVO.getVendorid();
		VendorVO vendorVO = venSvc.findOneVendor(vendorid);
		
		String company = vendorVO.getCompany();
		String addr = vendorVO.getAddr();
		Integer taxid = vendorVO.getTaxid();
		String name = vendorVO.getName();
		String tel = vendorVO.getTel();
		
		vactInfo.put("vendorid", Integer.toString(vendorid));
		vactInfo.put("company", company);
		vactInfo.put("addr", addr);
		vactInfo.put("taxid", Integer.toString(taxid));
		vactInfo.put("name", name);
		vactInfo.put("tel", tel);
		
		//============ 轉gson輸出 ============//
		Gson gson = new Gson();
		String vactInfoJson = gson.toJson(vactInfo);
		
		PrintWriter out = res.getWriter();
		out.write(vactInfoJson);
		
	}
}
