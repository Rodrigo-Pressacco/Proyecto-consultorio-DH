package com.dh.clinica.exceptions.CRUDException;

import com.dh.clinica.exceptions.ServiceException;

public class SaveException extends ServiceException {
    public SaveException(String message) {
        super(message);
    }
}
