package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    //
    public TurnoDto save(TurnoDto t, Integer idPaciente, Integer idOdontologo) throws SaveException, FindException {
        logger.info("Ejecutando el guardado de un nuevo Turno");
        t.setPaciente(pacienteService.buscar(idPaciente));
        t.setOdontologo(odontologoService.buscar(idOdontologo));

        if (verificacionDeExistenciaDeTurno(t.getOdontologo().getId(),t.getPaciente().getId(),t.getFecha()).isEmpty()){
            turnoRepository.save(t.toEntity());
            return t;
        }
        logger.error("Fallo al guardar el turno");
        throw new SaveException("No se pudo guardar el turno");
    }

    public List<TurnoDto> obtenerTodos() {
        logger.info("Ejecutando el  metodo para traer a todos los Turnos");

        List<TurnoDto> turnoDtos = new  ArrayList<>();
        for(Turno t: turnoRepository.findAll()){
            turnoDtos.add(new TurnoDto(t));
        }
        return turnoDtos;
    }

    public TurnoDto buscar(Integer id) throws FindException {
        logger.info("Ejecutando el  metodo para traer al turno por Id");
        Turno t = turnoRepository.findById(id).orElse(null);
        if (t==null) {
            logger.error("Fallo al buscar el turno con este id"+ id);
            throw new FindException("No se existe el turno por este id " + id);
        }
        return new TurnoDto(turnoRepository.getById(id));
    }

    public void borrar(Integer id) throws DeleteException {
        logger.info("Ejecutando el  metodo de borrado de un turno");
        Turno t = turnoRepository.findById(id).orElse(null);
        if (t==null) {
            logger.error("Fallo al borrar el turno el id no existe");
            throw new DeleteException("El Id no existe");
        }
        turnoRepository.deleteById(id);
    }

    public TurnoDto actualizar(TurnoDto t) throws UpdateException {
        Turno turno = this.turnoRepository.findAll().stream().filter(t1 -> t1.getId().equals(t.getId())).findFirst().orElse(null);
        if (turno==null) {
            logger.error("Fallo al actualizar el turno");
            throw new UpdateException("Ese Turno existe y no se pudo actualizar");
        }
        PacienteDto paciente = this.pacienteService.obtenerTodos().stream().filter(p1 -> p1.getId().equals(t.getPaciente().getId())).findFirst().get();
        OdontologoDto odontologo = this.odontologoService.obtenerTodos().stream().filter(o1 -> o1.getId().equals(t.getOdontologo().getId())).findFirst().get();
        TurnoDto turnoDto = new TurnoDto(turno);

        turnoDto.setFecha(t.getFecha());
        turnoDto.setPaciente(paciente);
        turnoDto.setOdontologo(odontologo);
        turnoRepository.save(turnoDto.toEntity());

        logger.info("Ejecutando el  metodo de actualizado de un Turno");
        return turnoDto;
    }

    //
    private List<TurnoDto> verificacionDeExistenciaDeTurno(Integer idOdontologo, Integer idPaciente, LocalDateTime fecha){
        List<TurnoDto> turnoDtos = new  ArrayList<>();
        for(Turno t: turnoRepository.findAll()) {
            if (t.getFecha().equals(fecha)) {
                if (t.getPaciente().getId().equals(idPaciente) || t.getOdontologo().getId().equals(idOdontologo)) {
                    turnoDtos.add(new TurnoDto(t));
                }
            }
        }
        return turnoDtos;
    }

    //
    public List<TurnoDto> buscarLosTurnosPorSemana(){
        LocalDateTime dateTime = LocalDateTime.now().plusDays(7);
        List<Turno> turnoList = turnoRepository.findAll().stream().filter(turno -> turno.getFecha().isBefore(dateTime)&&turno.getFecha().isAfter(dateTime)).collect(Collectors.toList());
        List<TurnoDto> turnoDtos = new ArrayList<>();
        for (Turno t: turnoList)
            turnoDtos.add(new TurnoDto(t));
        return turnoDtos;
    }

}
