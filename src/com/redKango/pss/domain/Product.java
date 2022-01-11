package com.redKango.pss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class Product {
	@ID
	private Long id;
	@Column("Product Name")
	private String productName;
	@Column("Parent Id")
	private Long dir_id;
	@Column("Sale Price")
	private Double salePrice;
	@Column("Supplier Name")
	private String supplier;
	@Column("Brand Name")
	private String brand;
	@Column("Discount")
	private Double discount;
	@Column("Cost Price")
	private Double costPrice;
}
