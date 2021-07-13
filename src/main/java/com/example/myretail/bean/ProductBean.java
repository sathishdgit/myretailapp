package com.example.myretail.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="myretail")
public class ProductBean {

	@Id
	private int prodId;
	private String prodName;
	
	public ProductBean(int prodId, String prodName) {
		this.prodId = prodId;
		this.prodName = prodName;
	}
	
	/**
	 * @return the prodId
	 */
	public int getProdId() {
		return prodId;
	}
	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	
}
