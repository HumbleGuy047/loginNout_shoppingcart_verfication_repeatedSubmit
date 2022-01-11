package com.redKango.pss.dao;

import java.util.List;

import com.redKango.pss.domain.ProductStock;

public interface IProductStockDAO {
	/**
	 * Get all stocking information.
	 * 
	 * @return 所有商品分类
	 */
	List<ProductStock> readAll();
}
