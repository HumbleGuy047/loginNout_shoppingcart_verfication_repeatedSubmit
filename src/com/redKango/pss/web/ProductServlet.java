package com.redKango.pss.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.dao.IProductDirDAO;
import com.redKango.pss.dao.impl.ProductDAOImpl;
import com.redKango.pss.dao.impl.ProductDirDAOImpl;
import com.redKango.pss.domain.Product;
import com.redKango.pss.domain.ProductDir;
import com.redKango.pss.filteredQuery.ProductQueryObject;
import com.redKango.pss.page.PageResult;
import com.redKango.pss.util.StringUtils;

/**
 * A servlet that controls the CRUD of the Product Table.
 * 
 * @author Administrator
 *
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet{

	private static final long serialVersionUID = -819359712165019633L;
	
	private IProductDAO productDao;
	private IProductDirDAO productDirDAO;

	@Override
	public void init() throws ServletException {
		productDao = new ProductDAOImpl();
		productDirDAO = new ProductDirDAOImpl();
	}

	// determines which action to manage the database by the cmd embedded in the URL 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // set encoding type as UTF-8 to avoid jiberish when encounters Chinese characters
		String cmd = (String) req.getParameter("cmd");
		// the whole table has a jsp UI, delete is just a link, and the create and update are represented in one jsp UI
		if ("delete".equals(cmd)) {
			delete(req, resp);
		} else if ("edit".equals(cmd)) {
			edit(req, resp);
		} else if ("save".equals(cmd)) {
			save(req, resp);
		} else {
			try {
				queryAll(req, resp);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	// create and update handling
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		// if id isn't null then it's an update, not a creation.
		if (StringUtils.hasLength(req.getParameter("id"))) {
			Product pro = productDao.readSingle(Long.parseLong(id));
			req.setAttribute("pro", pro); // requestScope sends data
		}
		// 获得所有商品匪类信息，回显道JSP页面中
		List<ProductDir> proDirList = productDirDAO.readAll();
		req.setAttribute("proDirList", proDirList);
		req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req, resp); // jumping to another activity of the editting page
	}

	// save both changes (creation and update)
	private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		// get parameters embedded in the URL
		String id = req.getParameter("id");
		String productName = req.getParameter("productName");
		String dir_id = req.getParameter("dir_id");
		String salePrice = req.getParameter("salePrice");
		String supplier = req.getParameter("supplier");
		String brand = req.getParameter("brand");
		String discount = req.getParameter("discount");
		String costPrice = req.getParameter("costPrice");
		// if id isn't null then it's update, else it's a creation
		if (StringUtils.hasLength(req.getParameter("id"))) {
			Product pro = new Product(Long.parseLong(id), productName, Long.parseLong(dir_id),
					Double.parseDouble(salePrice), supplier, brand, Double.parseDouble(discount),
					Double.parseDouble(costPrice));
			productDao.update(pro);
		}
		else {
			Product pro = new Product(null, productName, Long.parseLong(dir_id), Double.parseDouble(salePrice),
					supplier, brand, Double.parseDouble(discount), Double.parseDouble(costPrice));
			productDao.create(pro);
		}
		resp.sendRedirect("/product?cmd=list"); // after changes are made, go back to the list page.
	}

	// deleting through the id given in the URL
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		if (StringUtils.hasLength(id))
			productDao.delete(Long.parseLong(id));
		resp.sendRedirect("/product?cmd=list");
	}

	// the go-to page (main activity)
	/*private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> proList = dao.readAll();
		req.setAttribute("list", proList);
		req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
	}*/

	// the go-to page (main activity)
	protected void queryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, InterruptedException {
		Thread.sleep(3000);
		//校验重复提交
		//用户隐藏于中的token
		String token = req.getParameter("token");
		//session中的随机数
		String sessionToken = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		//System.out.println(token + "---" + sessionToken);
		//将两者对比
		if (StringUtils.hasLength(token)) {
			if (token.equals(sessionToken)) {
				req.getSession().removeAttribute("TOKEN_IN_SESSION"); // 只从session移除随机数
			} else {
				System.out.println("手贱。。。");
				return;
			}
		}

		// 登陆校验
		Object obj = req.getSession().getAttribute("USER_IN_SESSION"); // 只要user信息在session中为空就跳转回登录页面
		if (obj == null) {
			resp.sendRedirect("/login/login.jsp");
			return;
		}

		ProductQueryObject qo = getProductQueryObject(req);
		PageResult page = productDao.pageQuery(qo);
		req.setAttribute("proDirList", productDirDAO.readAll());
		req.setAttribute("page", page);
		req.setAttribute("qo", qo);
		req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
	}

	private ProductQueryObject getProductQueryObject(HttpServletRequest req) {
		String productName = req.getParameter("productName");
		String minSalePrice = req.getParameter("minSalePrice");
		String maxSalePrice = req.getParameter("maxSalePrice");
		String dir_id = req.getParameter("dir_id");
		String keyword = req.getParameter("keyword");
		String currentPage = req.getParameter("currentPage");
		String pageSize = req.getParameter("pageSize");
		// 默认的页面
		Integer iCurrentPage = 1;
		// 默认的页面大小
		Integer iPageSize = 5;
		if (StringUtils.hasLength(currentPage)) {
			iCurrentPage = Integer.valueOf(currentPage);
		}
		if (StringUtils.hasLength(pageSize)) {
			iPageSize = Integer.valueOf(pageSize);
		}

		ProductQueryObject qo = new ProductQueryObject();
		if (StringUtils.hasLength(productName)) {
			qo.setProductName(productName);
		}
		if (StringUtils.hasLength(minSalePrice)) {
			qo.setMinSalePrice(new BigDecimal(minSalePrice));
		}
		if (StringUtils.hasLength(maxSalePrice)) {
			qo.setMaxSalePrice(new BigDecimal(maxSalePrice));
		}
		if (StringUtils.hasLength(dir_id)) {
			qo.setDir_id(Long.valueOf(dir_id));
		}
		if (StringUtils.hasLength(keyword)) {
			qo.setKeyword(keyword);
		}
		qo.setCurrentPage(iCurrentPage);
		qo.setPageSize(iPageSize);
		return qo;
	}
}
