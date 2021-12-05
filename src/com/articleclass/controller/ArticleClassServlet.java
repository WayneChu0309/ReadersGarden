//package com.articleclass.controller;
//
//import java.io.*;
//import java.util.*;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import com.articleclass.model.*;
//
//public class ArticleClassServlet extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_article.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("CLASSNAME");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入文章類別名稱");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/newArticleHome.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String CLASSNAME = null;
//				try {
//					CLASSNAME = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("文章類別名稱不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/newArticleHome.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				Article_ClassService artclassSvc = new Article_ClassService();
//				Article_Class artC = artclassSvc.getOneArticle(CLASSNAME);
//				if (artC == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_article.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("artC", artC); // 資料庫取出的empVO物件,存入req
//				String url = "/article/articleListOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_article.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String CLASSNAME = new String(req.getParameter("CLASSNAME"));
//				
//				/***************************2.開始查詢資料****************************************/
//				Article_ClassService artclassSvc = new Article_ClassService();
//				Article_Class art = artclassSvc.getOneArticle(CLASSNAME);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("art", art);         // 資料庫取出的empVO物件,存入req
//				String url = "/article/articleUpdate.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/article/articleListAll.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			System.out.println("1");
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			
//				Integer ACID = new Integer(req.getParameter("ACID"));
//				String CLASSNAME = new String(req.getParameter("CLASSNAME"));
//
//
//				Article_Class artclass = new Article_Class();
//				artclass.setACID(ACID);
//				artclass.setCLASSNAME(CLASSNAME);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("artclass", artclass); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/article/articleUpdate.jsp");
//					System.out.println("6");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				Article_ClassService artclassSvc = new Article_ClassService();
//				artclass = artclassSvc.updateArticle(CLASSNAME);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("artclass", artclass); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/article/articleListOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/article/articleUpdate.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				
//				Integer ACID = null;
//				try {
//					ACID = new Integer(req.getParameter("ACID").trim());
//				} catch (NumberFormatException e) {
//					ACID = 0;
//					errorMsgs.add("文章類別編號請填數字.");
//				}
//				
//
//				String CLASSNAME = req.getParameter("CLASSNAME");
//				String classnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				
//
//				Article_Class artclass = new Article_Class();
//
//				artclass.setCLASSNAME(CLASSNAME);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("artclass", artclass); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/article/articleAdd.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				Article_ClassService artclassSvc = new Article_ClassService();
//				artclass = artclassSvc.addArticle(CLASSNAME);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/newArticle/newArticleHome.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/article/articleAdd.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer ACID = new Integer(req.getParameter("CLASSNAME"));
//				
//				/***************************2.開始刪除資料***************************************/
//				Article_ClassService artclassSvc = new Article_ClassService();
//				artclassSvc.deleteArticle(ACID);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/newArticle/newArticleHome.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/newArticle/newArticleHome.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
//}