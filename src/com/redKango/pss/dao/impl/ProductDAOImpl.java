package com.redKango.pss.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.redKango.pss.dao.IProductDAO;
import com.redKango.pss.domain.Product;
import com.redKango.pss.filteredQuery.ProductQueryObject;
import com.redKango.pss.page.PageResult;
import com.redKango.pss.template.HibernatorTemplate;
import com.redKango.pss.template.IResultSetHandler;
import com.redKango.pss.template.JdbcTemplate;
import com.redKango.pss.template.handler.BeanHandler;
import com.redKango.pss.template.handler.BeanListHandler;

public class ProductDAOImpl extends BaseDAO implements IProductDAO {

	// DML
	@Override
	public void create(Product pro) {
		HibernatorTemplate.create(pro);
	}

	@Override
	public void delete(Long id) {
		HibernatorTemplate.delete(id, new Product());
	}

	@Override
	public void update(Product pro) {
		HibernatorTemplate.update(pro);
	}

	// DQL
	@Override
	public Product readSingle(Long id) {
		String sql = "SELECT * FROM `product` WHERE id = ?";
		// query(SQL, a new ResultSetHandler(param: domain object), id)
		return (Product) JdbcTemplate.query(sql, new BeanHandler<>(Product.class), id);
	}
	

	@Override
	public PageResult page(Integer currentPage, Integer pageSize) {
		Integer beginIndex = (currentPage - 1) * pageSize;
		// 获取商品的结果集
		List<Product> listData = JdbcTemplate.query("SELECT * FROM `product` LIMIT ?, ?",
				new BeanListHandler<>(Product.class), beginIndex, pageSize);
	
		// 获取总条数
		Integer totalCount = JdbcTemplate.query("SELECT COUNT(*) FROM `product`", new IResultSetHandler<Long>() {
			@Override
			public Long handle(ResultSet rs) {
				try {
					if (rs.next()) {
						return rs.getLong(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0L;
			}
		}).intValue();
	
		System.out.println(listData);
		System.out.println(totalCount);
	
		PageResult result = new PageResult(listData, totalCount, currentPage, pageSize);
	
		return result;
	}

	@Override
	public PageResult pageQuery(ProductQueryObject qo) {
		return super.query(qo, "product", Product.class);
	}

}
