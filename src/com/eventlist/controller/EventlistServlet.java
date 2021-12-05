package com.eventlist.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.partiinf.model.Partiinf;
import com.partiinf.model.PartiinfService;

public class EventlistServlet extends HttpServlet {
	EventService eventSvc;
	PartiinfService partSvc;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(1);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		eventSvc = new EventService();
		partSvc = new PartiinfService();
		
		if ("mylist".equals(action)) {
			req.setAttribute("mylist", eventSvc.findByMember(memberVO.getNumber()));
			String url = "/event/eventMylist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);  
			successView.forward(req, res);
			return;
		}
		
		
		if ("myjoin".equals(action)) {
			List<Partiinf> parti = partSvc.findByMember(memberVO.getNumber());
			List<Event> myjoin = new ArrayList<>();
			for (int i = 0; i < parti.size(); i++) {
				myjoin.add(eventSvc.getOneEvent(parti.get(i).geteventid()));
			}
			req.setAttribute("myjoin", myjoin);
			String url = "/event/eventMyjoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);  
			successView.forward(req, res);
			return;
		}
		
	}
}
