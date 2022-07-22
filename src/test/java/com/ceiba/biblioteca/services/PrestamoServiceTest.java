package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.models.dtos.PrestamoDto;
import com.ceiba.biblioteca.models.dtos.ResultadoPrestar;
import com.ceiba.biblioteca.repositories.IPrestamoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PrestamoServiceTest {

    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;
    public static final int USUARIO_DESCONOCIDO = 5;

    @Autowired
    Iprestamocontrolador prestamoService;

    @MockBean
    IPrestamoRepository prestamoRepository;

    @DisplayName("Probar método save OK")
    @Test
    void when_save_data_exists_ok() throws Exception {

        Prestamo prestamo = new Prestamo();
        prestamo.setIsbn("EQWQW8545");
        prestamo.setIdentificacionUsuario("74851254");
        prestamo.setTipoUsuario(USUARIO_INVITADO);

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        ResultadoPrestar actual = prestamoService.save(prestamo);

        assertNotNull(actual);
    }

    @DisplayName("Probar método findById OK")
    @Test
    void when_findById_data_exists_ok() throws Exception {

        Long id = 1L;

        Prestamo prestamo = new Prestamo();
        prestamo.setIsbn("EQWQW8545");
        prestamo.setIdentificacionUsuario("74851254");
        prestamo.setTipoUsuario(USUARIO_INVITADO);

        Optional<Prestamo> prestamoOptional = Optional.of(prestamo);

        when(prestamoRepository.findById(id)).thenReturn(prestamoOptional);

        PrestamoDto actual = prestamoService.findById(id);

        assertNotNull(actual);
    }
}