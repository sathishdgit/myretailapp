package com.example.myretail.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiParam;

@Document(collection="product_price")
@JsonInclude(value=Include.NON_DEFAULT)
public class ProductPrice {
	
	@Id
	@JsonProperty("product_id")
	@ApiParam(value = "Value of ProductId", required = true)
	private int productId;
	
	@Field("current_price")
	@JsonProperty("current_price")
	@ApiParam(value = "The price object", required = true)
	private CurrentPrice currentPrice;

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the currentPrice
	 */
	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice the currentPrice to set
	 */
	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	
}
