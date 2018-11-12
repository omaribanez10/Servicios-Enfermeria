package com.example.omar10ibanez.serviciosenfermeria.consulta;

/**
 * Created by Omar10ibañez on 23/04/2018.
 */

public class EnfermeraDialog {


        private String nombre;
        private String apellido;
        private String telefono;
        private String DesripcionTrabajo;
        private String calificacion;
        private String foto;

    public EnfermeraDialog() {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        DesripcionTrabajo = DesripcionTrabajo;
        this.calificacion = calificacion;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDesripcionTrabajo() {
        return DesripcionTrabajo;
    }

    public void setDesripcionTrabajo(String desripcionTrabajo) {
        DesripcionTrabajo = desripcionTrabajo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

