package com.mgt.DixitFurnitures.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FurnitureUtils {

    private FurnitureUtils(){
    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\" Message \" : \""+responseMessage+"}",httpStatus);
    }
}
