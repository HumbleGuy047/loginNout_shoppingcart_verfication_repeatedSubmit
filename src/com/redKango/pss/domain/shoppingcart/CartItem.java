package com.redKango.pss.domain.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 商品对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	// 唯一的表示
	private Long id;
	private String name;
	private Double price;
	private Integer num;
}
