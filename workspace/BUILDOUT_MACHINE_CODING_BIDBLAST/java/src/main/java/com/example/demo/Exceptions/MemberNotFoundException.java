package com.example.demo.Exceptions;

public class MemberNotFoundException extends RuntimeException{
      public MemberNotFoundException(String msg){
        super(msg);
      }
}
