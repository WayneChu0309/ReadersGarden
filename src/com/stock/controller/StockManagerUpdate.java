package com.stock.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.stock.model.*;
import com.stockList.model.StockListService;

@MultipartConfig
public class StockManagerUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer typeId = new Integer(req.getParameter("type"));
		StockService stockSvc = new StockService();
		List<StockVO> updateStockList = stockSvc.getAll(typeId);
		
		HttpSession session = req.getSession();
		String url = "/Stock/stockManagerUpdate.jsp";
		session.setAttribute("updateTypeId", typeId);
		session.setAttribute("updateStockList", updateStockList);
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
//		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Collection<Part> parts = req.getParts();
		StockService stockSvc = new StockService();
		HttpSession session = req.getSession();
		
		StockVO updateStock = new StockVO();
		
		StockVO errUpdateStock = (StockVO)session.getAttribute("updateStock");
		
		String stockId = req.getParameter("stockId");
		
		if (stockId != null) {
			updateStock = stockSvc.getOneStock(Integer.parseInt(req.getParameter("stockId")));
		}
		
		if (errUpdateStock != null && stockId != null) {
//			System.out.println(errUpdateStock);
//			System.out.println(updateStock);
			if (errUpdateStock.getStockId() == updateStock.getStockId())
			updateStock = errUpdateStock;
		}
		
		Integer updateNumber = null;
		
		StringBuilder updateMessage = new StringBuilder();
		String success = "修改成功";
			
		
		for (Part part : parts) {
			String partName = part.getName();
			
			// 名稱
			if (partName.contains("stockname")) {
				String stockname = req.getParameter(partName);
				if (stockname != null && stockname.length() != 0) {
//					System.out.println("stockname=" + stockname);
					updateStock.setStockName(stockname);					
				} else {
					updateMessage.append("書名/電影名稱<br>不得為空<br>");
				}
			}

			// 作者/導演 允許空值
			if (partName.contains("stockauthor")) {
				String author = req.getParameter(partName);
				if (author != null && author.length() != 0) {
//					System.out.println("author=" + author);
					updateStock.setAuthor(author);					
				}
			}
			
			// 出版社/發行公司 允許空值
			if (partName.contains("stockpress")) {
				String press = req.getParameter(partName);
				if (press != null && press.length() != 0) {
//					System.out.println("press=" + press);
					updateStock.setPress(press);					
				}
			}
			
			// 出版日 允許空值
			if (partName.contains("stockissuedate")) {
				String issuedate = req.getParameter(partName);
//				System.out.println(issuedate);
				if (issuedate != null && issuedate.length() != 0) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date Date;
					try {
						long actDate = df.parse(java.time.LocalDate.now().toString()).getTime();
						long selDate = df.parse(issuedate).getTime();
						if (selDate <= actDate) {
							Date = df.parse(issuedate);
							java.sql.Date formatDate = new java.sql.Date(Date.getTime());
//							System.out.println("issuedate=" + formatDate);
							updateStock.setIssuedDate(formatDate);							
						} 
					} catch (ParseException e) {
					}					
				} else {
					updateStock.setIssuedDate(null);
				}
			}
			
			// 介紹文字
			if (partName.contains("stockcontent")) {
				String stockContent = req.getParameter(partName).trim();
				if (stockContent != null && stockContent.length() != 0) {
//					System.out.println("content=" + stockContent);
					updateStock.setStockContent(stockContent);					
				} else {
					updateMessage.append("介紹內容需補充<br>");
				}
			}

			/***
			 * 判斷是否為圖片欄位
			 ***/
			if (partName.contains("stockimg")) {
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] stockImg = new byte[in.available()];
					in.read(stockImg);
					in.close();
					updateStock.setStockImg(stockImg);				
				}
			}
			
			// 數量
			if (partName.contains("value")) {
				Integer value = new Integer(req.getParameter(partName));
				updateNumber = value;
//				System.out.println("value=" + value);	
				// 沒錯誤時新增
				if (updateMessage.length() == 0) {
					updateMessage.append(success);
					stockSvc.updateStock(updateStock);
					// 依數量新增 stockList
					for (int i = 0; i < value; i++) {
						/*
						 *  新增時, 狀態為1, 可借
						 */
						StockListService stockListSvc = new StockListService();
						stockListSvc.addStockList(updateStock.getStockId(), 1);
					}
				}
				
			}
		}
			
		String url = "/Stock/stockManagerUpdate.jsp";
		session.setAttribute("updateStock", updateStock);
		session.setAttribute("updateMessage", updateMessage);
		session.setAttribute("updateStockValue", updateNumber);
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
	}

}
