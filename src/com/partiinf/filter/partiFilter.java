package com.partiinf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

public class partiFilter implements Filter {


    public partiFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = "/login/login.jsp";
		try {
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			System.out.println("filter" + memberVO);
			if (memberVO == null) {
				res.sendRedirect(req.getContextPath() + url);
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		} catch(Exception e) {
			System.out.println("filter catch");
			res.sendRedirect(req.getContextPath() + url);
			return;
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
