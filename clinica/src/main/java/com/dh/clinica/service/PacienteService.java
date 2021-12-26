package com.dh.clinica.service;

import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService{

    private final PacienteRepository pacienteRepository;
    private final DomicilioService domicilioService;
    private final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioService domicilioService) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioService = domicilioService;
    }

    public PacienteDto save(PacienteDto p) throws SaveException {
        logger.info("Ejecutando el guardado de un nuevo Paciente");
        if (p==null) {
            logger.error("Fallo al guardar el paciente");
            throw new SaveException("No se pudo guardar el paciente ");
        }
        p.setFechaIngreso(LocalDateTime.now());
        p.setId(pacienteRepository.save(p.toEntity()).getId());
        return p;
    }

    public List<PacienteDto> obtenerTodos() {
        logger.info("Ejecutando el  metodo para traer a todos los Pacientes");
        List<PacienteDto> pacienteDto = new ArrayList<>();
        for (Paciente p: pacienteRepository.findAll()) {
            pacienteDto.add(new PacienteDto(p));
        }
        return pacienteDto;

    }

    public PacienteDto buscar(Integer id) throws FindException {
        Paciente p = pacienteRepository.findById(id).orElse(null);
        if (p==null) {
            logger.error("Fallo al buscar el paciente con id "+id);
            throw new FindException("No se encontro el paciente con el id " + id);
        }
        logger.info("Buscando al paciente con el Id"+ id);
        return new PacienteDto(pacienteRepository.findById(id).get());
    }

    public void borrar(Integer id) throws DeleteException {
        logger.info("Ejecutando el  metodo de borrado de un paciente");
        Paciente p = pacienteRepository.findById(id).orElse(null);
        if (p==null) {
            logger.error("Fallo al borrar el paciente");
            throw new DeleteException("No existe paciente con ese id");
        }
        pacienteRepository.deleteById(id);
    }

    public PacienteDto actualizar(PacienteDto p) throws UpdateException {
        Paciente paciente = this.pacienteRepository.findAll().stream().filter(pacien -> pacien.getId().equals(p.getId())).findFirst().orElse(null);
        if (paciente==null) {
            logger.error("Fallo al actualizar el paciente");
            throw new UpdateException("No se pudo actualizar el paciente");
        }

        PacienteDto pacienteDto = new PacienteDto(paciente);

        pacienteDto.setNombre(p.getNombre());
        pacienteDto.setApellido(p.getApellido());
        pacienteDto.setDni(p.getDni());
        pacienteDto.setFechaIngreso(p.getFechaIngreso());
        pacienteDto.setDomicilio(p.getDomicilio());
        pacienteRepository.save(pacienteDto.toEntity());

        logger.info("Ejecutando el  metodo de actualizado de un paciente");

        return p;
    }
}
