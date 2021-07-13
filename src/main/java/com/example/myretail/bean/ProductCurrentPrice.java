/**
 * 
 */
package com.example.myretail.bean;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ctsuser
 *
 */
@JsonInclude(value=Include.NON_DEFAULT)
public class ProductCurrentPrice {

	@JsonProperty("current_price")
	@NotNull(message = "The current price object should not be empty")
	private CurrentPrice currentPrice;

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
