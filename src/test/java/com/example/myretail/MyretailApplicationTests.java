package com.example.myretail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.myretail.bean.CurrentPrice;
import com.example.myretail.bean.ProductCurrentPrice;
import com.example.myretail.bean.ProductResponse;
import com.example.myretail.controller.MyRetailAppController;

/**
 * This class is to test retrieve products and update product price
 * @author Sathish kumar Damodaran
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class MyretailApplicationTests {
	
	@Autowired
	MyRetailAppController retailAppController;

	@Test
	void testGetProductsForIncorrectProducts() {
		int productId = 12345;
		String message = "The 12345 does not exists";
		ResponseEntity<ProductResponse> respEntity = retailAppController.getProductDetails(productId);
		assertEquals(message, respEntity.getBody().getMessage());
	}
	
	@Test
	void testGetProductsForValidProduct() {
		int productId = 13860428;
		String title = "The Big Lebowski (Blu-ray)";
		ResponseEntity<ProductResponse> respEntity = retailAppController.getProductDetails(productId);
		assertEquals(title, respEntity.getBody().getName());
	}
	
	@Test
	void testUpdateProductsPrice() {
		
		int productId = 12345678;
		BigDecimal price = BigDecimal.valueOf(12.11);
		String currencyCode = "USD";
		
		ProductCurrentPrice prodCurrentPrice = new ProductCurrentPrice();
		CurrentPrice currentPrice = new CurrentPrice();

		currentPrice.setPriceValue(price);
		currentPrice.setCurrencyCode(currencyCode);
		prodCurrentPrice.setCurrentPrice(currentPrice);
		
		ResponseEntity<String> respEntity = retailAppController.updateProductPrice(productId, prodCurrentPrice);
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
	}

}
