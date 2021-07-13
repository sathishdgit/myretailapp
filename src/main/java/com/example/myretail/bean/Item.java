package com.example.myretail.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value=Include.NON_DEFAULT)
public class Item {

	@JsonProperty("product_description")
	private ProductDescription productDesc;

	/**
	 * @return the productDesc
	 */
	public ProductDescription getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(ProductDescription productDesc) {
		this.productDesc = productDesc;
	}
	
	
}
