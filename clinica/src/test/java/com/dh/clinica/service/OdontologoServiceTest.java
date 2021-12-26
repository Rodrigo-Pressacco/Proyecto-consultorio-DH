package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.CRUDException.DeleteException;
import com.dh.clinica.exceptions.CRUDException.FindException;
import com.dh.clinica.exceptions.CRUDException.SaveException;
import com.dh.clinica.persistence.entities.Odontologo;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceTest {

/*    @Autowired
    private OdontologoService odontologoService;

    public void cargarDataSet() throws SaveException {
        Odontologo odontologo = new Odontologo("Santiago", "Paz", "3455647");
        this.odontologoService.save(new OdontologoDto(odontologo));
    }

    @Test
    void agregarOdontologo() throws SaveException {
        this.cargarDataSet();
        Odontologo odontologo = new Odontologo("Juan", "Ramirez", "348971960");
        this.odontologoService.save(new OdontologoDto(odontologo));
        List<OdontologoDto> odontologoList = odontologoService.obtenerTodos();
        Assert.assertTrue(odontologoList.size()==2);
    }

    @Test
    void traerTodos() {
        List<OdontologoDto> odontologoList = odontologoService.obtenerTodos();
        Assert.assertTrue(!odontologoList.isEmpty());
        Assert.assertTrue(odontologoList.size()==2);
    }

    @Test
    void eliminarOdontologoTest() throws DeleteException{
        odontologoService.borrar(1);
        List<OdontologoDto> odontologoList = odontologoService.obtenerTodos();
        Assert.assertTrue(odontologoList.size()==1);
   */
}