package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OdontologoDto implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(Odontologo o){
        id = o.getId();
        nombre = o.getNombre();
        apellido = o.getApellido();
        matricula = o.getMatricula();
    }

    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);

        return entity;
    }



}
