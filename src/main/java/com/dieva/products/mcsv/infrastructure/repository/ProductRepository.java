package com.dieva.products.mcsv.infrastructure.repository;

import com.dieva.products.mcsv.infrastructure.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
