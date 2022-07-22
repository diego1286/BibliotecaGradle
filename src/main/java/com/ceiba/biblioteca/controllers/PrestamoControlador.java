package com.ceiba.biblioteca.controllers;


import com.ceiba.biblioteca.models.dtos.PrestamoDto;
import com.ceiba.biblioteca.models.dtos.ResultadoPrestar;
import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.services.Iprestamocontrolador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    @Autowired
    private Iprestamocontrolador prestamoService;

    @PostMapping
    public ResponseEntity<ResultadoPrestar> prestar(@RequestBody final Prestamo prestado) throws Exception {
        return ResponseEntity.ok().body(prestamoService.save(prestado));
    }

    @GetMapping("/{id-prestamo}")
    public ResponseEntity<PrestamoDto> obtenerPrestamo(@PathVariable(value = "id-prestamo") final Long idPrestamo) throws Exception{
        return ResponseEntity.ok().body(prestamoService.findById(idPrestamo));
    }

}

