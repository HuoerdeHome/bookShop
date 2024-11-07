package com.briup.springbootwebservelet.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 14409
 */

public class CustomeException extends RuntimeException{
    public CustomeException(){

    }
    public CustomeException(String massage){
        super(massage);
    }
}
