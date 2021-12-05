package com.vendor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

import redis.clients.jedis.Jedis;

public class VendorLogin extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		
		VendorService venSvc = new VendorService();
		VendorVO vendorVO = null;
		Map<String, String> errorLogin = new HashMap<>();
		HttpSession session = req.getSession();
		
		//用email查詢vendorVO
		String loginEmail = req.getParameter("email").trim();
		if(loginEmail == null || loginEmail.length() == 0 ) {
			errorLogin.put("email", "請輸入email");
		} else {
			vendorVO = venSvc.findByEmail(loginEmail);	
			if(vendorVO == null) {
				errorLogin.put("email", "此用戶不存在");
			} else if(vendorVO.getStatus().equals("停權")) {
				errorLogin.put("email", "此用戶狀態異常，如欲恢復請聯繫客服。");
			}
		}
		
		//密碼驗證
		String loginPassword = req.getParameter("password");

		if(loginPassword == null || loginPassword.length() == 0) {
			errorLogin.put("password", "請輸入密碼");
		} else {
			if(vendorVO != null && !vendorVO.getStatus().equals("停權")) {
				String password = vendorVO.getPassword();
				if(!loginPassword.equals(password)) {
					errorLogin.put("password", "密碼錯誤");
				}
			} 	
		}
		
		req.setAttribute("errorLogin", errorLogin);
		String url = "/login/vendorLogin.jsp";
		
		if(!errorLogin.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
			return;
		}
		
		//帳號密碼正確
		session.setAttribute("vendorVO", vendorVO);
		session.removeAttribute("member");
		
		//自動登入功能
		String autoLogin = req.getParameter("autoLogin");
		if("true".equals(autoLogin)) {
			
			//產生token
			String token = UUID.randomUUID().toString().replace("-", "");
			
			//存入Redis
			Jedis jedis = new Jedis("localhost", 6379);
			System.out.println("jedis"+jedis);
			String vendorid = vendorVO.getVendorid().toString();
			System.out.println(vendorid);
			jedis.set(token, vendorid);
			System.out.println("token"+token);
			jedis.expire(token, 3600*24*7);
			jedis.close();
			//存入context跟cookie
//			ServletContext context = session.getServletContext();
//			context.setAttribute(token, vendorVO);
			Cookie cookie = new Cookie("autoLogin", token);
			//設置有效期限為一周
			cookie.setMaxAge(3600*24*7);
			//設置cookie路徑
			cookie.setPath(session.getServletContext().getContextPath());
			res.addCookie(cookie);
		}
		
		//從session找是否有導向頁面
		try {
			String location = (String) session.getAttribute("location");

			if(location != null) {
				res.sendRedirect(location);
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//沒有就導回首頁
		RequestDispatcher successView = req.getRequestDispatcher("/home/home.jsp");
		successView.forward(req, res);
	}
}
