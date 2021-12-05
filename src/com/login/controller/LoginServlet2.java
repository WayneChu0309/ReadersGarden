package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.websocket.Session;
import com.member.model.*;

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet2() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session = request.getSession();
//		boolean flag=new MemberDAO().login(email, password);
		MemberVO member = new MemberService().login(email, password);
		if (member != null) {
			String url = "/home/home.jsp";
			response.setContentType("text/html;charset=UTF-8");
			session.setAttribute("member", member);
			System.out.println("True");
			pWriter.print("True");
			
			
		} else {
			pWriter.print("False");
		}
	}
}
