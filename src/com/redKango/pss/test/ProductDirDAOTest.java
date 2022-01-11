package com.redKango.pss.test;

import java.util.List;

import org.junit.Test;

import com.redKango.pss.dao.IProductDirDAO;
import com.redKango.pss.dao.impl.ProductDirDAOImpl;
import com.redKango.pss.domain.ProductDir;

public class ProductDirDAOTest {

	private IProductDirDAO dao = new ProductDirDAOImpl();
	@Test
	public void testReadAll() {
		List<ProductDir> dirlist = dao.readAll();
		for (ProductDir dir : dirlist) {
			System.out.println(dir);
		}
	}

}
