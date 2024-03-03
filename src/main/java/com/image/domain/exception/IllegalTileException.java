/*
 * IllegalTileException.java
 *
 * created at 2024-01-15 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
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
