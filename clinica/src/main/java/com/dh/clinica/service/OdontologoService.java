package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.exceptions.CRUDException.UpdateException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OdontologoService {


    private final OdontologoRepository odontologoRepository;
    private final Logger logger = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public OdontologoDto save(OdontologoDto odontologo) throws SaveException {

        if (odontologo==null) {
            logger.error("Fallo al guardar el odontologo");
            throw new SaveException("No se pudo guardar el Odontologo ");
        }
        logger.info("Ejecutando el guardado de un nuevo Odontologo");
        odontologoRepository.save(odontologo.toEntity());
        return odontologo;
    }

    public List<OdontologoDto> obtenerTodos(){
        logger.info("Ejecutando el  metodo para traer a todos los Odontologos");
        List<OdontologoDto> odontologoDto = new ArrayList<>();

        for(Odontologo o: odontologoRepository.findAll()){
            odontologoDto.add(new OdontologoDto(o));
        }
        return odontologoDto;
    }

    public OdontologoDto buscar(Integer  id) throws FindException {
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        if (o==null) {
            logger.error("Fallo al Buscar el odontologo");
            throw new FindException("No se encontro el odontologo con el id " + id);
        }
        logger.info("Ejecutando el  metodo para traer al Odontologo por Id");
         return new OdontologoDto(o);
    }

    public void borrar(Integer id) throws DeleteException {
        logger.info("Ejecutando el  metodo de borrado de un Odontologo");
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        if (o==null) {
            logger.error("Fallo al borrar el odontologo");
            throw new DeleteException("No existe Odontologo con ese id " + id);
        }
        odontologoRepository.deleteById(id);
    }

    public OdontologoDto actualizar(OdontologoDto o) throws UpdateException {
        Odontologo d= this.odontologoRepository.findAll().stream().filter(odon->odon.getId().equals(o.getId())).findFirst().orElse(null);
        if (d==null) {
            logger.error("Fallo al actualizar el odontologo");
            throw new UpdateException("No se pudo actualizar el odontologo");
        }

        OdontologoDto odontologo = new OdontologoDto(d);

        odontologo.setNombre(o.getNombre());
        odontologo.setApellido(o.getApellido());
        odontologo.setMatricula(o.getMatricula());
        odontologoRepository.save(odontologo.toEntity());

        logger.info("Ejecutando el  metodo de actualizado de un paciente");

        return odontologo;
    }

    /// Metodos Usando Querys

    public List<OdontologoDto> buscarPorNombre(String nombre){
        List<Odontologo> u = odontologoRepository.findAll().stream().filter(o->o.getNombre().equals(nombre)).collect(Collectors.toList());
        List<OdontologoDto> odontologoDto = new ArrayList<>();
        for (Odontologo o: u){
            odontologoDto.add(new OdontologoDto(o));
        }
        return odontologoDto;
    }

    public List<OdontologoDto> buscarPorNombreYApellido(String nombre,String apellido){
        List<Odontologo> u = odontologoRepository.buscar(nombre,apellido).get();
        List<OdontologoDto> odontologoDto = new ArrayList<>();
        for (Odontologo o: u){
            odontologoDto.add(new OdontologoDto(o));
        }
        return odontologoDto;
    }

}
