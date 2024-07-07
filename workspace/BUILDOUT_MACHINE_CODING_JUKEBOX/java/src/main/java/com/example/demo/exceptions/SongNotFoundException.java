package com.example.demo.exceptions;

public class SongNotFoundException extends RuntimeException{
 
     public SongNotFoundException(){
        super();
     }

     public SongNotFoundException(String msg){
        super(msg);
     }
     
}
