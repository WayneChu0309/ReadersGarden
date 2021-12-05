package com.partiinf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.event.model.Event;
import com.event.model.EventService;
import com.member.model.MemberVO;
import com.partiinf.model.PartiinfService;

public class partiServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = "/event/eventJoin.jsp";
		String url2 = "/event/eventJoinfail.jsp";
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("getOne_join".equals(action)) {
			System.out.println("2");
			try {
				Object mem = session.getAttribute("member");
				if (mem == null) {
					res.sendRedirect(req.getContextPath() + "/login/login.jsp");
					return;
				}
				MemberVO memVO = (MemberVO) mem;
				
				Integer memberid = memVO.getNumber();
				
				Integer eventid = new Integer(req.getParameter("eventid"));
				System.out.println("memberid"+memberid);
				System.out.println("eventid"+eventid);
				
				PartiinfService partSvc = new PartiinfService();
				partSvc.addParti(eventid, memberid);
				
				EventService eventSvc = new EventService();
				Event event = eventSvc.getOneEvent(eventid);
				req.setAttribute("event", event);
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher(url2);
				successView.forward(req, res);
				return;
			}
		}
		
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
		
	}
	
}