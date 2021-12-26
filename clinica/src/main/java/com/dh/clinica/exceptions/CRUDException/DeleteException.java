package com.dh.clinica.exceptions.CRUDException;

import com.dh.clinica.exceptions.ServiceException;

public class DeleteException extends ServiceException {
    public DeleteException(String message) {
        super(message);
    }
}
