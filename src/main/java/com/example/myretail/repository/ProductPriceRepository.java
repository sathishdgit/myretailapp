package com.example.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.myretail.bean.ProductPrice;

public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer>{

}
