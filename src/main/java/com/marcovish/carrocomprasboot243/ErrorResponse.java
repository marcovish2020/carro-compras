package com.marcovish.carrocomprasboot243;


import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class ErrorResponse extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    private String message;
    private Integer status;

    public ErrorResponse(){

    }
    public ErrorResponse(String m, Integer s ){
        this.message = m;
        this.status = s;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    /*
    public String geMessage(){ return this.message;}
    public void setMessage(String message){ this.message = message;}

    public Integer getStatus(){ return this.status;}
    public void setStatus(Integer status){ this.status = status;}
    */
}
