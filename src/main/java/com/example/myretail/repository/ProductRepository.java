package com.example.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.myretail.bean.ProductBean;

public interface ProductRepository extends MongoRepository<ProductBean, Integer>{

}
