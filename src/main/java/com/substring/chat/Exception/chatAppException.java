package com.substring.chat.Exception;

public class chatAppException extends RuntimeException {
   
    public chatAppException(String message){
        super(message);
    }
    public chatAppException(String mesaage,Throwable cause){
        super(mesaage,cause);
    }
}
