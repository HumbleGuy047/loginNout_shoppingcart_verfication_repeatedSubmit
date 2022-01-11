package com.redKango.pss.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.dao.IUserDAO;
import com.redKango.pss.dao.impl.UserDAOImpl;
import com.redKango.pss.domain.User;
import com.redKango.pss.util.StringUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
		req.setCharacterEncoding("UTF-8");
		// 验证码验证
		// 用户填写的验证码
		String randomCode = req.getParameter("checkCode");
		//获取session中的验证码
		String sessionRandomCode = (String) req.getSession().getAttribute("RANDOMCODE_IN_SESSION");
		//将两者比对
		if (!StringUtils.hasLength(randomCode) || !StringUtils.hasLength(sessionRandomCode)) {
			req.getSession().setAttribute("errorMsg", "验证码已过期或未被填写");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
			return;
		} else if (!randomCode.equals(sessionRandomCode)) {
			req.getSession().setAttribute("errorMsg", "验证码错误，请重新输入");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
			return;
		}

		// 用户名和密码验证
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 调用业务方法处理请求
		User u = dao.checkLogin(username, password);
		if (u.getId() == null) {
			req.getSession().setAttribute("errorMsg", "用户名或密码错误。");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
			return;
		}
		req.getSession().setAttribute("USER_IN_SESSION", u);

		// 防止重复提交表单 （未请求页面就重新提交数据）
		// 生成随机数
		String token = UUID.randomUUID().toString();
		// 将随机数放入session
		req.getSession().setAttribute("TOKEN_IN_SESSION", token);

		resp.sendRedirect("/product");
	}
}
