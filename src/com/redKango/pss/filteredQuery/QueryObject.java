package com.redKango.pss.filteredQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class QueryObject {
	private List<String> conditions = new ArrayList<>();
	private List<Object> params = new ArrayList<>();

	// 分页查询的数据
	private Integer currentPage;
	private Integer pageSize;

	public String getQuery() {
		StringBuilder sql = new StringBuilder();
		customizedQuery();
		// build sql
		for (int i = 0; i < conditions.size(); i++) {
			if (i == 0) {
				sql.append(" WHERE ");
			} else {
				sql.append(" AND ");
			}
			sql.append(conditions.get(i));
		}
		return sql.toString();
	}

	// protected暴漏给子类实现
	protected void customizedQuery() {
	}

	// 从子类传递param
	protected void addCondition(String condition, Object... params) { // 可变参数为了关键字查询
		// 由于sql先执行or需要判断，如果多于一个条件那就要在条件前后加（）
		if (params.length > 1) {
			this.conditions.add("("+condition+")");
		} else {
			this.conditions.add(condition);
		}
		this.params.addAll(Arrays.asList(params));
	}
}
