package com.event.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.event.model.Event;
import com.event.model.EventService;
import com.member.model.MemberVO;


public class EventServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println(1);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
	
		
		
//		System.out.println("測試= " +category);
		
		
		if ("cateselect".equals(action)) { //來自eventinfo.jsp的請求
			Integer category = new Integer(req.getParameter("category"));
			
			if(category == 0) {
				String url = "/event/eventInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交eventinfo.jsp
				successView.forward(req, res);	
				return; 
			}
			
			List<Event> eventlist = new LinkedList<Event>();
			
			
			EventService eventSvc = new EventService();
			eventlist = eventSvc.getAllByCate(category);
			
			req.setAttribute("eventlist", eventlist);
			
			String url = "/event/eventInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交eventinfo.jsp
			successView.forward(req, res);		
			return;				
			}
		
		if ("dateselect".equals(action)) { //來自eventinfo.jsp的請求
			Integer dateselect = new Integer(req.getParameter("date"));
			
			if(dateselect == 1) {
				List<Event> eventlist = new LinkedList<Event>();
				
				EventService eventSvc = new EventService();
				eventlist = eventSvc.getAllActiveDesc();
				
				req.setAttribute("eventlist", eventlist);
				String url = "/event/eventInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交eventinfo.jsp
				successView.forward(req, res);	
				return; 
				
			}else if(dateselect == 2) {
			
			List<Event> eventlist = new LinkedList<Event>();
			
			
			EventService eventSvc = new EventService();
			eventlist = eventSvc.getAllActiveAsc();
			
			req.setAttribute("eventlist", eventlist);
			
			String url = "/event/eventInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交eventinfo.jsp
			successView.forward(req, res);		
			return;					
			}
		}
		

		
		if("getOne_event".equals(action)) { //來自eventinfo.jsp的請求
			
			String eventSelect = req.getParameter("eventid");
			Integer eventid = new Integer(eventSelect);
			
			EventService eventSvc = new EventService();
			Event event = eventSvc.getOneEvent(eventid);
			
			req.setAttribute("event", event);
			
			String url = "/event/eventDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
		
		if("getOne_join".equals(action)) { //來自eventDetail.jsp的請求
			String eventSelect = req.getParameter("eventid");
			Integer eventid = new Integer(eventSelect);
			
			EventService eventSvc = new EventService();
			Event event = eventSvc.getOneEvent(eventid);
			
			req.setAttribute("event", event);
			
			String url = "/event/eventJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
			return;
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自eventMylist.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer eventid = new Integer(req.getParameter("eventid"));
				
				/***************************2.開始查詢資料****************************************/
				EventService eventSvc = new EventService();
				Event event = eventSvc.getOneEvent(eventid);
				String eventstart = event.getEventstart().toString().substring(0, 16).replace(" ", "T");
				String eventend = event.getEventend().toString().substring(0, 16).replace(" ", "T");
				String eventappstart = event.getEventappstart().toString().substring(0, 16).replace(" ", "T");
				String eventappend = event.getEventappend().toString().substring(0, 16).replace(" ", "T");
				req.setAttribute("eventstart", eventstart);
				req.setAttribute("eventend", eventend);
				req.setAttribute("eventappstart", eventappstart);
				req.setAttribute("eventappend", eventappend);
				System.out.println("eventstart" + eventstart);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("event", event);         // 資料庫取出的event物件,存入req
				String url = "/event/eventUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 eventUpdate.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/event/eventMylist.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自eventUpdate.jsp的請求
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer eventid = new Integer (req.getParameter("eventid").trim());
				System.out.println("eventid" + eventid);
				
				Integer eventcateid = new Integer (req.getParameter("eventcateid").trim());
				
				HttpSession session = req.getSession();
				MemberVO memVO = (MemberVO) session.getAttribute("member");
				Integer memberid = memVO.getNumber();
				
				
				String capacityStr = req.getParameter("capacity").trim();
				Integer capacity = null;
				
				if(capacityStr == null || capacityStr.equals("")) {
					errorMsgs.add("人數需求: 請勿空白");
				}  else {
					try{
						capacity = new Integer(capacityStr);
						if(capacity <= 0) {
							errorMsgs.add("人數需求: 請填入1以上的數字");
						}
					} catch ( NumberFormatException e ) {
						errorMsgs.add("人數需求: 請填入數字");				
					}
				}			
				

//				try {				
					String eventname = req.getParameter("eventname");
					String evtnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (eventname == null || eventname.trim().length() == 0) {
						errorMsgs.add("活動名稱: 請勿空白");
					} else if(!eventname.trim().matches(evtnameReg)) { 
						//以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }

			
					String eventdescription = req.getParameter("eventdescription");
					if (eventname == null || eventname.trim().length() == 0) {
						errorMsgs.add("活動內容: 請勿空白");
					} 
					
				
					java.sql.Timestamp eventstart = null;
					try {
						String date = req.getParameter("eventstart").replace("T", " ");
						eventstart = java.sql.Timestamp.valueOf(date+":00");
					} catch (IllegalArgumentException e) {
						errorMsgs.add("請輸入活動開始時間!");
					}
					
					java.sql.Timestamp eventend = null;
					try {
						String date = req.getParameter("eventend").replace("T", " ");
						eventend = java.sql.Timestamp.valueOf(date+":00");
					} catch (IllegalArgumentException e) {
						errorMsgs.add("請輸入活動結束時間!");
					}				
					
					java.sql.Timestamp eventappstart = null;
					try {
						String date = req.getParameter("eventappstart").replace("T", " ");
						eventappstart = java.sql.Timestamp.valueOf(date+":00");
					} catch (IllegalArgumentException e) {
						errorMsgs.add("請輸入活動報名開始時間!");
					}
					
					java.sql.Timestamp eventappend = null;
					try {
						String date = req.getParameter("eventappend").replace("T", " ");
						eventappend = java.sql.Timestamp.valueOf(date+":00");
					} catch (IllegalArgumentException e) {
						errorMsgs.add("請輸入活動報名結束時間!");
					}
					
					
					Integer eventstatus = 0;

					Event event = new Event();
					event.setEventid(eventid);
					event.setEventcateid(eventcateid);
					event.setMemberid(memberid);
					event.setCapacity(capacity);
					event.setEventname(eventname);
					event.setEventdescription(eventdescription);
					event.setEventstart(eventstart);
					event.setEventend(eventend);
					event.setEventappstart(eventappstart);
					event.setEventappend(eventappend);
					event.setEventstatus(eventstatus);
					System.out.println("update="+event);
		

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("event", event); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/event/eventUpdate.jsp");
						failureView.forward(req, res);
						return;
					}
				
				/***************************2.開始修改資料*****************************************/
				EventService eventSvc = new EventService();
				event = eventSvc.updateEvent(eventid, eventcateid, memberid, capacity, eventname, eventdescription, eventstart, eventend, eventend, eventappend, eventstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("event", event); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/eventlist/EventlistServlet?action=mylist";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/event/eventUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		
				
        if ("insert".equals(action)) { // 來自addEvent.jsp的請求  
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
		
			Integer eventcateid = new Integer (req.getParameter("eventcateid").trim());
			
			HttpSession session = req.getSession();
			MemberVO memVO = (MemberVO) session.getAttribute("member");
			Integer memberid = memVO.getNumber();
			String capacityStr = req.getParameter("capacity").trim();
			Integer capacity = null;
			
			if(capacityStr == null || capacityStr.equals("")) {
				errorMsgs.add("人數需求: 請勿空白");
			}  else {
				try{
					capacity = new Integer(capacityStr);
					if(capacity <= 0) {
						errorMsgs.add("人數需求: 請填入1以上的數字");
					}
				} catch ( NumberFormatException e ) {
					errorMsgs.add("人數需求: 請填入數字");				
				}
			}			
			

//			try {				
				String eventname = req.getParameter("eventname");
				String evtnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (eventname == null || eventname.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!eventname.trim().matches(evtnameReg)) { 
					//以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

		
				String eventdescription = req.getParameter("eventdescription");
				if (eventname == null || eventname.trim().length() == 0) {
					errorMsgs.add("活動內容: 請勿空白");
				} 
				
			
				java.sql.Timestamp eventstart = null;
				try {
					String date = req.getParameter("eventstart").replace("T", " ");
					eventstart = java.sql.Timestamp.valueOf(date+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始時間!");
				}
				
				java.sql.Timestamp eventend = null;
				try {
					String date = req.getParameter("eventend").replace("T", " ");
					eventend = java.sql.Timestamp.valueOf(date+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束時間!");
				}				
				
				java.sql.Timestamp eventappstart = null;
				try {
					String date = req.getParameter("eventappstart").replace("T", " ");
					eventappstart = java.sql.Timestamp.valueOf(date+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動報名開始時間!");
				}
				
				java.sql.Timestamp eventappend = null;
				try {
					String date = req.getParameter("eventappend").replace("T", " ");
					eventappend = java.sql.Timestamp.valueOf(date+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動報名結束時間!");
				}
				
				
				Integer eventstatus = 0;

				Event event = new Event();
				event.setEventcateid(eventcateid);
				event.setMemberid(memberid);
				event.setCapacity(capacity);
				event.setEventname(eventname);
				event.setEventdescription(eventdescription);
				event.setEventstart(eventstart);
				event.setEventend(eventend);
				event.setEventappstart(eventappstart);
				event.setEventappend(eventappend);
				event.setEventstatus(eventstatus);
				System.out.println("insert="+event);
				// 活動開始時間 > 活動結束時間
				// 活動報名截止 > 活動開始
				// 報名開始 > 報名截止
				if (event.getEventend() == null || event.getEventstart() == null || 
						event.getEventappend() == null || event.getEventappstart() == null) {
					errorMsgs.add("時間需選擇");
				}else if ((event.getEventstart().getTime() > event.getEventend().getTime()) || 
						(event.getEventappend().getTime() > event.getEventstart().getTime()) ||
						(event.getEventappstart().getTime() > event.getEventappend().getTime())) {
					errorMsgs.add("時間順序錯誤");
				}
	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("event", event); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/event/eventAdd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EventService eventSvc = new EventService();
				event = eventSvc.addEvent(eventcateid, memberid, capacity, eventname, eventdescription, eventstart, eventend, eventend, eventappend, eventstatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/event/eventInfo.jsp";
				res.sendRedirect(req.getContextPath() + url);
				return;
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/event/eventAdd.jsp");
//				failureView.forward(req, res);
//			}

		
		}
	}
}
