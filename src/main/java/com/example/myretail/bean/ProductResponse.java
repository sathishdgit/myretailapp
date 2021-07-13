package com.example.myretail.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_DEFAULT)
public class ProductResponse {
	
	private int id;
	
	private String name;
	
	@JsonProperty("current_price")
	private ProdCurrentPrice prodCurrentPrice;
	
	private String message;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prodCurrentPrice
	 */
	public ProdCurrentPrice getProdCurrentPrice() {
		return prodCurrentPrice;
	}

	/**
	 * @param prodCurrentPrice the prodCurrentPrice to set
	 */
	public void setProdCurrentPrice(ProdCurrentPrice prodCurrentPrice) {
		this.prodCurrentPrice = prodCurrentPrice;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
}
