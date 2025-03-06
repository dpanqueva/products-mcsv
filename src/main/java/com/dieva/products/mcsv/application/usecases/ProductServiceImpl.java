package com.dieva.products.mcsv.application.usecases;


import com.dieva.products.mcsv.application.service.ProductService;
import com.dieva.libs.mcsv.commons.domain.model.ProductDto;
import com.dieva.products.mcsv.domain.port.out.ProductRepositoryPort;
import com.dieva.products.mcsv.infrastructure.entities.Product;
import com.dieva.products.mcsv.infrastructure.exceptions.ProductErrorException;
import com.dieva.products.mcsv.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryPort productRepositoryPort;

    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepositoryPort.getProducts();
        return productMapper.toDto(products);
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        Optional<Product> productOptional = productRepositoryPort.getProductById(id);
        if (productOptional.isPresent()) {
            return Optional.of(productMapper.toEntity(productOptional.get()));
        }
        throw new ProductErrorException("Product not existing");
    }

    @Override
    public ProductDto saveProduct(ProductDto product) {
        Product productEntity = productMapper.toDto(product);
        Product productSaved = productRepositoryPort.saveProduct(productEntity);
        return productMapper.toEntity(productSaved);
    }

    @Override
    public ProductDto updateProduct(ProductDto product, Long id) {
        Optional<Product> productOptional = productRepositoryPort.getProductById(id);
        if (productOptional.isPresent()) {

            Product productUpdated = productRepositoryPort.updateProduct(productOptional.get().toBuilder().price(product.getPrice()).name(product.getName()).build(), id);
            return productMapper.toEntity(productUpdated);
        }
        throw new ProductErrorException("Product not updated");
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> productOptional = productRepositoryPort.getProductById(id);
        if (productOptional.isEmpty()) {
            throw new ProductErrorException("Product not found");
        }
        productRepositoryPort.deleteProductById(id);
    }
}
