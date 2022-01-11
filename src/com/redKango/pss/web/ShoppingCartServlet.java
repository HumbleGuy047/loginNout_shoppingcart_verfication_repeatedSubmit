package com.redKango.pss.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.domain.shoppingcart.CartItem;
import com.redKango.pss.domain.shoppingcart.ShoppingCart;

@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接受请求参数
		String cmd = req.getParameter("cmd");
		if ("save".equals(cmd)) {
			save(req, resp);
		} else if ("delete".equals(cmd)) {
			delete(req, resp);

		}
	}

	// 保存
	protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String num = req.getParameter("num");
		//调用商务方法处理请求
		// 从session中取出购物车对象
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("SHOPPINGCART_IN_SESSION");
		//如果为空就创建新的, 并且放到session中
		if (cart == null) {
			cart = new ShoppingCart();
			req.getSession().setAttribute("SHOPPINGCART_IN_SESSION", cart);
		}
		// 封装商品的数据
		CartItem item = new CartItem();
		item.setName(name);
		item.setNum(Integer.parseInt(num));
		if ("电脑".equals(name)) {
			item.setId(111L);
			item.setPrice(10000D);
		} else if ("手机".equals(name)) {
			item.setId(222L);
			item.setPrice(20000D);
		} else if ("平板".equals(name)) {
			item.setId(333L);
			item.setPrice(30000D);
		}

		cart.save(item);
		//控制页面跳转 (session中共享的数据不需要req 去forward)
		//req.getRequestDispatcher("/shoppingcart/cart.jsp").forward(req, resp);	如果用request 会多次发出请求造成多次添加
		resp.sendRedirect("/shoppingcart/cart.jsp");
	}

	// 删除
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("SHOPPINGCART_IN_SESSION");
		cart.delete(Long.parseLong(id));
		resp.sendRedirect("/shoppingcart/cart.jsp");
	}
}
