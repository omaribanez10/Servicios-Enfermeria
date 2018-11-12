package com.example.omar10ibanez.serviciosenfermeria.consulta;

/**
 * Created by Omar10ibañez on 3/03/2018.
 */

public class Oferta {
    private String titulo;
    private String descripcion;
    private String telefono;
    private String fecha;
    private String hora;
    private String nombreNurse;
    private String apellido;
    private String calificacionNurse;

    public String getCalificacionNurse() {
        return calificacionNurse;
    }

    public void setCalificacionNurse(String calificacionNurse) {
        this.calificacionNurse = calificacionNurse;
    }

    public Oferta() {
    }

    public Oferta(String titulo, String descripcion, String telefono, String fecha, String hora, String nombreNurse, String apellido, String calificacionNurse) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.fecha = fecha;
        this.hora = hora;
        this.nombreNurse = nombreNurse;
        this.apellido = apellido;
        this.calificacionNurse = calificacionNurse;
    }

    public String getNombreNurse() {
        return nombreNurse;
    }

    public void setNombreNurse(String nombreNurse) {
        this.nombreNurse = nombreNurse;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }




    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
