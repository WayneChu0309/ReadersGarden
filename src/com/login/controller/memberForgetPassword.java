package com.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.model.MailServiceDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

import connectionpool.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class memberForgetPassword extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		doPost(req, res);
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Map<String, String> errorMsgs = new HashMap<>();
		req.setAttribute("errorMsgs", errorMsgs);
		Boolean ifSuccess = false;
		
		if("forgetPassword".equals(action)) { //來自vendorForgetPassword.jsp (產生token寄驗證信)

			//抓取使用者輸入email查詢
			String emailInput = req.getParameter("email");
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.findEmail(emailInput);
			if(memVO == null) {
				errorMsgs.put("email", "此用戶不存在");
				RequestDispatcher failureView = req.getRequestDispatcher("/login/memberForgetPassword.jsp");
				failureView.forward(req, res);
				
			} else {
				//若用戶存在, 寄發重設密碼郵件
				String email = memVO.getEmail();
				if(emailInput.equals(email)) {
					MailServiceDAO mailSvc = new MailServiceDAO();
					Integer number = memVO.getNumber();

					//產生token
					String token = UUID.randomUUID().toString().replace("-", "");	
					
					//存入Redis設定存活時間
					JedisPool pool = JedisUtil.getJedisPool();
					Jedis jedis = pool.getResource();
					jedis.del("member"+number);
					jedis.set("member"+number, token);
					jedis.expire("member"+number, 180); //秒數
					jedis.close();
					
					ifSuccess = mailSvc.memberForgetPassword(number, email, token);
				}
			}
			
			if(ifSuccess) {
				errorMsgs.put("email","發送成功，請至信箱收取驗證信。");
				RequestDispatcher successView = req.getRequestDispatcher("/login/memberSuccess.jsp");
				successView.forward(req, res);
			} else {
				errorMsgs.put("email","發送失敗，請重新發送。");
				RequestDispatcher failureView = req.getRequestDispatcher("/login/memberForgetPassword.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("resetAvaliable".equals(action)) { //email連結驗證token
			
			//抓取連結裡的資料
			String number = req.getParameter("number");
			String Receivedtoken = req.getParameter("token");
			req.setAttribute("number", number);
			
			//去Redis查暫存的token
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			String token = jedis.get("member"+number);
			jedis.close();
			
			//驗證token
			if(token == null) {
				errorMsgs.put("email", "驗證信已失效, 請重新寄送。");
				RequestDispatcher failureView = req.getRequestDispatcher("/login/memberForgetPassword.jsp");
				failureView.forward(req, res);
				return;
				
			} else if(Receivedtoken.equals(token)) { //驗證成功進入重設密碼頁面
				RequestDispatcher successView = req.getRequestDispatcher("/login/memberResetPassword.jsp");
				successView.forward(req, res);
			} else {
				errorMsgs.put("email", "驗證有誤, 請重新申請。");
				RequestDispatcher failureView = req.getRequestDispatcher("/login/memberForgetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("reset".equals(action)) { //來自vendorResetPassword.jsp (重設密碼)
			
			//新密碼欄位驗證
			String password = req.getParameter("password");
			String passwordReg = "^[(a-zA-Z0-9)]{8,10}$";
			
			if(password == null || password.length() == 0) {
				errorMsgs.put("password", "請輸入新密碼");
			} else if (!password.matches(passwordReg)){
				errorMsgs.put("password", "請填寫長度8-10位之英數字");
			}
			
			//確認密碼欄位驗證
			String passwordConfirm = req.getParameter("passwordConfirm");
			if(passwordConfirm == null || passwordConfirm.length() == 0) {
				errorMsgs.put("passwordConfirm", "請確認新密碼");
			} else if (!passwordConfirm.equals(password)) {
				errorMsgs.put("passwordConfirm", "兩次輸入密碼不一致");
			}
			
			//錯誤訊息回傳原頁面
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/login/memberResetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//驗證通過, 開始更新密碼
			MemberService memSvc = new MemberService();
			Integer number = new Integer(req.getParameter("number"));
			MemberVO memberVO = memSvc.getOneMember(number);
			memberVO.setPassword(passwordConfirm);
			memSvc.updateMember(memberVO);
			
			//刪除Redis存的token
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			jedis.del("member"+number);
			
			
			//更新完成, 轉移頁面
			errorMsgs.put("email", "重設密碼成功，請用新密碼登入");
			RequestDispatcher successView = req.getRequestDispatcher("/login/memberSuccess.jsp");
			successView.forward(req, res);
			
		}
	}
	
	
}
