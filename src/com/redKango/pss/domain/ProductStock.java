package com.redKango.pss.domain;


import java.util.Date;

import lombok.Data;

// 商品分类的实例类
@Data
public class ProductStock {
	private Long id;
	private Long product_id;
	private Integer storeNum;
	private Date lastShippedInDate;
	private Date lastShippedOutDate;
	private Integer warningNum;
}
