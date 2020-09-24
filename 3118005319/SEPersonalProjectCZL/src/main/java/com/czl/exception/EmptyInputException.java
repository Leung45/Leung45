package com.czl.exception;

/**
 * @author CZL
 * @date 2020 09 24
 */
public class EmptyInputException extends Exception {
    public EmptyInputException(){
        super();
    }

    /**
     * 空输入异常
     * @param message
     */
    public EmptyInputException(String message){
        super(message);
    }
}
