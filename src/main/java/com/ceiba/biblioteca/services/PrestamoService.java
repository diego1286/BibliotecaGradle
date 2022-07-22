package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.constants.DiaTipoUsuario;
import com.ceiba.biblioteca.constants.Mensajes;
import com.ceiba.biblioteca.constants.TipoUsuario;
import com.ceiba.biblioteca.exceptions.BadRequestException;
import com.ceiba.biblioteca.exceptions.ErrorDto;
import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.models.dtos.PrestamoDto;
import com.ceiba.biblioteca.models.dtos.ResultadoPrestar;
import com.ceiba.biblioteca.repositories.IPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService implements Iprestamocontrolador{

    @Autowired
    private IPrestamoRepository prestamoRepository;


    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) prestamoRepository.findAll();
    }

    @Override
    public ResultadoPrestar save(Prestamo prestamo) throws Exception {
        if(!esTipoUsuarioValido(prestamo.getTipoUsuario())){
            throw new BadRequestException(ErrorDto.getErrorDto(Mensajes.ID_NO_PERMIT));
        }
        List<Prestamo> prestamos = prestamoRepository.findByIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        if(prestamos.size() > 0 && prestamo.getTipoUsuario() == TipoUsuario.USUARIO_INVITADO){
            throw new BadRequestException(ErrorDto.getErrorDto(Mensajes.mensajeLibroPrestado(prestamo.getIdentificacionUsuario())));
        }
        String fechaMaximaDevolucion = getFechaMaximaDevolucion(prestamo.getTipoUsuario());
        prestamo.setFechaMaximaDevolucion(fechaMaximaDevolucion);
        Prestamo prestamoBD = prestamoRepository.save(prestamo);
        return obtenerResultado(prestamoBD);
    }

    @Override
    public PrestamoDto findById(Long id) throws Exception {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(prestamo.isPresent()){
            return obtenerResultadoPrestamo(prestamo.get());
        }else {
            return new PrestamoDto();
        }
    }

    private PrestamoDto obtenerResultadoPrestamo(final Prestamo prestamo){
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setId(prestamo.getId());
        prestamoDto.setIsbn(prestamo.getIsbn());
        prestamoDto.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        prestamoDto.setTipoUsuario(prestamo.getTipoUsuario());
        prestamoDto.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());
        return prestamoDto;
    }

    private ResultadoPrestar obtenerResultado(final Prestamo prestamo){
        ResultadoPrestar resultadoPrestar = new ResultadoPrestar();
        resultadoPrestar.setId(prestamo.getId());
        resultadoPrestar.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());
        return resultadoPrestar;
    }

    private String getFechaMaximaDevolucion(final Integer tipoUsuario){
        LocalDate fecha = LocalDate.now();
        switch (tipoUsuario){
            case 1:
                fecha = agregarDias(DiaTipoUsuario.USUARIO_AFILIADO);
                break;
            case 2:
                fecha = agregarDias(DiaTipoUsuario.USUARIO_EMPLEADO);
                break;
            case 3:
                fecha = agregarDias(DiaTipoUsuario.USUARIO_INVITADO);
                break;
            default:
        }
        return formatearFecha(fecha);
    }

    private Boolean esTipoUsuarioValido(final Integer tipoUsuario){
        return (tipoUsuario == TipoUsuario.USUARIO_AFILIADO || tipoUsuario == TipoUsuario.USUARIO_EMPLEADO || tipoUsuario == TipoUsuario.USUARIO_INVITADO);
    }


    private LocalDate agregarDias(final long dias){
        LocalDate ahora = LocalDate.now();
        if (dias < 1) {
            return ahora;
        }
        LocalDate resultado = ahora;
        int adicionados = 0;
        while (adicionados < dias) {
            resultado = resultado.plusDays(1);
            if (!(resultado.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    resultado.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++adicionados;
            }
        }
        return resultado;
    }

    private String formatearFecha(LocalDate fecha){
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        return fecha.format(formatters);
    }
}
