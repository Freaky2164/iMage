package com.image.domain.exception;


/**
 * Exception for illegal tile creation
 */
public class IllegalTileException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public IllegalTileException(String message)
    {
        super(message);
    }
}
