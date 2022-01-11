package com.redKango.pss.test;

import org.junit.Test;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.dao.impl.ProductDAOImpl;
import com.redKango.pss.domain.Product;
import com.redKango.pss.page.PageResult;

public class ProductDAOTest {

	private IProductDAO dao = (IProductDAO) new ProductDAOImpl();

	@Test
	public void testCreate() throws Exception {
		Product pro = new Product();
		pro.setProductName("爱国者GM330");
		pro.setSalePrice(250.98);
		pro.setDir_id(3L);
		pro.setSupplier("郑州重工");
		pro.setBrand("红袋鼠");
		pro.setDiscount(0.75);
		pro.setCostPrice(75.3);
		dao.create(pro);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(31L);
	}

	@Test
	public void testUpdate() throws Exception {
		Product pro = new Product();
		pro.setId(33L);
		pro.setProductName("张二疯");
		pro.setSalePrice(2222.20);
		pro.setDir_id(2L);
		pro.setSupplier("操奥奥");
		pro.setBrand("CCccccccccccccC");
		pro.setDiscount(0.5);
		pro.setCostPrice(122.10);
		dao.update(pro);
	}

	@Test
	public void testReadSingle() throws Exception {
		Product pro = dao.readSingle(19L);
		System.out.println(pro);
	}

	@Test
	public void testPage() throws Exception {
		PageResult page = dao.page(2, 5);
		System.out.println(page);
	}
}
