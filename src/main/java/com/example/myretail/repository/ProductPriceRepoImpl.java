package com.example.myretail.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.myretail.bean.ProdCurrentPrice;
import com.example.myretail.bean.ProductPrice;

/**
 * This class is to interact with Repository for DB operations
 * @author Sathish kumar Damodaran
 *
 */
@Component
public class ProductPriceRepoImpl {

	@Autowired
	private ProductPriceRepository prodPriceRepo;
	
	/**
	 * This method is to retrieve the product price details for the given productId
	 * @param productId
	 * @return
	 */
	public ProdCurrentPrice getProductPrice(int productId) {

		ProdCurrentPrice prodCurrPrice = null;
		
		try {
			
			Optional<ProductPrice> prodPriceDetails = prodPriceRepo.findById(productId);

			if(prodPriceDetails.isPresent()) {

				ProductPrice prodPrice = prodPriceDetails.get();

				if(prodPrice.getCurrentPrice() != null) {
					prodCurrPrice = new ProdCurrentPrice();
					prodCurrPrice.setValue(prodPrice.getCurrentPrice().getPriceValue());
					prodCurrPrice.setCurrencyCode(prodPrice.getCurrentPrice().getCurrencyCode());

				}
			}
		} catch(Exception e) {
			// log error
			return null;
		}

		return prodCurrPrice;
	}
	
	/**
	 * This method is to insert or update the product price for the given product id
	 * @param prodPrice
	 * @return
	 */
	public boolean saveProdPrice(ProductPrice prodPrice) {
		
		try {
			prodPriceRepo.save(prodPrice);
			return true;
		} catch(Exception e) {
			// log error
		}
		
		return false;
	}
	
}
