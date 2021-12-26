package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

//  Esta hecho porque lo estaba usando para crear los domicilios pero ahora lo hace desde el paciente
//  Lo deje porque de esa forma lo podia verificar
    private final OdontologoService odontologoService;
    private final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody OdontologoDto o) throws SaveException {
            OdontologoDto odontologoInsertado = odontologoService.save(o);
            return ResponseEntity.ok(odontologoInsertado);
    }

    @GetMapping("")
    public ResponseEntity<List<?>> consultarTodos(){
        return ResponseEntity.ok(odontologoService.obtenerTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id) throws DeleteException {
            odontologoService.borrar(id);
            return   ResponseEntity.ok("Eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws FindException {
            OdontologoDto odontologoInsertado =  odontologoService.buscar(id);
            return ResponseEntity.ok(odontologoInsertado);
    }

    @PutMapping("")
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDto o) throws UpdateException{
            OdontologoDto odontologoInsertado =  odontologoService.actualizar(o);
            return ResponseEntity.ok(odontologoInsertado);
    }

    //Request usando Querys

    @GetMapping(params = "nombre")
    public ResponseEntity<List<OdontologoDto>> buscarPorNombre(@RequestParam String nombre){

        if (odontologoService.buscarPorNombre(nombre)== null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }

    @GetMapping(params = {"nombre","apellido"})
    public ResponseEntity<List<OdontologoDto>> buscarPorNombre(@RequestParam String nombre,String apellido){

        if (odontologoService.buscarPorNombreYApellido(nombre,apellido)== null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(odontologoService.buscarPorNombreYApellido(nombre,apellido));
    }


    
}
