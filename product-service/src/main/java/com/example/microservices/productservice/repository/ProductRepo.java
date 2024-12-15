package com.example.microservices.productservice.repository;

import com.example.microservices.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends MongoRepository<Product, String> {
}
