package com.markRecord.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.markRecord.model.MarkRecordService;
import com.markRecord.model.MarkRecordVO;
import com.markRecord.model.*;

public class MarkRecordServlet extends HttpServlet {

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
				String str = req.getParameter("serialNumber");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入流水編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/markRecord/selectMarkRecord.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer serialNumber = null;
				try {
					serialNumber = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/markRecord/selectMarkRecord.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MarkRecordService markRecordSvc = new MarkRecordService();
				MarkRecordVO markRecordVO = markRecordSvc.getOneMarkRecord(serialNumber);
				if (markRecordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/markRecord/selectMarkRecord.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MarkRecordVO", markRecordVO); // 資料庫取出的empVO物件,存入req
				String url = "/markRecord/listOneMarkRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/markRecord/selectMarkRecord.jsp");
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
				Integer serialNumber = new Integer(req.getParameter("serialNumber"));
				
				/***************************2.開始查詢資料****************************************/
				MarkRecordService markRecordSvc = new MarkRecordService();
				MarkRecordVO markRecordVO = markRecordSvc.getOneMarkRecord(serialNumber);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("MarkRecordVO", markRecordVO);         // 資料庫取出的empVO物件,存入req
				String url = "/markRecord/update_markRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/markRecord/update_markRecord_input.jsp");
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
				Integer serialNumber = new Integer(req.getParameter("serialNumber").trim());
				
		
				String number = req.getParameter("rnumber");
//				String taxidReg = "^\\d{8}$";
				Integer rnumber = null;

				
					if(number == null || number.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
//					} else if(!number.trim().matches(taxidReg)) {
//						errorMsgs.add("會員編號: 請輸入8位數字");
					} else {
						try{
							rnumber = new Integer((number).trim());
						} catch(NumberFormatException e) {
							errorMsgs.add("請輸入數字");
						}
					}
				
					String stockID = req.getParameter("rstockID");
//					String taxidReg = "^\\d{8}$";
					Integer rstockID = null;

					
						if(stockID == null || stockID.trim().length() == 0) {
							errorMsgs.add("商品編號: 請勿空白");
//						} else if(!number.trim().matches(taxidReg)) {
//							errorMsgs.add("會員編號: 請輸入8位數字");
						} else {
							try{
								rstockID = new Integer((stockID).trim());
							} catch(NumberFormatException e) {
								errorMsgs.add("請輸入數字");
							}
						}
				
						String vacID = req.getParameter("rvacID");
//						String taxidReg = "^\\d{8}$";
						Integer rvacID = null;

						
							if(vacID == null || vacID.trim().length() == 0) {
								errorMsgs.add("商品編號: 請勿空白");
//							} else if(!number.trim().matches(taxidReg)) {
//								errorMsgs.add("會員編號: 請輸入8位數字");
							} else {
								try{
									rvacID = new Integer((vacID).trim());
								} catch(NumberFormatException e) {
									errorMsgs.add("請輸入數字");
								}
							}

			
			

				

				MarkRecordVO markRecordVO = new MarkRecordVO();
				markRecordVO.setSerialNumber(serialNumber);
				markRecordVO.setNumber(rnumber);
				markRecordVO.setStockID(rstockID);
				markRecordVO.setVacID(rvacID);

			
			

				

			
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MarkRecordVO", markRecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/markRecord/update_markRecord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MarkRecordService markRecordSvc = new MarkRecordService();
				markRecordVO = markRecordSvc.updateMarkRecord(serialNumber, rnumber, rstockID, rvacID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MarkRecordVO", markRecordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/markRecord/listOneMarkRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/markRecord/update_markRecord_input.jsp");
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
				
//				Integer serialNumber = new Integer(req.getParameter("serialNumber").trim());
				
				
				String number = req.getParameter("rnumber");
//				String taxidReg = "^\\d{8}$";
				Integer rnumber = null;

				
					if(number == null || number.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
//					} else if(!number.trim().matches(taxidReg)) {
//						errorMsgs.add("會員編號: 請輸入8位數字");
					} else {
						try{
							rnumber = new Integer((number).trim());
						} catch(NumberFormatException e) {
							errorMsgs.add("請輸入數字");
						}
					}
				
					String stockID = req.getParameter("rstockID");
//					String taxidReg = "^\\d{8}$";
					Integer rstockID = null;

					
						if(stockID == null || stockID.trim().length() == 0) {
							errorMsgs.add("商品編號: 請勿空白");
//						} else if(!number.trim().matches(taxidReg)) {
//							errorMsgs.add("會員編號: 請輸入8位數字");
						} else {
							try{
								rstockID = new Integer((stockID).trim());
							} catch(NumberFormatException e) {
								errorMsgs.add("請輸入數字");
							}
						}
				
						String vacID = req.getParameter("rvacID");
//						String taxidReg = "^\\d{8}$";
						Integer rvacID = null;

						
							if(vacID == null || vacID.trim().length() == 0) {
								errorMsgs.add("商品編號: 請勿空白");
//							} else if(!number.trim().matches(taxidReg)) {
//								errorMsgs.add("會員編號: 請輸入8位數字");
							} else {
								try{
									rvacID = new Integer((vacID).trim());
								} catch(NumberFormatException e) {
									errorMsgs.add("請輸入數字");
								}
							}

			
			

				

				MarkRecordVO markRecordVO = new MarkRecordVO();
//				markRecordVO.setSerialNumber(serialNumber);
				markRecordVO.setNumber(rnumber);
				markRecordVO.setStockID(rstockID);
				markRecordVO.setVacID(rvacID);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MarkRecordVO", markRecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/markRecord/addMarkRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MarkRecordService markRecordSvc = new MarkRecordService();
				markRecordVO = markRecordSvc.addMarkRecord( rnumber, rstockID, rvacID);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/markRecord/listAllMarkRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/markRecord/addMarkRecord.jsp");
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
				Integer serialNumber = new Integer(req.getParameter("serialNumber"));
				
				/***************************2.開始刪除資料***************************************/
				MarkRecordService markRecordSvc = new MarkRecordService();
				markRecordSvc.deleteMarkRecord(serialNumber);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/markRecord/listAllMarkRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/markRecord/listAllMarkRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
