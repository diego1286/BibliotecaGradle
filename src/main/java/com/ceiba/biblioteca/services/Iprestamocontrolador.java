package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.models.dtos.PrestamoDto;
import com.ceiba.biblioteca.models.dtos.ResultadoPrestar;

import java.util.List;

public interface Iprestamocontrolador {

    List<Prestamo> findAll();

    //Prestamo save(Prestamo prestamo) throws Exception;

    ResultadoPrestar save(Prestamo prestamo) throws Exception;

    PrestamoDto findById(Long id) throws Exception;
}

