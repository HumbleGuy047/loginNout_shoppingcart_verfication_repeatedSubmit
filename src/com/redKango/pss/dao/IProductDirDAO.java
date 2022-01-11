package com.redKango.pss.dao;

import java.util.List;

import com.redKango.pss.domain.ProductDir;

public interface IProductDirDAO {
	/**
	 * 获取所有商品分类信息
	 * 
	 * @return 所有商品分类
	 */
	List<ProductDir> readAll();
}
