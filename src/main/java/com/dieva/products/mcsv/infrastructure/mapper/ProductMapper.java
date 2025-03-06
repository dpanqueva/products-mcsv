package com.dieva.products.mcsv.infrastructure.mapper;

import com.dieva.libs.mcsv.commons.domain.model.ProductDto;
import com.dieva.products.mcsv.infrastructure.entities.Product;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductDto> {

    @Override
    ProductDto toEntity(Product dto);

    @Override
    Product toDto(ProductDto entity);

    @Override
    List<ProductDto> toDto(List<Product> entity);

    @Override
    List<Product> toEntity(List<ProductDto> dto);

    default LocalDateTime toLocalDateTime(LocalDateTime createdAt){
        if(Objects.isNull(createdAt)){
            return LocalDateTime.now(ZoneOffset.UTC);
        }
        return createdAt;

    }

}
