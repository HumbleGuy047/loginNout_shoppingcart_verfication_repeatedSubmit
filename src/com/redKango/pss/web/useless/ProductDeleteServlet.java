package com.redKango.pss.web.useless;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.dao.impl.ProductDAOImpl;

@WebServlet("/delete")
public class ProductDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -581591401085508494L;

	private IProductDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new ProductDAOImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		dao.delete(id);
		resp.sendRedirect("/product/list");
	}
}
