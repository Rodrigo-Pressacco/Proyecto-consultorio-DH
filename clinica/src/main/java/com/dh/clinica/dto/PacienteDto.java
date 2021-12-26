package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class PacienteDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDateTime fechaIngreso;
    private DomicilioDto domicilio;

    public PacienteDto(){

    }

    public PacienteDto(String nombre, String apellido, String dni, LocalDateTime fechaIngreso, DomicilioDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteDto(Paciente paciente){
        id = paciente.getId();
        nombre = paciente.getNombre();
        apellido = paciente.getApellido();
        dni = paciente.getDni();
        fechaIngreso = paciente.getFechaIngreso();
        domicilio = new DomicilioDto(paciente.getDomicilio());
    }

    public Paciente toEntity(){
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setDni(dni);
        entity.setFechaIngreso(fechaIngreso);
        entity.setDomicilio(domicilio.toEntity());

        return entity;

    }
}
