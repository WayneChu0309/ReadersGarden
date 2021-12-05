package com.stock.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.borrow.model.BorrowDAOHibernate;
import com.borrow.model.HibernateBorrowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stock.model.HibernateStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;
import com.stockType.model.HibernateStockTypeService;
import com.stockType.model.StockTypeBean;
import com.stockType.model.StockTypeDAOHibernate;

@MultipartConfig
public class StockManagerUpdateHibernate extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateStockTypeService typeSvc;
	private HibernateStockListService listSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		typeSvc = new HibernateStockTypeService(new StockTypeDAOHibernate(HibernateUtil.getSessionFactory()));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		List<StockBean> updateStockList = null;
		String updateItems = "{}";
		String updateStockInf = "{}";
		int updateNumber = 0;
		// 檢查是否為初次進入 , 不是的話依類別id抓對應的stock
		int typeId = new Integer(req.getParameter("typeId"));
		if (typeId != 0) {
			updateStockList = stockSvc.getAll(typeId);	
			// 轉成 json 格式, 給 js 用
			updateItems = gson.toJson(updateStockList);
		}
		
		// 抓類別清單
		List<StockTypeBean> typeList = typeSvc.getAll();
		
		HttpSession session = req.getSession();

		String actDate = java.time.LocalDate.now().toString();
		
		req.setAttribute("nowDate", actDate);
		req.setAttribute("updateStockInf", updateStockInf);
		req.setAttribute("updateStockValue", updateNumber);
		session.removeAttribute("updateStock");
		session.setAttribute("updateStockList", updateStockList);
		session.setAttribute("typeId", typeId);
		session.setAttribute("typeList", typeList);
		session.setAttribute("updateItems", updateItems);
		
		String url = "/Stock/HibernateStockManagerUpdate.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Collection<Part> parts = req.getParts();
		HttpSession session = req.getSession();
		
		StockBean updateStock = new StockBean();
		StockBean errUpdateStock = (StockBean)session.getAttribute("updateStock");
		
		String stockId = req.getParameter("stockId");
		
		if (stockId != null) {
			updateStock = stockSvc.findByStockId(Integer.parseInt(req.getParameter("stockId")));
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
					stockSvc.update(updateStock);
					// 依數量新增 stockList
					for (int i = 0; i < value; i++) {
						/*
						 *  新增時, 狀態為1, 可借
						 */
						StockListBean listBean = new StockListBean();
						listBean.setStockBean(updateStock);
						listBean.setListStates(1);
						listSvc.add(listBean);
					}
				}
				
			}
		}
		
		
		String actDate = java.time.LocalDate.now().toString();
		String updateStockInf = gson.toJson(updateStock);
		String url = "/Stock/HibernateStockManagerUpdate.jsp";
		req.setAttribute("nowDate", actDate);
		req.setAttribute("updateStockInf", updateStockInf);
		req.setAttribute("updateMessage", updateMessage);
		req.setAttribute("updateStockValue", updateNumber);
		session.setAttribute("updateStock", updateStock);
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
	}

}
