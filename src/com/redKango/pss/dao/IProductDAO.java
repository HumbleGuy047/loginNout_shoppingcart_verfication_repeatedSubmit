package com.redKango.pss.dao;

import com.redKango.pss.domain.Product;
import com.redKango.pss.filteredQuery.ProductQueryObject;
import com.redKango.pss.page.PageResult;

/**
 * CRUD operations for the Product Table.
 * 
 * @author Administrator
 *
 */
public interface IProductDAO {
	/**
	 * Create new row of information about a product.
	 * 
	 * @param pro The Product class contains the id (primary key), productName,
	 *            dir_id (foreign key), salePrice, supplier, brand, discount,
	 *            costPrice.
	 */
	void create(Product pro);

	/**
	 * Delete a row of information about a product.
	 * 
	 * @param id This id (primary key) indicates which row to delete.
	 */
	void delete(Long id);

	/**
	 * Update one or multiple data-cells of information about a product.
	 * 
	 * @param pro A new product contains the altered information of a product
	 *            including the id that indicates which row to update.
	 */
	void update(Product pro);

	/**
	 * Get one row of information.
	 * 
	 * @param id Id that indicates which row to get info from.
	 * @return The info received is stored in a Product object and returned.
	 */
	Product readSingle(Long id);

	/**
	 * Pagination Query
	 * 
	 * @param currentPage User's input of current page number.
	 * @param pageSize    User's input of the size of the current page.
	 * @return Returns a PageResult object.
	 */
	PageResult page(Integer currentPage, Integer pageSize);

	/**
	 * 高级查询加分页
	 * 
	 * @param qo 高级查询和分页的数据
	 * @return 返回页面之后的数据
	 */
	PageResult pageQuery(ProductQueryObject qo);

}
