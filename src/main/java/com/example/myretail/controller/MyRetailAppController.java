/**
 * 
 */
package com.example.myretail.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myretail.bean.ProductCurrentPrice;
import com.example.myretail.bean.ProductResponse;
import com.example.myretail.helper.MyRetailAppHelper;
import com.example.myretail.service.MyRetailAppService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * This class is to interact with manager class for business logic
 * @author Sathish kumar Damodaran
 *
 */
@RestController
public class MyRetailAppController {

	@Autowired
	MyRetailAppService myRetailService;
	
	@Autowired
	private MyRetailAppHelper myRetailAppHelper;
	
	/**
	 * Retrieve the product details for the given productId
	 * @param productId
	 * @return
	 */
	@GetMapping("/products/{productId}")
	@ApiOperation(value="To retrieve the product name and price details")
	public ResponseEntity<ProductResponse> getProductDetails(
			@ApiParam(
				    type = "Integer",
				    value = "The productId value",
				    required = true)
			@PathVariable int productId) {
		
		ProductResponse prodResp = myRetailService.getProductDetails(productId);
		
		if(prodResp == null) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<>(prodResp, HttpStatus.OK);
	}
	
	/**
	 * Add or update the product price details into DB for the given productId
	 * @param productId
	 * @param productCurrentPrice
	 * @return
	 */
	@PutMapping("/products/{productId}")
	@ApiOperation(value="Update the product price for the given productId")
	public ResponseEntity<String> updateProductPrice(
			@ApiParam(
				    type = "Integer",
				    value = "The productId value",
				    required = true)
			@PathVariable int productId, 
			@ApiParam(
				    value = "The product price and currency code values",
				    required = true)
			@Valid @RequestBody ProductCurrentPrice productCurrentPrice) {
		
		boolean isValid = myRetailAppHelper.isValidRequest(productCurrentPrice);
		
		if(!isValid) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		boolean isUpdateSuccess = myRetailService.insertOrUpdateProductPrice(productId, productCurrentPrice);
		
		if(!isUpdateSuccess) {
			return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
