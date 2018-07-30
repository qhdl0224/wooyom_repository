package com.lectopia.lab.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.istack.internal.Nullable;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception
	{
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("login");
		if(loginId == null || loginId.isEmpty()) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable
            ModelAndView modelAndView) throws Exception
	{
		
	}
	
}
