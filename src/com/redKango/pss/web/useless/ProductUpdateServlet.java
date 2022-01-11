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

@WebServlet("/update")
public class ProductUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2904160406713002373L;

	private IProductDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new ProductDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String productName = req.getParameter("productName");
		Long dir_id = Long.parseLong(req.getParameter("dir_id"));
		Double salePrice = Double.parseDouble(req.getParameter("salePrice"));
		String supplier = req.getParameter("supplier");
		String brand = req.getParameter("brand");
		Double discount = Double.parseDouble(req.getParameter("discount"));
		Double costPrice = Double.parseDouble(req.getParameter("costPrice"));

		Product pro = new Product(id, productName, dir_id, salePrice, supplier, brand, discount, costPrice);
		dao.update(pro);
		resp.sendRedirect("/product/list");
	}
}
