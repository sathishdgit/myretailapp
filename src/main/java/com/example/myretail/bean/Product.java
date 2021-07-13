package com.example.myretail.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_DEFAULT)
public class Product {
	
	@JsonProperty("available_to_promise_network")
	private PromiseNetwork promiseNetwork;
	
	@JsonProperty("item")
	private Item item;

	/**
	 * @return the promiseNetwork
	 */
	public PromiseNetwork getPromiseNetwork() {
		return promiseNetwork;
	}

	/**
	 * @param promiseNetwork the promiseNetwork to set
	 */
	public void setPromiseNetwork(PromiseNetwork promiseNetwork) {
		this.promiseNetwork = promiseNetwork;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
}
