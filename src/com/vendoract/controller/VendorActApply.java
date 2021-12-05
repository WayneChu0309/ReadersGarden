package com.vendoract.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.vendor.model.VendorVO;
import com.vendoract.model.VendorActService;
import com.vendoract.model.VendorActVO;

@MultipartConfig
public class VendorActApply extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("new_Apply".equals(action)) {

			HttpSession session = req.getSession();
			session.removeAttribute("vactVO");
			session.removeAttribute("sitename");
			session.removeAttribute("daycost");
			session.removeAttribute("rntStartFormat");
			session.removeAttribute("rntStartWeek");
			session.removeAttribute("rntEndFormat");
			session.removeAttribute("rntEndWeek");
			session.removeAttribute("dayTotal");

			RequestDispatcher successView = req.getRequestDispatcher("/site/siteApply.jsp");
			successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // 來自siteApplyConfirm.jsp的請求

			HttpSession session = req.getSession();
			VendorActVO vactVO = (VendorActVO) session.getAttribute("vactVO");
			
			// 開始新增
			VendorActService vactSvc = new VendorActService();
			vactSvc.addVendorAct(vactVO);
			
			//新增完成, 刪除session裡的資料
			session.removeAttribute("vactVO");
			session.removeAttribute("sitename");
			session.removeAttribute("daycost");
			session.removeAttribute("rntStartFormat");
			session.removeAttribute("rntStartWeek");
			session.removeAttribute("rntEndFormat");
			session.removeAttribute("rntEndWeek");
			session.removeAttribute("dayTotal");

			// 新增完成, 準備轉交
			String url = "/home/home.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	//siteApply.jsp
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Collection<Part> parts = req.getParts();
		Map<String, String> errorMsgs = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = req.getSession();
		
		//驗證日期用
		java.sql.Date rtlstart = null;
		java.sql.Date rtlend = null;
		java.sql.Date actstart = null;
		java.sql.Date actend = null;
		String sitename = null;
		String daycost = null;
		Integer siteid = null;

		
		VendorActVO vactVO = (VendorActVO) session.getAttribute("vactVO");
		if (vactVO == null) {
			vactVO = new VendorActVO();
		}

		String action = null;

		for (Part part : parts) {
			String partName = part.getName();
//			System.out.println(partName);

			if (partName.equals("action")) {
				action = req.getParameter("action");
//				System.out.println(action);
			}

			// ***左側資訊***//
			// 場地名稱
			else if (partName.equals("sitename")) {
				sitename = req.getParameter("sitename");
			}

			// 單日費用
			else if (partName.equals("daycost")) {
				daycost = req.getParameter("daycost");
			}

			// 場地代號
			else if (partName.equals("siteid")) {
				siteid = new Integer(req.getParameter("siteid"));
//				System.out.println(siteid);
			}

			// 租借開始日期
			else if (partName.equals("rntStart")) {
				String rntStart = req.getParameter("rntStart");
				try {
					rtlstart = new java.sql.Date(sdf.parse(rntStart).getTime());
					vactVO.setRtlstart(rtlstart);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

			else if (partName.equals("rntStartFormat")) {
				String rntStartFormat = req.getParameter("rntStartFormat");
				session.setAttribute("rntStartFormat", rntStartFormat);
			}

			else if (partName.equals("rntStartW")) {
				String rntStartWeek = req.getParameter("rntStartW");
				session.setAttribute("rntStartWeek", rntStartWeek);

			}

			// 租借結束日期
			else if (partName.equals("rntEnd")) {
				String rntEnd = req.getParameter("rntEnd");
				try {
					rtlend = new java.sql.Date(sdf.parse(rntEnd).getTime());
					vactVO.setRtlend(rtlend);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (partName.equals("rntEndFormat")) {
				String rntEndFormat = req.getParameter("rntEndFormat");
				session.setAttribute("rntEndFormat", rntEndFormat);
			}

			else if (partName.equals("rntEndW")) {
				String rntEndWeek = req.getParameter("rntEndW");
				session.setAttribute("rntEndWeek", rntEndWeek);

			}

			else if (partName.equals("dayTotal")) {
				String dayTotal = req.getParameter("dayTotal");
				session.setAttribute("dayTotal", dayTotal);
			}

			// 總金額
			else if (partName.equals("total")) {
				String total = req.getParameter("total");
//				System.out.println(total);
				if (total == null || total.trim().length() == 0) {
					errorMsgs.put("left", "請選擇租借場地與時間");
				} else {
					Integer amount = new Integer(total);
					vactVO.setAmount(amount);
				}
			}
					
			// ***右側資訊***//
			// 活動名稱
			else if (partName.equals("name")) {
				String name = req.getParameter("name");
//				System.out.println(name);
				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("actname", "請填寫活動名稱");
				} else {
					vactVO.setName(name);
					String name1 = vactVO.getName();
//					System.out.println("here" + name1);
				}
			}

			// 活動類型 (input type = select)
			else if (partName.equals("actid")) {

				Integer actid = new Integer(req.getParameter("actid"));
//				System.out.println(actid);

				vactVO.setActid(actid);
			}

			// 活動開始日期
			else if (partName.equals("act_start_date")) {
				String actStart = req.getParameter("act_start_date").trim();
								
				try {
					if(actStart == null || actStart.length() == 0) {
						errorMsgs.put("actstart", "請選擇活動開始日期");
					} else {
						actstart = new java.sql.Date(sdf.parse(actStart).getTime());
						System.out.println("actstart=" + actstart);
						vactVO.setActstart(actstart);
						
						if(actstart.getTime() < rtlstart.getTime()) {
							errorMsgs.put("actstart", "活動開始日期不得早於租借日期");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.put("actstart", "請先選擇售票日期");
				}
			}

			// 活動結束日期
			else if (partName.equals("act_end_date")) {
				String actEnd = req.getParameter("act_end_date").trim();
				try {
					if(actEnd == null || actEnd.length() == 0) {
						errorMsgs.put("actend", "請選擇活動結束日期");

					} else {
						actend = new java.sql.Date(sdf.parse(actEnd).getTime());
//						System.out.println("actend=" + actend);
						vactVO.setActend(actend);

						if(actend.getTime() > rtlend.getTime()) {
							errorMsgs.put("actend", "活動結束日期不得晚於租借日期");
						}
						
					}

				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.put("actend", "請先選擇售票日期");
				}
			}

			// 售票開始日期
			else if (partName.equals("tk_start_date")) {
				String tkStart = req.getParameter("tk_start_date");
				try {
					if(tkStart == null || tkStart.length() == 0) {
						errorMsgs.put("tkstart", "請選擇售票開始日期");

					} else{
						java.sql.Date tkstart = new java.sql.Date(sdf.parse(tkStart).getTime());
						
						Date day= new Date(System.currentTimeMillis());
						String dayStr = sdf.format(day);
						Date today = new Date(sdf.parse(dayStr).getTime());
						
						if(tkstart.getTime() < today.getTime()) {
							errorMsgs.put("tkstart", "售票開始日請勿早於申請日");
						}
						
//						System.out.println("tkstart=" + tkstart);
						vactVO.setTkstart(tkstart);
					}

				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.put("tkstart", "請選擇售票開始日期");
				}
			}

			// 售票結束日期
			else if (partName.equals("tk_end_date")) {
				String tkEnd = req.getParameter("tk_end_date");
				try {
					if(tkEnd == null || tkEnd.length() == 0) {
						errorMsgs.put("tkend", "請選擇售票結束日期");
					} else {
						java.sql.Date tkend = new java.sql.Date(sdf.parse(tkEnd).getTime());
//						System.out.println("tkend=" + tkend);
						vactVO.setTkend(tkend);
						
						if(tkend.getTime() > actend.getTime()) {
							errorMsgs.put("tkend", "售票結束日請勿晚於活動日期");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.put("tkend", "請先選擇活動日期");
				}
			}

			// 售票單價
			else if (partName.equals("tkprice")) {
				String tkprice = req.getParameter("tkprice");
				String priceReg = "^[0-9]*$";
//				System.out.println(tkprice);
				if (tkprice == null || tkprice.trim().length() == 0) {
					errorMsgs.put("price", "請輸入售票單價");
				} else if (!tkprice.trim().matches(priceReg)) {
					errorMsgs.put("price", "售票單價只能是數字");
				} else {
					Integer price = new Integer(tkprice);
					vactVO.setPrice(price);
				}

			}

			// 活動內容
			else if (partName.equals("activity_content")) {
				String actcnt = req.getParameter("activity_content");
//				System.out.println(actcnt);
				if (actcnt == null || actcnt.trim().length() == 0) {
					errorMsgs.put("actcnt", "請輸入活動內容");
				} else {
					vactVO.setActcnt(actcnt);
				}
			}

			// 活動圖片
			else if (partName.equals("pic")) {
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] img = new byte[in.available()];
					in.read(img);
					in.close();
					vactVO.setImg(img);
				}

				if (vactVO.getImg() == null) {
					errorMsgs.put("img", "請選擇圖片");
				}
			}
		}
		
		if(vactVO.getRtlstart() != null) {
			session.setAttribute("sitename", sitename);
			session.setAttribute("daycost", daycost);
			vactVO.setSiteid(siteid);
		}
		
		// ***再次驗證場地是否仍未預定***//
		if(checkIfOccupied(siteid, rtlstart, rtlend)) {
			errorMsgs.put("left", "所選時間已被預訂，請重新選擇");
			session.removeAttribute("sitename");
			session.removeAttribute("daycost");
			vactVO.setSiteid(null);
		}
		
		session.setAttribute("vactVO", vactVO);

		req.setAttribute("errorMsgs", errorMsgs);
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/site/siteApply.jsp");
			failureView.forward(req, res);
			return;
		}

		// *** 從session找廠商代號 ***//
		VendorVO venVO = ((VendorVO) session.getAttribute("vendorVO"));
		Integer vendorid = venVO.getVendorid();
		vactVO.setVendorid(vendorid);

		if ("info_For_Confirm".equals(action)) { // 來自siteApply.jsp的請求
			String url = "/site/siteApplyConfirm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
	
	public boolean checkIfOccupied(Integer siteid, Date rtlstart, Date rtlend) {
		
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		Integer startYear = Integer.parseInt(sdfYear.format(rtlstart));
		Integer startMonth = Integer.parseInt(sdfMonth.format(rtlstart));
		Integer endYear = Integer.parseInt(sdfYear.format(rtlend));
		Integer endMonth = Integer.parseInt(sdfMonth.format(rtlend));
		
		long rtlstartM = rtlstart.getTime();
		long rtlendM = rtlend.getTime();
		
		VendorActService vactSvc = new VendorActService();
		//若起訖租借日在同年月, 查詢一次即可
		if (startYear == endYear && startMonth == endMonth) {
			List<VendorActVO> startlist = vactSvc.findOccupied(siteid, startYear, startMonth);
			for(VendorActVO vactVO : startlist) {
				long start = vactVO.getRtlstart().getTime();
				long end = vactVO.getRtlend().getTime();
				
				if(rtlstartM >= start && rtlstartM <= end) {
					return true;
				}		
				if(rtlendM >= start && rtlendM <= end) {
					return true;
				}
			}
		}
		
		//若在不同年月, 查詢結束日的年月
		List<VendorActVO> endlist = vactSvc.findOccupied(siteid, endYear, endMonth);
		for(VendorActVO vactVO : endlist) {
			long start = vactVO.getRtlstart().getTime();
			long end = vactVO.getRtlend().getTime();
			
			if(rtlstartM >= start && rtlstartM <= end) {
				return true;
			}		
			if(rtlendM >= start && rtlendM <= end) {
				return true;
			}
		}			
		
		return false;
	}
}
