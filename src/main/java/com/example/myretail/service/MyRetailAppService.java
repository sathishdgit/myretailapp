package com.example.myretail.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.example.myretail.bean.PriceValidation;
import com.example.myretail.bean.ProdCurrentPrice;
import com.example.myretail.bean.ProductCurrentPrice;
import com.example.myretail.bean.ProductPrice;
import com.example.myretail.bean.ProductResponse;
import com.example.myretail.bean.RedSkyProdDetails;
import com.example.myretail.config.MyRetailAppConfig;
import com.example.myretail.helper.MyRetailAppHelper;
import com.example.myretail.repository.ProductPriceRepoImpl;

/**
 * This class is to execute the business logic and call repository class for DB operation or interact with external system
 * @author Sathish kumar Damodaran
 *
 */
@Service
public class MyRetailAppService {

	@Autowired
	@Qualifier("restTemplate")
	RestTemplate restTemplate;
	
	@Autowired
	MyRetailAppConfig myRetailAppConfig;
	
	@Autowired
	private ProductPriceRepoImpl prodPriceRepoImpl;
	
	@Autowired
	private MyRetailAppHelper myRetailAppHelper;
	
	@Autowired
	private Validator validator;
	
	/**
	 * Call Redsky end point to get the Products for the given productId
	 * @param productId
	 * @return
	 */
	public ProductResponse getProductDetails(int productId) {
		
		// create request entity
		RequestEntity<String> reqEntity = null;
		ProductResponse prodResp = new ProductResponse();
		
		try {
			reqEntity = new RequestEntity<String>(HttpMethod.GET, new URI(myRetailAppConfig.getRedSkyUrl()));
		} catch (URISyntaxException e) {
			// log error
			return null;
		}
		
		prodResp = new ProductResponse();
		
		ResponseEntity<String> respEntity = null;
		
		try {
			respEntity = restTemplate.exchange(reqEntity, String.class);
		} catch (Exception e) {
			//log error
			return null;
		}

		if(respEntity != null && respEntity.getBody() != null) {
			
			RedSkyProdDetails redSkyProd = myRetailAppHelper.convertString(respEntity.getBody().toString());
			
			try {
				
				if(productId == Integer.valueOf(redSkyProd.getProduct().getPromiseNetwork().getProductId())) {
					
					prodResp.setId(Integer.valueOf(redSkyProd.getProduct().getPromiseNetwork().getProductId()));
					prodResp.setName(redSkyProd.getProduct().getItem().getProductDesc().getTitle());
					
					ProdCurrentPrice prodCurrPrice = prodPriceRepoImpl.getProductPrice(productId);
					
					if(prodCurrPrice != null) {
						prodResp.setProdCurrentPrice(prodCurrPrice);
					}
					
				} else {
					prodResp.setMessage("The "+productId+" does not exists");
				}
			} catch(Exception e) {
				// log error
				return null;
			}
		} else {
			prodResp.setMessage("The "+productId+" does not exists");
		}
		
		return prodResp;
	}
	
	/**
	 * Add the product price details for the given productId into DB
	 * @param prodCurrentPrice
	 * @return
	 */
	public boolean insertOrUpdateProductPrice(int productId, ProductCurrentPrice prodCurrentPrice) {
		
		Set<ConstraintViolation<ProductCurrentPrice>> constrainVioations = setupValidatorInstance().validate(prodCurrentPrice, PriceValidation.class);
		
		if(CollectionUtils.isEmpty(constrainVioations)) {
		
			ProductPrice prodPrice = new ProductPrice();
			prodPrice.setProductId(productId);
			prodPrice.setCurrentPrice(prodCurrentPrice.getCurrentPrice());
			return prodPriceRepoImpl.saveProdPrice(prodPrice);
			
		} else {
			
			for(ConstraintViolation<ProductCurrentPrice> constraintProdPrice : constrainVioations) {
				System.out.println("Message : "+constraintProdPrice.getMessage());
			}
			return false;
		}
	}
	
    private Validator setupValidatorInstance() {
    	validator = Validation.buildDefaultValidatorFactory().getValidator();
    	return validator;
    }

	
}
