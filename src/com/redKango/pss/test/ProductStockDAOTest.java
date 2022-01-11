package com.redKango.pss.test;

import java.util.List;

import org.junit.Test;

import com.redKango.pss.dao.IProductStockDAO;
import com.redKango.pss.dao.impl.ProductStockDAOImpl;
import com.redKango.pss.domain.ProductStock;

public class ProductStockDAOTest {

	private IProductStockDAO dao = new ProductStockDAOImpl();

	@Test
	public void testReadAll() throws Exception {
		List<ProductStock> stockList = dao.readAll();
		for (ProductStock stock : stockList) {
			System.out.println(stock);
		}
	}
}
