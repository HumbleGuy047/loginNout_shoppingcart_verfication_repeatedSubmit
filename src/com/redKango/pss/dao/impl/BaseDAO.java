package com.redKango.pss.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.redKango.pss.filteredQuery.QueryObject;
import com.redKango.pss.page.PageResult;
import com.redKango.pss.template.IResultSetHandler;
import com.redKango.pss.template.JdbcTemplate;
import com.redKango.pss.template.handler.BeanListHandler;

public class BaseDAO {
	public PageResult query(QueryObject qo, String tableName, Class clz) {
		// 获取商品的结果集
		String sql = "SELECT * FROM `" + tableName + "` " + qo.getQuery() + " LIMIT ?, ?";
		// 获取高级查询的参数
		List<Object> params = qo.getParams();
		// 重新创建一个新的集合，用来存放高级查询和分页的数据
		List<Object> queryNpageParams = new ArrayList<>();
		Integer beginIndex = (qo.getCurrentPage() - 1) * qo.getPageSize();
		queryNpageParams.addAll(params);
		queryNpageParams.add(beginIndex);
		queryNpageParams.add(qo.getPageSize());
		List listData = JdbcTemplate.query(sql, new BeanListHandler<>(clz),
				queryNpageParams.toArray());

		// 获取总条数
		Integer totalCount = JdbcTemplate
				.query("SELECT COUNT(*) FROM `" + tableName + "` " + qo.getQuery(), new IResultSetHandler<Long>() {
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
				}, params.toArray()).intValue();
		PageResult result = new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
		return result;
	}
}
