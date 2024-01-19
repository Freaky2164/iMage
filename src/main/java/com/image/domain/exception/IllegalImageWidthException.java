/*
 * ImageDimensionException.java
 *
 * created at 2024-01-15 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.exception;


public class IllegalImageWidthException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public IllegalImageWidthException(String message)
    {
        super(message);
    }
}
