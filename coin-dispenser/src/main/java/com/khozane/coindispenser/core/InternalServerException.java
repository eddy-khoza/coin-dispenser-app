package com.khozane.coindispenser.core;

public class InternalServerException extends InternalError{
    public InternalServerException(String message){
        super(message);
    }
}
