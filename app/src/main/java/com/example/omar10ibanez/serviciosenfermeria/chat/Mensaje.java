package com.example.omar10ibanez.serviciosenfermeria.chat;

/**
 * Created by Omar10ibañez on 12/11/2017.
 */

public class Mensaje {
    String mensaje,nombreEnviador;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String nombreEnviador) {
        this.mensaje = mensaje;
        this.nombreEnviador = nombreEnviador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreEnviador() {
        return nombreEnviador;
    }

    public void setNombreEnviador(String nombreEnviador) {
        this.nombreEnviador = nombreEnviador;
    }
}
