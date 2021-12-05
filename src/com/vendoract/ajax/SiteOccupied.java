package com.vendoract.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

public class SiteOccupied extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setCharacterEncoding("UTF-8");

		Integer siteid = new Integer(req.getParameter("siteid"));
		Integer year = new Integer(req.getParameter("year"));
		Integer month = new Integer(req.getParameter("month"));

		VendorActService vactSvc = new VendorActService();
		List<VendorActVO> vactlist = vactSvc.findOccupied(siteid, year, month);

		Set<Integer> daylist = new HashSet<>();

		for (VendorActVO vact : vactlist) {
			Date rtlstart = vact.getRtlstart();
			Date rtlend = vact.getRtlend();

			// 取年
			SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
			int startYear = Integer.valueOf(sdf_year.format(rtlstart));
			int endYear = Integer.valueOf(sdf_year.format(rtlend));

			// 取月
			SimpleDateFormat sdf_month = new SimpleDateFormat("MM");
			int startMonth = Integer.valueOf(sdf_month.format(rtlstart));
			int endMonth = Integer.valueOf(sdf_month.format(rtlend));

			// 取日
			SimpleDateFormat sdf_day = new SimpleDateFormat("dd");
			int startDay = Integer.valueOf(sdf_day.format(rtlstart));
			int endDay = Integer.valueOf(sdf_day.format(rtlend));

			System.out.println(startYear);
			System.out.println(endYear);

			if (startYear == year && endYear == year && startMonth == month && endMonth == month) { // start, end都在本年月
				for (int i = startDay; i <= endDay; i++) {
					daylist.add(i);
//					System.out.println("start, end都在本年月");
				}

			} else if (startYear == year && startMonth == month && endYear > year) { // 只有start在本年月
				int totalDay = getMonthLastDay(year, month);
				for (int i = startDay; i <= totalDay; i++) {
					daylist.add(i);
//					System.out.println("只有start在本年月");
				}

			} else if (startYear == year && startMonth == month && endYear == year && endMonth != month) { // 只有start在本年月
				int totalDay = getMonthLastDay(year, month);
				for (int i = startDay; i <= totalDay; i++) {
					daylist.add(i);
//					System.out.println("只有start在本年月");
				}

			} else if (startYear <= year && startMonth != month && endYear == year && endMonth == month) { // 只有end在本月
				for (int i = 1; i <= endDay; i++) {
					daylist.add(i);
//					System.out.println("只有end在本月");
				}
			} else if (startYear == year && endYear == year && startMonth < month && endMonth > month) { // 選擇月份在兩者之間
			
				int totalDay = getMonthLastDay(year, month);
				for (int i = 1; i <= totalDay; i++) {
					daylist.add(i);
//					System.out.println("選擇月份在兩者之間");
				}
			} else if (endYear == year && year > startYear && endMonth > month) {
				int totalDay = getMonthLastDay(year, month);
				for (int i = 1; i <= totalDay; i++) {
					daylist.add(i);
//					System.out.println("選擇月份在兩者之間");
				}
			} else if (endYear > year && year > startYear) {
				int totalDay = getMonthLastDay(year, month);
				for (int i = 1; i <= totalDay; i++) {
					daylist.add(i);
//					System.out.println("選擇月份在兩者之間");
				
			}

		}

	}

	PrintWriter out = res.getWriter();
	Gson gson = new Gson();
	String dayJson = gson.toJson(daylist);
	out.write(dayJson);

	}

	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期設定為當月第一天
		a.roll(Calendar.DATE, -1);// 日期回滾一天，也就是最後一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
}
