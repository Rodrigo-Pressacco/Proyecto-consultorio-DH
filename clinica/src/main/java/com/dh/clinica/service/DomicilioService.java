package com.dh.clinica.service;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.repository.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    private final DomicilioRepository domicilioRepository;
    private final Logger logger = Logger.getLogger(DomicilioService.class);

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public DomicilioDto save(DomicilioDto d){
        logger.info("Ejecutando el guardado de un nuevo Domicilio");
        domicilioRepository.save(d.toEntity());
        return d;
    }

    public List<DomicilioDto> obtenerTodos(){
        logger.info("Ejecutando el  metodo para traer a todos los Domicilios");

        List<DomicilioDto> domicilios = new ArrayList<>();

        for(Domicilio d: domicilioRepository.findAll()){
            domicilios.add(new DomicilioDto(d));
        }
        return domicilios;
    }

    public void borrar(Integer id){
        logger.info("Ejecutando el  metodo de borrado de un domicilio");
        domicilioRepository.deleteById(id);
    }

    public DomicilioDto buscar(Integer  id){
        logger.info("Ejecutando el  metodo para traer al domicilio por Id");
        return new DomicilioDto(domicilioRepository.findById(id).get());
    }

    public DomicilioDto actualizar(DomicilioDto d){
        Domicilio domicilio= this.domicilioRepository.findAll().stream().filter(odon->odon.getId().equals(d.getId())).findFirst().get();

        DomicilioDto domicilioDto = new DomicilioDto(domicilio);

        domicilioDto.setProvincia(d.getProvincia());
        domicilioDto.setCalle(d.getCalle());
        domicilioDto.setLocalidad(d.getLocalidad());
        domicilioDto.setNumero(d.getNumero());
        domicilioRepository.save(domicilioDto.toEntity());

        logger.info("Ejecutando el  metodo de actualizado de un domocilio");

        return domicilioDto;
    }



}