package com.redKango.pss.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.dao.IUserDAO;
import com.redKango.pss.dao.impl.UserDAOImpl;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUserDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new UserDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate(); // 销毁session对象
		resp.sendRedirect("/login/login.jsp"); // 回到登录页面
	}
}
