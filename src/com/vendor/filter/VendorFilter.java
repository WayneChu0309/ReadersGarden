package com.vendor.filter;

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

import com.vendor.model.VendorVO;

public class VendorFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = "/login/vendorLogin.jsp";
		
		try {
			VendorVO vendorVO = (VendorVO) session.getAttribute("vendorVO");
			if(vendorVO == null) {
				session.setAttribute("location", req.getRequestURI());
				res.sendRedirect(req.getContextPath() + url);
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		} catch(Exception e) {
			res.sendRedirect(req.getContextPath()+url);
			return;
		}		
	}
	
    public VendorFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
