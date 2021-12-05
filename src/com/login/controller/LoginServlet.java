//<<<<<<< HEAD
////package com.login.controller;
////
////
////import java.io.IOException;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////
////import com.member.model.*; //引入連線資料庫的java檔案包
////
////@WebServlet("/LoginServlet")
////public class LoginServlet extends HttpServlet {
////	private static final long serialVersionUID = 1L;
////
////   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
////   //【實際上應至資料庫搜尋比對】
//////  protected boolean allowUser(String email, String password) {
//////    if ("tomcat".equals(email) && "tomcat".equals(password))
//////      return true;
//////    else
//////      return false;
//////  }
//////  
////  public void doPost(HttpServletRequest req, HttpServletResponse res)
////                                throws ServletException, IOException {
////    req.setCharacterEncoding("UTF-8");
////    res.setContentType("text/html; charset=UTF-8");
//////    PrintWriter out = res.getWriter();
////
//////    // 【取得使用者 帳號(account) 密碼(password)】
//////    String account = req.getParameter("account");
//////    String password = req.getParameter("password");
////
////	  req.setCharacterEncoding("UTF-8");
////		res.setContentType("text/html;charset=utf-8");
////		
////		String email = req.getParameter("email");
////		String password = req.getParameter("password");
////		String psw =new MemberDAO().findByEmail(email);
////		
////		if(psw ==null){
////			req.setAttribute("msg", "沒有這個使用者！");
////			req.getRequestDispatcher("/login/loginFail.jsp").forward(req, res);
////			return;
////
////		}
////		if(psw!=null&&!psw.equals(password)){
////			req.setAttribute("msg", "密碼錯誤請重新輸入！");
////			req.getRequestDispatcher("/login/loginFail.jsp").forward(req, res);
////			return;	
////		}
////		if(psw.equals(password)){
////			req.setAttribute("msg", "歡迎訪問");
////			req.getRequestDispatcher("/login/loginSuccess.jsp").forward(req, res);
////		}
////	}
////}
//=======
//package com.login.controller;
//
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.member.model.*; //引入連線資料庫的java檔案包
//
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
//   //【實際上應至資料庫搜尋比對】
////  protected boolean allowUser(String email, String password) {
////    if ("tomcat".equals(email) && "tomcat".equals(password))
////      return true;
////    else
////      return false;
////  }
////  
//  public void doPost(HttpServletRequest req, HttpServletResponse res)
//                                throws ServletException, IOException {
//    req.setCharacterEncoding("UTF-8");
//    res.setContentType("text/html; charset=UTF-8");
////    PrintWriter out = res.getWriter();
//
////    // 【取得使用者 帳號(account) 密碼(password)】
////    String account = req.getParameter("account");
////    String password = req.getParameter("password");
//
//	  req.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html;charset=utf-8");
//		
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		MemberVO psw =new MemberDAO().findByEmail(email);
//		
//		if(psw ==null){
//			req.setAttribute("msg", "沒有這個使用者！");
//			req.getRequestDispatcher("/login/loginFail.jsp").forward(req, res);
//			return;
//
//		}
//		if(psw!=null&&!psw.equals(password)){
//			req.setAttribute("msg", "密碼錯誤請重新輸入！");
//			req.getRequestDispatcher("/login/loginFail.jsp").forward(req, res);
//			return;	
//		}
//		if(psw.equals(password)){
//			req.setAttribute("msg", "歡迎訪問");
//			req.getRequestDispatcher("/login/loginSuccess.jsp").forward(req, res);
//		}
//	}
//}
//>>>>>>> origin/leo
