package com.redKango.pss.dao.impl;

import java.util.List;

import com.redKango.pss.dao.IProductStockDAO;
import com.redKango.pss.domain.ProductStock;
import com.redKango.pss.template.JdbcTemplate;
import com.redKango.pss.template.handler.BeanListHandler;

public class ProductStockDAOImpl implements IProductStockDAO {

	@Override
	public List<ProductStock> readAll() {
		String sql = "SELECT * FROM productstock";
		List<ProductStock> productStock = JdbcTemplate.query(sql, new BeanListHandler<>(ProductStock.class));
		return productStock;
	}

}
