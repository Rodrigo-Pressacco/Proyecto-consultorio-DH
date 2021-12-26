package com.dh.clinica.controller;

import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController{

    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody TurnoDto t) throws SaveException, FindException {
            TurnoDto turnoInsertado = turnoService.save(t, t.getPaciente().getId(),t.getOdontologo().getId());
            return ResponseEntity.ok(turnoInsertado);
    }

    @GetMapping("")
    public ResponseEntity<List<?>> consultarTodos(){
        return ResponseEntity.ok(turnoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws FindException{
        TurnoDto t = turnoService.buscar(id);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id) throws DeleteException {
        turnoService.borrar(id);
        return ResponseEntity.ok("Eliminado");
    }

    @PutMapping("")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto t) throws UpdateException{
        turnoService.actualizar(t);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/semanal")
    public ResponseEntity<?> buscarFechaMenorASieteDias(){
        return ResponseEntity.ok(turnoService.buscarLosTurnosPorSemana());
    }

}