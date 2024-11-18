package com.dieva.products.mcsv.domain.model;


import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;

}
