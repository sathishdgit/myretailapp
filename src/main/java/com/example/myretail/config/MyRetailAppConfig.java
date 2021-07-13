package com.example.myretail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is an configuration class
 * @author Sathish kumar Damodaran
 *
 */
@Configuration
@EnableSwagger2
public class MyRetailAppConfig {

	@Value("${redsky.url}")
	private String redSkyUrl;
	
	 /**
     * 
     * @return
     */
    @Bean("restTemplate")
    public RestTemplate restTemplate() {

    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate;
    }
    
    
	/**
	 * Instantiate the objectMapper
	 * @return
	 */
    @Bean
    public ObjectMapper objectMapper () {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		return objectMapper;
	}


	/**
	 * @return the redSkyUrl
	 */
	public String getRedSkyUrl() {
		return redSkyUrl;
	}


	/**
	 * @param redSkyUrl the redSkyUrl to set
	 */
	public void setRedSkyUrl(String redSkyUrl) {
		this.redSkyUrl = redSkyUrl;
	}
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    
}
