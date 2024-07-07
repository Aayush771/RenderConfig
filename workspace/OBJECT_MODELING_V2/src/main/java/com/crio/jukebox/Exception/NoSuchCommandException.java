package com.crio.jukebox.Exception;

public class NoSuchCommandException extends RuntimeException{
    public NoSuchCommandException()
    {
     super();
    }
    public NoSuchCommandException(String msg)
    {
     super(msg);
    }
}
