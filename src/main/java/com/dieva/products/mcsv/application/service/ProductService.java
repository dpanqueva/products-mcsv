package com.dieva.products.mcsv.application.service;

import com.dieva.products.mcsv.domain.model.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getProducts();

    Optional<ProductDto> getProductById(Long id);

    ProductDto saveProduct(ProductDto product);

    ProductDto updateProduct(ProductDto product, Long id);

    void deleteProductById(Long id);
}
