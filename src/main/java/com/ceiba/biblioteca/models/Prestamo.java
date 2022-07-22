package com.ceiba.biblioteca.models;


import com.ceiba.biblioteca.constants.Mensajes;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 10, message = Mensajes.ISBN_NO_VALIDO)
    @NotNull
    private String isbn;// TODO: colocar el entity

    @Size(min = 1, max = 10, message = Mensajes.ID_NO_VALIDO)
    @NotNull
    @Column(name = "identification_usuario")
    private String identificacionUsuario;// TODO: colocar el entity

    @NotNull
    @Range(min = 1, max = 3, message = Mensajes.ID_NO_PERMIT)
    @Column(name = "tipo_usuario")
    private Integer tipoUsuario;// TODO: colocar el entity

    @Column(name = "fecha_maxima_devolucion")
    private String fechaMaximaDevolucion;

    public Prestamo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
