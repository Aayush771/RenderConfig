package com.crio.jukebox.Exception;

public class PlayListNotFoundException extends RuntimeException{
      public PlayListNotFoundException(){
        super();
      }
      public PlayListNotFoundException(String msg){
        super(msg);
      }
}