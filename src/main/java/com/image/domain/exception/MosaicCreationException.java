/*
 * MosaicCreationException.java
 *
 * created at 2024-01-15 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.exception;


/**
 * Exception for errors during mosaic creation
 */
public class MosaicCreationException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public MosaicCreationException(String message)
    {
        super(message);
    }
}
