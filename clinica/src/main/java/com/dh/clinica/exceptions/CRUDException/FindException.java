package com.dh.clinica.exceptions.CRUDException;

import com.dh.clinica.exceptions.ServiceException;

public class FindException extends ServiceException {
    public FindException(String message) {
        super(message);
    }
}
