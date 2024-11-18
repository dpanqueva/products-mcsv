package com.dieva.products.mcsv.infrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ProductErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        printError("An error occurred: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("An error occurred, please try again");
    }

    @ExceptionHandler(ProductErrorException.class)
    public ResponseEntity<Object> handleProductErrorDeleteException(Exception ex) {
        printError("An error occurred deleted: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("An error occurred, please try deleting again");
    }

    private void printError(String message, String messageExceptionError){
        log.error(message, messageExceptionError);
    }


}
