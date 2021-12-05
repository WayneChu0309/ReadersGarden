package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;

public class NewArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自newArticleHome.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("AID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/newArticle/newArticleHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer AID = null;
				try {
					AID = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/newArticle/newArticleHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService artSvc = new ArticleService();
				Article art = artSvc.getOneArticle(AID);
				if (art == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/newArticle/newArticleHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("art", art); // 資料庫取出的empVO物件,存入req
				String url = "/article/articleListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/newArticle/newArticleHome.jsp");
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
				Integer AID = new Integer(req.getParameter("AID"));
				
				/***************************2.開始查詢資料****************************************/
				ArticleService artSvc = new ArticleService();
				Article art = artSvc.getOneArticle(AID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("art", art);         // 資料庫取出的empVO物件,存入req
				String url = "/newArticle/articleUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/newArticle/articleListAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("1");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer AID = new Integer(req.getParameter("AID"));
//				Integer ACID = new Integer(req.getParameter("ACID"));			
//				Integer ACCTID = new Integer(req.getParameter("ACCTID"));
				
				
				System.out.println("2");
				
				
				String ANAME = req.getParameter("ANAME");
				String anameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ANAME == null || ANAME.trim().length() == 0) {
					errorMsgs.add("文章名稱: 請勿空白");
				} else if(!ANAME.trim().matches(anameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				System.out.println("3");

				String ADESCRIPT = req.getParameter("ADESCRIPT");
				String ADESCRIPTReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ADESCRIPT == null || ADESCRIPT.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} else if(!ADESCRIPT.trim().matches(ADESCRIPTReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

//				java.sql.Date APD = null;
//				try {
//					APD = java.sql.Date.valueOf(req.getParameter("APD").trim());
//				} catch (IllegalArgumentException e) {
//					APD=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

//				Integer deptno = new Integer(req.getParameter("deptno").trim());
				Integer ACID = new Integer(req.getParameter("ACID"));
				Integer ACCTID = new Integer(req.getParameter("ACCTID"));
				Integer AID = new Integer(req.getParameter("AID"));

				Article art = new Article();
				art.setANAME(ANAME);
				art.setADESCRIPT(ADESCRIPT);
				art.setACID(ACID);
				art.setACCTID(ACCTID);
				art.setAID(AID);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("art", art); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/newArticle/articleUpdate.jsp");
					System.out.println("6");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArticleService artSvc = new ArticleService();
				art = artSvc.updateArticle(AID, ACID, ACCTID, ANAME, ADESCRIPT);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("art", art); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/newArticle/articleListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/newArticle/articleUpdate.jsp");
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
				
				Integer ACID = null;
				try {
					ACID = new Integer(req.getParameter("ACID").trim());
				} catch (NumberFormatException e) {
					ACID = 0;
					errorMsgs.add("文章類別編號請填數字.");
				}
				
				Integer ACCTID = null;
				try {
					ACCTID = new Integer(req.getParameter("ACCTID").trim());
				} catch (NumberFormatException e) {
					ACCTID = 0;
					errorMsgs.add("發布者ID請填數字.");
				}	
				
				String ANAME = req.getParameter("ANAME");
				String anameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ANAME == null || ANAME.trim().length() == 0) {
					errorMsgs.add("文章名稱: 請勿空白");
				} else if(!ANAME.trim().matches(anameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				

				String ADESCRIPT = req.getParameter("ADESCRIPT");
				String adescriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ANAME == null || ANAME.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} else if(!ANAME.trim().matches(anameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
//				java.sql.Date APD = null;
//				try {
//					APD = java.sql.Date.valueOf(req.getParameter("APD").trim());
//				} catch (IllegalArgumentException e) {
//					APD=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}


				Article art = new Article();

				art.setACID(ACID);
				art.setACCTID(ACCTID);
				art.setANAME(ANAME);
				art.setADESCRIPT(ADESCRIPT);
//				art.setAPD(APD);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("art", art); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/newArticle/articleAdd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleService artSvc = new ArticleService();
				art = artSvc.addArticle(ACID, ACCTID, ANAME, ADESCRIPT);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/newArticle/articleListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/newArticle/articleAdd.jsp");
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
				Integer AID = new Integer(req.getParameter("AID"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService artSvc = new ArticleService();
				artSvc.deleteArticle(AID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/newArticle/articleListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/newArticle/articleListAll.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

