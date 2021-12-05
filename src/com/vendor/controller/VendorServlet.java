package com.vendor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.home.model.MailServiceDAO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

import redis.clients.jedis.Jedis;

public class VendorServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		VendorService venSvc = new VendorService();

		if ("insert".equals(action)) { // 來自vendorLogin.jsp的請求(註冊)

			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 接收請求參數, 錯誤格式處理 *********************/
				String company = req.getParameter("vcompany");
				String companyReg = "^[(\u4e00-\u9fa5)]{1,20}$";
				String vcompany = null;

				if (company == null || company.trim().length() == 0) {
					errorMsgs.put("company", "必填欄位");
				} else if (!company.trim().matches(companyReg)) {
					errorMsgs.put("company", "只能是中文, 長度20字以內");
				} else {
					vcompany = company;
				}

				String taxid = req.getParameter("vtaxid");
				String taxidReg = "^\\d{8}$";
				Integer vtaxid = null;

				if (taxid == null || taxid.trim().length() == 0) {
					errorMsgs.put("taxid", "必填欄位");
				} else if (!taxid.trim().matches(taxidReg)) {
					errorMsgs.put("taxid", "請輸入8位數字");
				} else {
					try {
						vtaxid = new Integer((taxid).trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("taxid", "請輸入8位數字");
					}
				}

				String password = req.getParameter("vpassword");
				String passwordReg = "^[(a-zA-Z0-9)]{8,10}$";
				String vpassword = null;

				if (password == null || password.trim().length() == 0) {
					errorMsgs.put("password", "必填欄位");
				} else if (!password.trim().matches(passwordReg)) {
					errorMsgs.put("password", "請填寫長度8-10位之英數字");
				} else {
					vpassword = password;
				}

			    String passwordConfirm = req.getParameter("vpasswordConfirm");

			    if (passwordConfirm == null || passwordConfirm.trim().length() == 0) {
			     errorMsgs.put("passwordConfirm", "必填欄位");
			    } else if (!password.equals(passwordConfirm)) {
			     errorMsgs.put("passwordConfirm", "兩次輸入的密碼不一致");
			    }
				
				
				String name = req.getParameter("vname");
				String nameReg = "^[\u4E00-\u9FA5]{2,20}$";
				String vname = null;

				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("name", "必填欄位");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.put("name", "請填寫中文姓名");
				} else {
					vname = name;
				}

				String email = req.getParameter("vemail").trim();
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				String vemail = null;
				
				if (email == null || email.length() == 0) {
					errorMsgs.put("email", "必填欄位");
				} else if (!email.matches(emailReg)) {
					errorMsgs.put("email", "請輸入正確email格式");
				} else {
					vemail = email;
					VendorVO vendorVO = venSvc.findByEmail(email);
					if(vendorVO != null){
						errorMsgs.put("email", "此email已被註冊");
					}
				}

				String tel = req.getParameter("vtel");
				String telReg = "^[0-9]{8,10}$";
				String vtel = null;

				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.put("tel", "必填欄位");
				} else if (!tel.trim().matches(telReg)) {
					errorMsgs.put("tel", "電話號碼至少8位數字");
				} else {
					vtel = tel;
				}

				String mobile = req.getParameter("vmobile");
				String mobileReg = "^[(0-9)]{10}$";
				String vmobile = null;

				if (mobile == null || mobile.trim().length() == 0) {
					mobile = null;
				} else if (!mobile.trim().matches(mobileReg)) {
					errorMsgs.put("mobile", "請輸入10位手機號碼");
				} else {
					vmobile = mobile;
				}

				// 地址
				String city = req.getParameter("city");
				String town = req.getParameter("town");

				String addr = req.getParameter("vaddr");
				String addrReg = "^[(\u4e00-\u9fa5)(0-9)]{0,}$";
				String vaddr = null;

				if (city == "" || town == "" || addr == null || addr.trim().length() == 0) {
					errorMsgs.put("addr", "請填寫完整地址");
				} else if (!addr.trim().matches(addrReg)) {
					errorMsgs.put("addr", "請輸入正確地址格式");
				} else {
					vaddr = city + " " + town + " " + addr;
				}
				
				req.setAttribute("city", city);
				req.setAttribute("town", town);
				req.setAttribute("addr", addr);
				
				String register = "register";
				req.setAttribute("register", register);
				
				VendorVO venVO = new VendorVO();
				venVO.setCompany(vcompany);
				venVO.setTaxid(vtaxid);
				venVO.setPassword(vpassword);
				venVO.setName(vname);
				venVO.setEmail(vemail);
				venVO.setTel(vtel);
				venVO.setMobile(vmobile);
				venVO.setAddr(vaddr);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venVO", venVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/login/vendorLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 新增資料 ***************************************/
				
				venVO = venSvc.addVendor(vcompany, vtaxid, vname, vemail, vpassword, vtel, vmobile, vaddr);
				Integer vendorid = venSvc.findByEmail(vemail).getVendorid();
				req.setAttribute("vendorid", vendorid);
				/*************************** 新增完成,準備轉交(Send the Success view) ***********/

				String url = "/login/vendorEmail.jsp";
				errorMsgs.put("success", "請點擊下方連結發送驗證信至您的信箱。");
				req.setAttribute("sendEmail", "true");

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
//				errorMsgs.put("company", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login/vendorLogin.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("vendorEmail".equals(action)) { //來自vendorEmail.jsp的請求(發送驗證信)
			
			Integer vendorid = new Integer(req.getParameter("vendorid"));
			String email = venSvc.findOneVendor(vendorid).getEmail();
			Map<String, String> msgs = new HashMap<>();
			req.setAttribute("msgs", msgs);
			req.setAttribute("vendorid", vendorid);

			//產生token
			String token = UUID.randomUUID().toString().replace("-", "");
			
			//放token進Redis, 設定過期時間
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.set("vendorEmail"+vendorid, token);
			jedis.expire("vendorEmail"+vendorid, 300);
			jedis.close();
			
			//發送驗證信
			MailServiceDAO mailSvc = new MailServiceDAO();
			Boolean ss = mailSvc.vendorRegisterEmail(vendorid, email, token);	
			
			//導回頁面
			if(ss) {
				msgs.put("msgs", "發送成功，請至信箱收取驗證信。");

			} else {
				msgs.put("msgs", "發送失敗，請重新發送。");
				req.setAttribute("sendEmail", "true");
			}
			
			RequestDispatcher nextView = req.getRequestDispatcher("/login/vendorEmail.jsp");
			nextView.forward(req, res);

		}
		
		if("tokenCheck".equals(action)) { //驗證信中的連結
			String vendorid = req.getParameter("vendorid");
			String receivedToken = req.getParameter("token");
			Map<String, String> msgs = new HashMap<>();
			req.setAttribute("msgs", msgs);
			req.setAttribute("sendEmail", "true");
			
			Jedis jedis = new Jedis("localhost", 6379);
			String token = jedis.get("vendorEmail"+vendorid);
			jedis.del("vendorEmail"+vendorid);
			jedis.close();

			if(token == null) {
				msgs.put("msgs", "驗證信已失效，請重新發送。");
				RequestDispatcher nextView = req.getRequestDispatcher("/login/vendorEmail.jsp");
				nextView.forward(req, res);
				return;
			} else if(receivedToken.equals(token)){
				venSvc.updateStatus(new Integer(vendorid), "正常");
				msgs.put("msgs", "帳戶已啟用，請登入。");
				req.setAttribute("sendEmail", "false");
				RequestDispatcher nextView = req.getRequestDispatcher("/login/vendorEmail.jsp");
				nextView.forward(req, res);
				return;
			} else {
				msgs.put("msgs", "驗證失敗，請重新發送");
				RequestDispatcher nextView = req.getRequestDispatcher("/login/vendorEmail.jsp");
				nextView.forward(req, res);
			}
			
		}
		

		if ("getAll".equals(action)) { // 來自vendorBackend.jsp
			VendorService vendorSvc = new VendorService();
			List<VendorVO> venlist = vendorSvc.getAll();
			req.setAttribute("venlist", venlist);

			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
			successView.forward(req, res);

		}

		if ("find_By_Keyword".equals(action)) { // 來自vendorBackend.jsp的請求

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgsKeyWord", errorMsgs);

			String vkeyword = req.getParameter("vendorname");
			String keywordReg = "^[(\u4e00-\u9fa5)]{1,20}$";

			if (vkeyword == null || vkeyword.trim().length() == 0) {
				errorMsgs.add("請輸入關鍵字");
			} else if (!vkeyword.trim().matches(keywordReg)) {
				errorMsgs.add("請輸入中文關鍵字");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("vkeyword", vkeyword);
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
				failureView.forward(req, res);
				return;
			}

			List<VendorVO> venlist = venSvc.findByKeyword(vkeyword.trim());

			if (venlist.size() == 0) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("venlist", venlist);

			RequestDispatcher successView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
			successView.forward(req, res);

		}
		
		
		if ("update".equals(action)) { // 來自vendorInfo.jsp的請求
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 接收請求參數, 錯誤格式處理 *********************/
				// 公司名稱
				String company = req.getParameter("vcompany");
				String companyReg = "^[(\u4e00-\u9fa5)]{1,20}$";
				String vcompany = null;

				if (company == null || company.trim().length() == 0) {
					errorMsgs.put("company", "必填欄位");
				} else if (!company.trim().matches(companyReg)) {
					errorMsgs.put("company", "須為中文名稱，長度20字以內");
				} else {
					vcompany = company;
				}

				// 統一編號
				String taxid = req.getParameter("vtaxid");
				String taxidReg = "^\\d{8}$";
				Integer vtaxid = null;

				if (taxid == null || taxid.trim().length() == 0) {
					errorMsgs.put("taxid", "必填欄位");
				} else if (!taxid.trim().matches(taxidReg)) {
					errorMsgs.put("taxid", "請輸入8碼統一編號");
				} else {
					try {
						vtaxid = new Integer((taxid).trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("taxid", "請輸入8碼統一編號");
					}
				}

				// 密碼欄位
				String password = req.getParameter("vpassword");
				String passwordReg = "^[(a-zA-Z0-9)]{8,10}$";
				String vpassword = null;
				
				//(從session找舊密碼)
				HttpSession session = req.getSession();
				VendorVO vendorVO = (VendorVO) session.getAttribute("vendorVO");
				Integer vendorid = new Integer(vendorVO.getVendorid());
				String oldPassword = vendorVO.getPassword();

				//密碼驗證
				if (password == null || password.trim().length() == 0) {
					errorMsgs.put("password", "請輸入密碼以完成資料更新");
				} else if (!password.trim().equals(oldPassword)) {
					errorMsgs.put("password", "密碼錯誤");
				} else {
					vpassword = password;
				}

				// 聯絡人
				String name = req.getParameter("vname");
				String nameReg = "^[\u4E00-\u9FA5]{2,20}$";
				String vname = null;

				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("name", "必填欄位");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.put("name", "請輸入中文姓名");
				} else {
					vname = name;
				}

				// EMAIL
				String email = req.getParameter("vemail");
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				String vemail = null;

				if (email == null || email.trim().length() == 0) {
					errorMsgs.put("email", "必填欄位");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgs.put("email", "請輸入正確email格式");
				} else {
					vemail = email;
				}

				// 連絡電話
				String tel = req.getParameter("vtel");
				String telReg = "^[0-9]{8,10}$";
				String vtel = null;

				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.put("tel", "必填欄位");
				} else if (!tel.trim().matches(telReg)) {
					errorMsgs.put("tel", "電話號碼至少8位數字");
				} else {
					vtel = tel;
				}

				// 手機
				String mobile = req.getParameter("vmobile");
				String mobileReg = "^[(0-9)]{10}$";
				String vmobile = null;

				if (mobile == null || mobile.trim().length() == 0) {
					mobile = null;
				} else if (!mobile.trim().matches(mobileReg)) {
					errorMsgs.put("mobile", "請輸入正確手機號碼格式");
				} else {
					vmobile = mobile;
				}

				// 地址
				String city = req.getParameter("city");
				String town = req.getParameter("town");
				String addr = req.getParameter("vaddr");

				String addrReg = "^[(\u4e00-\u9fa5)(0-9)]{0,}$";
				String vaddr = null;

				if (city == " " || town == " " || addr == null || addr.trim().length() == 0) {
					errorMsgs.put("addr", "請輸入完整地址");
				} else if (!addr.trim().matches(addrReg)) {
					errorMsgs.put("addr", "請輸入正確地址格式");
				} else {
					vaddr = city + " " + town + " " + addr;
					// 地址秀到畫面上
					String[] addrs = vaddr.split(" ");
					req.setAttribute("addrs", addrs);
				}
				
				VendorVO venVO = new VendorVO();
				venVO.setVendorid(vendorid);
				venVO.setCompany(vcompany);
				venVO.setTaxid(vtaxid);
				venVO.setPassword(vpassword);
				venVO.setName(vname);
				venVO.setEmail(vemail);
				venVO.setTel(vtel);
				venVO.setMobile(vmobile);
				venVO.setAddr(vaddr);

				req.setAttribute("vendorLocate", "info");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venVO", venVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorInfo.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 更新資料 ***************************************/
				vendorVO = venSvc.updateVendor(vendorid, vcompany, vtaxid, vname, vemail, vpassword, vtel, vmobile, vaddr);
				session.setAttribute("vendorVO", vendorVO);
				req.setAttribute("update","success");
				
				/*************************** 更新完成,準備轉交(Send the Success view) ***********/

				String url = "/vendor/vendorInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_Password".equals(action)) {// 來自vendorInfo.jsp的請求
			
			//抓取session裡vendorVO的登入密碼
			HttpSession session = req.getSession();
			VendorVO vendorVO = (VendorVO) session.getAttribute("vendorVO");
			String password = vendorVO.getPassword();
			System.out.println("sql密碼:"+password);
			
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 舊密碼欄位
			String passwordOld = req.getParameter("vpassword");
			System.out.println("舊密碼:"+passwordOld);
			if (passwordOld == null || passwordOld.trim().length() == 0) {
				errorMsgs.put("passwordOld", "請輸入舊密碼");
			} else if (!passwordOld.trim().equals(password)) {
				errorMsgs.put("passwordOld", "密碼錯誤");
			}

			// 新密碼欄位
			String passwordNew = req.getParameter("passwordNew");
			String passwordReg = "^[(a-zA-Z0-9)]{8,10}$";

			if (passwordNew == null || passwordNew.trim().length() == 0) {
				errorMsgs.put("passwordNew", "請輸入新密碼");
			} else if (!passwordNew.trim().matches(passwordReg)) {
				errorMsgs.put("passwordNew", "請填寫長度8-10位之英數字");
			}

			// 新密碼確認欄位
			String passwordConfirm = req.getParameter("passwordConfirm");
			if (passwordConfirm == null || passwordConfirm.trim().length() == 0) {
				errorMsgs.put("passwordConfirm", "請再次輸入新密碼");
			} else if (!passwordConfirm.equals(passwordNew)) {
				errorMsgs.put("passwordConfirm", "確認密碼需與新密碼一致");
			}

			String url = "/vendor/vendorInfo.jsp";
			req.setAttribute("vendorLocate", "password");

			// 有錯誤訊息, 直接導回原頁面
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}

			// 進行密碼更新
			vendorVO.setPassword(passwordConfirm);
			venSvc.updateVendor(vendorVO);
			// 導回原頁面
			req.setAttribute("update","success");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自vendorInfo.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();

			try {
				VendorVO venderVO = (VendorVO) session.getAttribute("vendorVO");

				String addr = venderVO.getAddr();
				String[] addrs = addr.split(" ");

				req.setAttribute("addrs", addrs);

				String url = "/vendor/vendorInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/home/home.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { //來自vendorBackend.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*********************** 接收請求參數, 錯誤格式處理 *********************/
				String str = req.getParameter("vendorid");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");		
					failureView.forward(req, res);
					return;
				}
				
				Integer vendorid = null;
				
				try {
					vendorid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廠商編號不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*********************** 開始查詢資料 *********************/
				VendorVO venVO = venSvc.findOneVendor(vendorid);
				if(venVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
					failureView.forward(req, res);
					return;
				}		
				
				/*********************** 查詢完成, 轉交資料 *********************/
				
				req.setAttribute("venVO", venVO);
				String url = "/vendor/vendorBackend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*********************** 其他可能錯誤處理 *********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorBackend.jsp");
				failureView.forward(req, res);
			}	
		}

//		if("delete".equals(action)) { //來自vendorAll.jsp & vendorOne.jsp的請求
//		
//		List<String> errorMsgs = new LinkedList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		try {
//			Integer vendorid = new Integer(req.getParameter("vendorid"));
//			
//			VendorService venSvc = new VendorService();
//			System.out.println("hello");
//			venSvc.deleteVendor(vendorid);
//			
//			String url = "/vendor/vendorAll.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			
//		} catch(Exception e) {
//			errorMsgs.add("刪除資料失敗");
//			RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendorAll.jsp");
//			failureView.forward(req, res);
//		} 
//	}

	}
}