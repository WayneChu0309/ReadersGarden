package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member.model.*;

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				
				String str = req.getParameter("number");
			
				
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/memberBackend.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer number = null;
				try {
					number = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/memberBackend.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
               MemberService memberSvc = new MemberService();
				
				MemberVO memberVO = memberSvc.getOneMember(number);
				
				
				 
				
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
					String backStatus = memberVO.getStatus();
					
				if (backStatus.equals("管理員")) {
					errorMsgs.add("此為管理員帳號");
				}
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/memberBackend.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:");
				RequestDispatcher failureView = req.getRequestDispatcher("/member/memberBackend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer number = new Integer(req.getParameter("number"));
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(number);
				/***************************2.開始查詢資料****************************************/
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的empVO物件,存入req
				String url = "/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer number = new Integer(req.getParameter("number").trim());
				
				String name = req.getParameter("mname");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String mname = null;
				
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }else {
					mname = name;
	            }
				
				String email = req.getParameter("memail");
				String emailReg =  "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				String memail = null;
				
				if(email == null || email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("Email: 請輸入正確email格式");
				} else {
					memail = email;
				}
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String phoneNumber = null;
				try {
					phoneNumber = new String(req.getParameter("phoneNumber").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("電話請填數字.");
				}

			
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String ID = req.getParameter("ID").trim();
				if (ID == null || ID.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}
				

				
				String password = req.getParameter("password").trim();
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("會員狀態請勿空白");
				}


				

				MemberVO memberVO = new MemberVO();
				memberVO.setNumber(number);
				memberVO.setEmail(memail);
				memberVO.setName(mname);
				memberVO.setBirthday(birthday);
				memberVO.setPhoneNumber(phoneNumber);
				memberVO.setAddress(address);
				memberVO.setID(ID);
				memberVO.setPassword(password);
				memberVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(number, memail, mname, birthday, phoneNumber,address, ID, password, status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
         //    Integer number = new Integer(req.getParameter("number").trim());//
				
				String name = req.getParameter("mname");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String mname = "";
				
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            } else {
					mname = name;
				}
				
				String email = req.getParameter("memail");
				String emailReg =  "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				String memail = "";
				
				if(email == null || email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("Email: 請輸入正確email格式");
				} else {
					memail = email;
				}
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String phoneNumber = req.getParameter("phoneNumber").trim();
				
				if (phoneNumber == null || phoneNumber.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
			

				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String ID = req.getParameter("ID").trim();
				if (ID == null || ID.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}
				

				
				String password = req.getParameter("password").trim();
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("會員狀態請勿空白");
				}
				
				
				MemberVO memberVO = new MemberVO();
			//	membertNumber(number);
				memberVO.setEmail(memail);
				memberVO.setName(mname);
				memberVO.setBirthday(birthday);
				memberVO.setPhoneNumber(phoneNumber);
				memberVO.setAddress(address);
				memberVO.setID(ID);
				memberVO.setPassword(password);
				memberVO.setStatus(status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(memail, mname, birthday, phoneNumber,address, ID, password, status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer number = new Integer(req.getParameter("number"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(number);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
