package com.ceiba.biblioteca.exceptions;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mensaje;

    /**
     * Obtiene un nuevo error
     * @param message
     * @return
     */
    public static ErrorDto getErrorDto(String message) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMensaje(message);
        return errorDto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }
    /**
     * @param mensaje the message to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
