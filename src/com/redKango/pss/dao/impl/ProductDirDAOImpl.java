package com.redKango.pss.dao.impl;

import java.util.List;

import com.redKango.pss.dao.IProductDirDAO;
import com.redKango.pss.domain.ProductDir;
import com.redKango.pss.template.JdbcTemplate;
import com.redKango.pss.template.handler.BeanListHandler;

public class ProductDirDAOImpl implements IProductDirDAO {

	@Override
	public List<ProductDir> readAll() {
		String sql = "SELECT * FROM productdir";
		List<ProductDir> productDir = JdbcTemplate.query(sql, new BeanListHandler<>(ProductDir.class));
		return productDir;
	}

}
