package com.example.myretail.helper;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.myretail.bean.ProductCurrentPrice;
import com.example.myretail.bean.RedSkyProdDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is an helper class which is having some utility methods
 * @author Sathish kumar Damodaran
 *
 */
@Component
public class MyRetailAppHelper {
	
	@Autowired
	ObjectMapper objectMapper;
	
	/**
	 * This method is to convert Json String to RedSkyProdDetails object
	 * @param jsonValue
	 * @return
	 */
	public RedSkyProdDetails convertString(String jsonValue) {
		
		RedSkyProdDetails redSkyProd = null;
		
		try {
			redSkyProd = objectMapper.readValue(jsonValue, RedSkyProdDetails.class);
		} catch (JsonProcessingException e) {
			// Log Exception
		}
		return redSkyProd;
	}
	
	/**
	 * Validate the input fields
	 * @param prodCurrentPrice
	 * @return
	 */
	public boolean isValidRequest(ProductCurrentPrice prodCurrentPrice) {
		
		if(prodCurrentPrice == null || prodCurrentPrice.getCurrentPrice() == null) {
			return false;
		}
		
		if(!StringUtils.hasText(prodCurrentPrice.getCurrentPrice().getCurrencyCode())) {
			return false;
		}
		
		return true;
	}
	

}
