package com.khozane.coindispenser.core;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String errorMessage){
        super(errorMessage);
    }
}
