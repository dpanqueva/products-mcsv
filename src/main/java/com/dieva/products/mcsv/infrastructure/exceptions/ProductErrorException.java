package com.dieva.products.mcsv.infrastructure.exceptions;

public class ProductErrorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductErrorException(String message) {
        super(message);
    }
}
