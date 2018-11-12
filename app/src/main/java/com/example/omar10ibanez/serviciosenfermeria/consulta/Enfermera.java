package com.example.omar10ibanez.serviciosenfermeria.consulta;

/**
 * Created by Omar10ibañez on 16/11/2017.
 */

public class Enfermera {


    private String nombre;
    private String apellido;
    private String foto;

    public Enfermera(String nombre, String apellido, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
    }

    public Enfermera() {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
