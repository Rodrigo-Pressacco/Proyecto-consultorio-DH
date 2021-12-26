package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoDto {

    private Integer id;
    private LocalDateTime fecha;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public TurnoDto() {
    }

    public TurnoDto(Turno t) {
        id = t.getId();
        fecha = t.getFecha();
        paciente = new PacienteDto(t.getPaciente());
        odontologo = new OdontologoDto(t.getOdontologo());;
    }

    public Turno toEntity(){
        Turno entity = new Turno();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setPaciente(paciente.toEntity());
        entity.setOdontologo(odontologo.toEntity());

        return entity;
    }

}
