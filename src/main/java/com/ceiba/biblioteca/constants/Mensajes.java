package com.ceiba.biblioteca.constants;

public interface Mensajes {
    static String mensajeLibroPrestado(String identificacion){
        return "El usuario con identificación "+ identificacion +" ya tiene un libro prestado por lo cual" +
                " no se le puede realizar otro préstamo";
    }
    String ISBN_NO_VALIDO = "ISBN no válido";
    String ID_NO_VALIDO = "Identificación no válida";
    String ID_NO_PERMIT = "Tipo de usuario no permitido en la biblioteca";

}
