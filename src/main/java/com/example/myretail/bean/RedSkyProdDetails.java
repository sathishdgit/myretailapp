/**
 * 
 */
package com.example.myretail.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ctsuser
 *
 */
@JsonInclude(value=Include.NON_DEFAULT)
public class RedSkyProdDetails {

	@JsonProperty("product")
	private Product product;

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
