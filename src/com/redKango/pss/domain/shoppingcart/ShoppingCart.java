package com.redKango.pss.domain.shoppingcart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 购物车对象
@Data
@AllArgsConstructor

@NoArgsConstructor
public class ShoppingCart {
	private List<CartItem> items = new ArrayList<>();

	// 增加商品
	public void save(CartItem item) {
		// 如果要添加的商品已经在购物车存在，就只要修改他的数量
		// 反之，就添加一个新的元素
		for (CartItem cartItem : items) {
			if (item.getId().equals(cartItem.getId())) {
				cartItem.setNum(cartItem.getNum() + item.getNum());
				return;
			}
		}
		items.add(item);
	}

	// 删除商品
	public void delete(Long id) {
		// 删除数列元素不能用foreach
		Iterator<CartItem> iterator = items.iterator();
		while (iterator.hasNext()) {
			CartItem item = iterator.next();
			if (item.getId().equals(id)) {
				iterator.remove();
			}
		}
	}

	// 获取商品总价
	public Double getTotalPrice() {
		Double totalPrice = 0.0;
		for (CartItem item : items) {
			totalPrice += item.getPrice() * item.getNum();
		}
		return totalPrice;
	}
}
