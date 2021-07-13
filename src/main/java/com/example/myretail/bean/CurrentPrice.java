package com.example.myretail.bean;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value=Include.NON_DEFAULT)
public class CurrentPrice {
	
	@Field("value")
	@JsonProperty("value")
	@NotNull(message = "The price value should not be empty")
	private BigDecimal priceValue;
	
	@Field("currency_code")
	@JsonProperty("currency_code")
	@NotEmpty(message = "The current code should not be empty")
	private String currencyCode;

	/**
	 * @return the priceValue
	 */
	public BigDecimal getPriceValue() {
		return priceValue;
	}

	/**
	 * @param priceValue the priceValue to set
	 */
	public void setPriceValue(BigDecimal priceValue) {
		this.priceValue = priceValue;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
