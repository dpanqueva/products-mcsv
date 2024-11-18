package com.dieva.products.mcsv.domain.port.out;


import com.dieva.products.mcsv.infrastructure.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> getProducts();

    Optional<Product> getProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Product product, Long id);

    void deleteProductById(Long id);
}
