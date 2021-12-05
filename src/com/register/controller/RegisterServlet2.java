package com.register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member.model.*;

@WebServlet("/RegisterServlet2")
public class RegisterServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet2() {
        super();
    }

public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");  



	

	   
		
			
			if ("register".equals(action)) { // 來自login.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	         //    Integer number = new Integer(req.getParameter("number").trim());//
					
					String name = req.getParameter("mname");
					String nameReg = "^[(\u4e00-\u9fa5)]{1,20}$";
					String mname = "";
					
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("請填寫姓名");
					} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("姓名只能是中文, 長度20個字以內");
		            } else {
						mname = name;
					}
					
					String email = req.getParameter("memail");
					String emailReg =  "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					String memail = "";
					MemberVO existEmail =new MemberDAO().findByEmail(email);
					
					
					if(email == null || email.trim().length() == 0) {
						errorMsgs.add("請填寫Email");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("請填寫正確Email格式");
					}else if(existEmail != null){
						errorMsgs.add("已有相同信箱註冊");
						
					} else {
						memail = email;
					}
					

					String phoneNumber = req.getParameter("mphoneNumber");
					String phoneNumberReg ="^09[0-9]{8}$";
					String mphoneNumber = "";
					
					
					if (phoneNumber == null || phoneNumber.trim().length() == 0) {
						errorMsgs.add("請填寫手機號碼");
					} else if (!phoneNumber.trim().matches(phoneNumberReg)) {
						errorMsgs.add("請填寫正確手機格式");
					} else {
						mphoneNumber = phoneNumber;
					}
					
					
				

					
					String address = req.getParameter("maddress");
					String addressReg = "^[(\u4e00-\u9fa5)(0-9)]{0,}$";
					String maddress = "";
					
					if (address == null || address.trim().length() == 0) {
						errorMsgs.add("請填寫地址");
					} else if(!address.trim().matches(addressReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("地址只能是中文及數字");
		            } else {
						maddress = address;
					}
					
					String ID = req.getParameter("mID");
					String IDReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
					String mID = "";
					
					if (ID == null || ID.trim().length() == 0) {
						errorMsgs.add("請填寫身份證字號");
					} else if(!ID.trim().matches(IDReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("請輸入正確身份證格式");
		            } else {
						mID = ID;
					}
					

					
					String password = req.getParameter("mpassword");
					String passwordReg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
					String mpassword = "";
					String password2 = req.getParameter("mpassword2");
					
					if (ID == null || password.trim().length() == 0) {
						errorMsgs.add("請填寫密碼");
					} else if(!password.trim().matches(passwordReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("密碼只能是英文及數字,且6到16個字");
					}else if(!(password.equals(password2))){
						errorMsgs.add("請確認密碼相同");
		            } else {
						mpassword = password;
					}
					
//					String status = req.getParameter("status").trim();
//					if (status == null || status.trim().length() == 0) {
//						errorMsgs.add("會員狀態請勿空白");
//					}
//					
					
					MemberVO memberVO = new MemberVO();
				
					memberVO.setEmail(memail);
					memberVO.setName(mname);
					
					memberVO.setPhoneNumber(mphoneNumber);
					memberVO.setAddress(maddress);
					memberVO.setID(mID);
					memberVO.setPassword(mpassword);
				
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/login/login.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.register(memail, mname,  mphoneNumber,maddress, mID, mpassword);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/home/home.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);
					res.setContentType("text/html;charset=UTF-8");
					PrintWriter pWriter=res.getWriter();
					System.out.println("True");
					pWriter.print("True");
					/***************************其他可能的錯誤處理**********************************/
				}catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/login/login.jsp");
					failureView.forward(req, res);
				}
			}}}
	
