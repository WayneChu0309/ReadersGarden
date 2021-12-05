package com.library.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connectionpool.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@WebServlet("/WelcomeReadersGarden")
public class WelcomeReadersGarden extends HttpServlet {
	private int capacity;
	private Jedis jedis;
	private static JedisPool pool = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");		
		ServletContext context = req.getServletContext();
		String action = req.getParameter("action");
		capacity = (int) context.getAttribute("capacity");
		String addr = req.getRemoteAddr();
		pool = JedisUtil.getJedisPool();
		jedis = pool.getResource();
		Map<String, String> capacityRedis = jedis.hgetAll("capacity");
		
		String check = jedis.hget("capacity", addr);
		if ("in".equals(action)) {
			if (check == null) {
				capacity--;
				capacityRedis.put(addr, "in");
				jedis.hmset("capacity", capacityRedis);
			}			
		}
		
		if ("out".equals(action)) {
			if ("in".equals(check)) {
				jedis.hdel("capacity", addr);
				capacity++;
			}
		}
		
		jedis.close();
		context.setAttribute("capacity", capacity);
		String url = "/ReadersGarden";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		return;
	}

}
