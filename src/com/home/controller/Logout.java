package com.home.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		session.removeAttribute("vendorVO");
		session.removeAttribute("member");
			
		//刪除廠商自動登入的cookie
		Cookie[] cookies = req.getCookies();
		if(cookies!= null && cookies.length > 0){
			for(Cookie c : cookies){
				if("autoLogin".equals(c.getName())){
					Jedis jedis = new Jedis("localhost", 6379);
					jedis.del(c.getValue());
					jedis.close();
					c.setMaxAge(0);
					res.addCookie(c);
				}
			}	
		}
		
		String url = req.getContextPath();
		System.out.println(url);
		res.sendRedirect(url + "/home/home.jsp");
		return;
	}
	

}
