package com.redKango.pss.web.useless;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.dao.impl.ProductDAOImpl;

@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -228266587244537373L;
	private IProductDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new ProductDAOImpl();
	}

	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> proList = dao.readAll();
		req.setAttribute("list", proList);
		req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
	}*/

}
