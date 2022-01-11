package com.redKango.pss.domain;

import lombok.Data;

// 商品分类的实例类
@Data
public class ProductDir {
	private Long id;
	private String dirName;
	private Long parent_id;
}
