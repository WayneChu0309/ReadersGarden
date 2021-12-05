package com.stock.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hibernate.HibernateUtil;
import com.stock.model.HibernateStockService;
import com.stock.model.NewStock;
import com.stock.model.NewStockDAOHibernate;
import com.stock.model.NewStockService;
import com.stock.model.StockBean;
import com.stock.model.StockDAOHibernate;
import com.stockList.model.HibernateStockListService;
import com.stockList.model.StockListBean;
import com.stockList.model.StockListDAOHibernate;
import com.stockList.model.StockListService;
import com.stockType.model.HibernateStockTypeService;
import com.stockType.model.StockTypeBean;
import com.stockType.model.StockTypeDAOHibernate;

@MultipartConfig
public class StockManagerHibernate extends HttpServlet {
	private HibernateStockService stockSvc;
	private HibernateStockTypeService typeSvc;
	private HibernateStockListService listSvc;
	private NewStockService newstockSvc;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		stockSvc = new HibernateStockService(new StockDAOHibernate(HibernateUtil.getSessionFactory()));
		typeSvc = new HibernateStockTypeService(new StockTypeDAOHibernate(HibernateUtil.getSessionFactory()));
		listSvc = new HibernateStockListService(new StockListDAOHibernate(HibernateUtil.getSessionFactory()));
		newstockSvc = new NewStockService(new NewStockDAOHibernate(HibernateUtil.getSessionFactory()));
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String status = req.getParameter("action");
		
		if ("add".equals(status)) {
			HttpSession session = req.getSession();
			
			List<StockTypeBean> typeList = typeSvc.getAll();
			// 日期, 給js用
			String actDate = java.time.LocalDate.now().toString();
			// 類別選項, 給js用
			String typeJson = gson.toJson(typeList);
//			// 第一次進來, addItems 為空
//			String addItems = "{}";
			// 同上, add數量為 0
			Integer addItemsNumber = 0;
			
			req.setAttribute("nowDate", actDate);
			req.setAttribute("addItemsNumber", addItemsNumber);
			session.setAttribute("typeJson", typeJson);
			
			String url = "/Stock/HibernateStockManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
			
		}
		
		
		if ("clear".equals(status)) {
			HttpSession session = req.getSession();
			session.removeAttribute("addStockList");
			req.removeAttribute("addMeaasge");
			req.removeAttribute("addStockValue");
			String url = "/Stock/HibernateStockManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Collection<Part> parts = req.getParts();

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<StockBean> errAddStockList = (List<StockBean>)session.getAttribute("addStockList");
		
		if (errAddStockList != null && errAddStockList.size() != 0) { 
 			for (int i = errAddStockList.size()-1; i >= 0; i--) {
 				StockBean addBean = errAddStockList.get(i);
 				if (addBean.getStockId() != null) {
 					errAddStockList.remove(i);
 				}
 			}
 		}
		
		List<Integer> addStockValue = new ArrayList<>();
		List<StockBean> addStockList = new ArrayList<>();
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
		
		StockBean bean = null;		
		
		for (Part part : parts) {
			String partName = part.getName();
			// 名稱
			if (partName.contains("stockname")) {
				if (errAddStockList != null && errAddStockList.size() > size) {
//					System.out.println("size=" + errAddStockList.size());
					bean = errAddStockList.get(size);
				} else {
					bean = new StockBean();
				}
				String stockname = req.getParameter(partName);
				if (stockname != null && stockname.length() != 0) {
//					System.out.println("stockname=" + stockname);
					bean.setStockName(stockname);					
				} else {
					sb.append("書名/電影名稱<br>不得為空<br>");
				}
				// 類型
			} else if (partName.contains("stocktype")) {
					Integer typeId = new Integer(req.getParameter(partName));
					if (typeId != null && typeId != 0) {
//						System.out.println("typeId=" + typeId);
						StockTypeBean typeBean = new StockTypeBean();
						typeBean.setTypeId(typeId);
						bean.setTypeBean(typeBean);;											
					} else {
						sb.append("類別需選擇<br>");						
					}
					
					// 作者
				} else if (partName.contains("stockauthor")) {
				String author = req.getParameter(partName);
				if (author != null && author.length() != 0) {
//					System.out.println("author=" + author);
					bean.setAuthor(author);					
				}
				// 出版社
			} else if (partName.contains("stockpress")) {
				String press = req.getParameter(partName);
				if (press != null && press.length() != 0) {
//					System.out.println("press=" + press);
					bean.setPress(press);					
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
//							System.out.println("issuedate=" + formatDate);
							bean.setIssuedDate(formatDate);							
						} 
					} catch (ParseException e) {
					}					
				}
				// 內容
			} else if (partName.contains("stockcontent")) {
				String stockContent = req.getParameter(partName).trim();
				if (stockContent != null && stockContent.length() != 0) {
//					System.out.println("content=" + stockContent);
					bean.setStockContent(stockContent);					
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
					bean.setStockImg(stockImg);	
//					System.out.println("我在抓圖片哦!!!");
				} else if (bean.getStockImg() == null){
					sb.append("圖片需選取");
				}
				// 數量
			} else if (partName.contains("value")) {
				Integer value = new Integer(req.getParameter(partName));
				addStockValue.add(value);
//				System.out.println("value=" + value);
				size++;				
				// 沒錯誤時新增
				if (sb.length() == 0) {
					bean = stockSvc.add(bean);
					System.out.println("StockBean=" + bean);
					addStockList.add(bean);
					System.out.println(bean.getTypeBean());
					Integer newtype = bean.getTypeBean().getTypeId() > 9 ? 2 : 1 ;
					
					NewStock newstock = newstockSvc.findByPk(newtype);
					newstock.setStockId(bean.getStockId());
					newstockSvc.update(newstock);
					
					// 依數量新增 stockList
					for (int i = 0; i < value; i++) {
						/*
						 *  新增時, 狀態為1, 可借
						 */
						System.out.printf("我在新增第%d筆資料!!!%n",i+1);
						StockListBean listBean = new StockListBean();
						listBean.setStockBean(bean);
						listBean.setListStates(1);
						listSvc.add(listBean);
					}
					if (errAddStockList != null && errAddStockList.size() > size) {
						bean = errAddStockList.get(size);						
					} else {
						bean = new StockBean();	
					}
					
					addMessage.add(success);
					
				} else {
					addStockList.add(bean);
					addMessage.add(sb.toString());
					sb = new StringBuilder();
				}
			}
		}
			
		String actDate = java.time.LocalDate.now().toString();
		session.setAttribute("addStockList", addStockList);
		req.setAttribute("nowDate", actDate);
		req.setAttribute("addItemsNumber", addStockList.size());
		req.setAttribute("addMessage", addMessage);
		req.setAttribute("addStockValue", addStockValue);
		String url = "/Stock/HibernateStockManager.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
	}

}
