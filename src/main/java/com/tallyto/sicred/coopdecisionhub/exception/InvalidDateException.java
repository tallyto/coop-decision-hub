package com.tallyto.sicred.coopdecisionhub.exception;

public class InvalidDateException extends ValidationException
{
    public InvalidDateException(String message)
    {
        super(message);
    }

    public InvalidDateException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
