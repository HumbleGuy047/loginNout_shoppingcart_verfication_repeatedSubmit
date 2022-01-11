package com.redKango.pss.web.useless;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.dao.impl.ProductDAOImpl;
import com.redKango.pss.domain.Product;

@WebServlet("/product/edit")
public class ProductEditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2827003863968489763L;

	private IProductDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new ProductDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product pro = new Product();
		if (req.getParameter("id") != null) {
			Long id = Long.parseLong(req.getParameter("id"));
			pro = dao.readSingle(id);
		}
		else {
			pro = null;
		}
		req.setAttribute("pro", pro);
		req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req, resp);
	}
}
