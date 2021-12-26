package com.dh.clinica.exceptions.CRUDException;

import com.dh.clinica.exceptions.ServiceException;

public class UpdateException extends ServiceException {

    public UpdateException(String message) {
        super(message);
    }
}
