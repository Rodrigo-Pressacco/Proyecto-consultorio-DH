package com.dh.clinica.controller;


import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    private final PacienteService pacienteService;
    private final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody PacienteDto p) throws SaveException {
            PacienteDto pacienteInsertado = pacienteService.save(p);
            return   ResponseEntity.ok(pacienteInsertado);
    }

    @GetMapping("")
    public ResponseEntity<List<PacienteDto>> consultarTodos(){
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws FindException {
            PacienteDto pacienteInsertado = pacienteService.buscar(id);
            return ResponseEntity.ok(pacienteInsertado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id) throws DeleteException {
            pacienteService.borrar(id);
            return ResponseEntity.ok("Eliminaste al paciente");
    }

    @PutMapping("")
    public ResponseEntity<?> actualizar(@RequestBody PacienteDto p) throws UpdateException {
            PacienteDto pacienteInsertado = pacienteService.actualizar(p);
            return ResponseEntity.ok(pacienteInsertado);
    }

//    @ExceptionHandler({PacienteException.class})
//    public ResponseEntity<?> procesarException(PacienteException ex){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

}
