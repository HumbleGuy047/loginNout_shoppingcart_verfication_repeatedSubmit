package com.redKango.pss.filteredQuery;

import java.math.BigDecimal;

import com.redKango.pss.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductQueryObject extends QueryObject {
	private String productName;
	private BigDecimal minSalePrice;
	private BigDecimal maxSalePrice;
	private Long dir_id;
	private String keyword; // 关键字：商品名，价格。。。

	// 已抽取
	protected void customizedQuery() {
		if (StringUtils.hasLength(productName)) {
			super.addCondition(" `Product Name` LIKE ?", "%" + productName + "%");
		}
		if (minSalePrice != null) {
			super.addCondition(" `Sale Price`>=?", minSalePrice);
		}
		if (maxSalePrice != null) {
			super.addCondition(" `Sale Price`<=?", maxSalePrice);
		}
		if (dir_id != null) {
			super.addCondition(" `Parent Id`=?", dir_id);
		}
		if (StringUtils.hasLength(keyword)) {
			super.addCondition(" `Product Name` LIKE ? OR `Brand Name` LIKE ? OR `Supplier Name` LIKE ?",
					"%" + keyword + "%",
					"%" + keyword + "%", "%" + keyword + "%");
		}
	}
}
