package com.redKango.pss.filteredQuery;

import com.redKango.pss.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDirQueryObject extends QueryObject {
	private String name;

	protected void customizedQuery() {
		if (StringUtils.hasLength(name)) {
			super.addCondition(" dirName LIKE ?", "%" + name + "%");
		}
	}
}
