package com.dh.clinica.exceptions;

import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({FindException.class})
    public ResponseEntity<?> procesarExceptionFind(FindException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({DeleteException.class})
    public ResponseEntity<?> procesarExceptionDelete(DeleteException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({UpdateException.class})
    public ResponseEntity<?> procesarExceptionUpdate(UpdateException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({SaveException.class})
    public ResponseEntity<?> procesarExceptionSave(SaveException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
