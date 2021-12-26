package com.dh.clinica.service;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.persistence.entities.Domicilio;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
class PacienteServiceTest {

 /*   @Autowired
    private PacienteService pacienteService;

    public void cargarDatos() throws SaveException {
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = pacienteService.save(new PacienteDto("Santiago", "Paz", "88888888", null, new DomicilioDto(domicilio)));
        Domicilio domicilio1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        PacienteDto p1 = pacienteService.save(new PacienteDto("Micaela", "Perez", "99999999", null, new DomicilioDto(domicilio1)));
    }

    @Test
    public void deberiaTraerElPacienteBuscado() throws SaveException, FindException {
        //DADOS
        this.cargarDatos();
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        PacienteDto p = pacienteService.save(new PacienteDto("Tomas", "Pereyra", "12345678", null, new DomicilioDto(domicilio)));

        //CUANDO
        Object respuesta = pacienteService.buscar(p.getId());

        //ENTONCES
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void eliminarPacienteTest() throws DeleteException {
        pacienteService.borrar(3);
        List<PacienteDto> t = pacienteService.obtenerTodos();
        Assert.assertTrue(t.stream().findFirst().get().getId()!=2);

    }

    @Test
    public void traerTodos() {
        List<PacienteDto> pacientes = pacienteService.obtenerTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }
    */


}