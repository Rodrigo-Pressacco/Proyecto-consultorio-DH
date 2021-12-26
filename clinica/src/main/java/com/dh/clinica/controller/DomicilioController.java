package com.dh.clinica.controller;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {


    private final DomicilioService domicilioService;

    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    ////////

    @PostMapping("")
    public ResponseEntity<DomicilioDto> crear(@RequestBody DomicilioDto p){

        if(p==null){

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(domicilioService.save(p));
    }

    @GetMapping("")
    public ResponseEntity<List<DomicilioDto>> consultarTodos(){
        return ResponseEntity.ok(domicilioService.obtenerTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id){

        if (domicilioService.buscar(id)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        domicilioService.borrar(id);
        return ResponseEntity.ok("Eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioDto> buscarPorId(@PathVariable Integer id){

        if (domicilioService.buscar(id)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(domicilioService.buscar(id));
    }

    @PutMapping("")
    public ResponseEntity<DomicilioDto> actualizar(@RequestBody DomicilioDto p){

        if (p.getId() == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        domicilioService.actualizar(p);
        return ResponseEntity.ok(domicilioService.actualizar(p));
    }



}
