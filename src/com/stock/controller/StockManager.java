package com.stock.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.stock.model.*;
import com.stockList.model.StockListService;

@MultipartConfig
public class StockManager extends HttpServlet {
	private StockService stockSvc = new StockService();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String status = req.getParameter("status");
		if ("clear".equals(status)) {
			HttpSession session = req.getSession();
			session.removeAttribute("addStockList");
			session.removeAttribute("addMeaasge");
			session.removeAttribute("addStockValue");
			String url = "/Stock/stockManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		
//		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Collection<Part> parts = req.getParts();

		HttpSession session = req.getSession();
		StockListService stockListSvc = new StockListService();
		@SuppressWarnings("unchecked")
		List<StockVO> errAddStockList = (List<StockVO>)session.getAttribute("addStockList");
		
		if (errAddStockList != null && errAddStockList.size() != 0) { 
 			for (int i = errAddStockList.size()-1; i >= 0; i--) {
 				StockVO addVO = errAddStockList.get(i);
 				if (addVO.getStockId() != null) {
 					errAddStockList.remove(i);
 				}
 			}
 		}
		
		List<Integer> addStockValue = new ArrayList<>();
		List<StockVO> addStockList = new ArrayList<>();
		List<String> addMessage = new LinkedList<>();
		
		
		int size = 0;

		Integer stockId = null;
		StringBuilder sb = new StringBuilder();
		String success = "新增成功";
		
//		Map<String, String[]> paramMap = req.getParameterMap();
//		for( Entry<String, String[]> entrySet : paramMap.entrySet()) {
//			entrySet.getKey();
//			entrySet.getValue();
//		}
		
		StockVO vo = null;		
		
		for (Part part : parts) {
			String partName = part.getName();
			// 名稱
			if (partName.contains("stockname")) {
				if (errAddStockList != null && errAddStockList.size() > size) {
					System.out.println("size=" + errAddStockList.size());
					vo = errAddStockList.get(size);			
				} else {
					vo = new StockVO();
				}
				String stockname = req.getParameter(partName);
				if (stockname != null && stockname.length() != 0) {
					System.out.println("stockname=" + stockname);
					vo.setStockName(stockname);					
				} else {
					sb.append("書名/電影名稱<br>不得為空<br>");
				}
				// 類型
			} else if (partName.contains("stocktype")) {
					Integer typeId = new Integer(req.getParameter(partName));
					if (typeId != null && typeId != 0) {
						System.out.println("typeId=" + typeId);
						vo.setTypeId(typeId);											
					} else {
						sb.append("類別需選擇<br>");						
					}
					
					// 作者
				} else if (partName.contains("stockauthor")) {
				String author = req.getParameter(partName);
				if (author != null && author.length() != 0) {
					System.out.println("author=" + author);
					vo.setAuthor(author);					
				}
				// 出版社
			} else if (partName.contains("stockpress")) {
				String press = req.getParameter(partName);
				if (press != null && press.length() != 0) {
					System.out.println("press=" + press);
					vo.setPress(press);					
				}
				// 出版日
			} else if (partName.contains("stockissuedate")) {
				String issuedate = req.getParameter(partName);
				if (issuedate != null && issuedate.length() != 0) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date Date;
					try {
						long actDate = df.parse(java.time.LocalDate.now().toString()).getTime();
						long selDate = df.parse(issuedate).getTime();
						if (selDate <= actDate) {
							Date = df.parse(issuedate);
							java.sql.Date formatDate = new java.sql.Date(Date.getTime());
							System.out.println("issuedate=" + formatDate);
							vo.setIssuedDate(formatDate);							
						} 
					} catch (ParseException e) {
					}					
				}
				// 內容
			} else if (partName.contains("stockcontent")) {
				String stockContent = req.getParameter(partName).trim();
				if (stockContent != null && stockContent.length() != 0) {
					System.out.println("content=" + stockContent);
					vo.setStockContent(stockContent);					
				} else {
					sb.append("介紹內容需補充<br>");
				}
				// 圖片
			} else if (partName.contains("stockimg")) {
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] stockImg = new byte[in.available()];
					in.read(stockImg);
					in.close();
					vo.setStockImg(stockImg);	
					System.out.println("我在抓圖片哦!!!");
				} else if (vo.getStockImg() == null){
					sb.append("圖片需選取");
				}
				// 數量
			} else if (partName.contains("value")) {
				Integer value = new Integer(req.getParameter(partName));
				addStockValue.add(value);
				System.out.println("value=" + value);
				System.out.println(size);
				size++;				
				// 沒錯誤時新增
				if (sb.length() == 0) {
					stockId = stockSvc.addStock2(vo);
					vo.setStockId(stockId);
					addStockList.add(vo);
					
					// 依數量新增 stockList
					for (int i = 0; i < value; i++) {
						/*
						 *  新增時, 狀態為1, 可借
						 */
						System.out.printf("我在新增第%d筆資料!!!%n",i+1);
						stockListSvc.addStockList(stockId, 1);
					}
					
					if (errAddStockList != null && errAddStockList.size() > size) {
						vo = errAddStockList.get(size);						
					} else {
						 vo = new StockVO();	
					}
					
					addMessage.add(success);
					
				} else {
					addStockList.add(vo);
					addMessage.add(sb.toString());
					sb = new StringBuilder();
				}
			}
		}
			
		String url = "/Stock/stockManager.jsp";
		session.setAttribute("addStockList", addStockList);
		session.setAttribute("addMessage", addMessage);
		session.setAttribute("addStockValue", addStockValue);
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		System.out.println("start forward addMessage="+addMessage);
		System.out.println("addStockList="+addStockList);
		System.out.println("addStockValue="+addStockValue);
		successView.forward(req, res);
		return;
	}

}
