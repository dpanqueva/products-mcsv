package com.dieva.products.mcsv.infrastructure.controllers;


import com.dieva.products.mcsv.application.service.ProductService;
import com.dieva.products.mcsv.domain.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.created(URI.create("/api/v1/products")).body(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) throws InterruptedException {
        errorsCircuitBreaker(id);
        return ResponseEntity.ok(productService.getProductById(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
        return ResponseEntity.created(URI.create("/api/v1/products")).body(productService.updateProduct(product, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }


    private void errorsCircuitBreaker(Long id) throws InterruptedException {
        if (id.equals(10L)) {
            throw new IllegalStateException("Product not found");
        }
        if (id.equals(2L)) {
            TimeUnit.SECONDS.sleep(3L);
        }
    }
}
