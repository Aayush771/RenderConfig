package com.example.demo.Exceptions;

public class NoSuchCommandException extends Exception{

    NoSuchCommandException(String msg){
        super(msg);
    }

    public NoSuchCommandException(){}

}
