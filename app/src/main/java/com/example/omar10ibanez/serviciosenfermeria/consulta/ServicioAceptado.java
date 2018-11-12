package com.example.omar10ibanez.serviciosenfermeria.consulta;

/**
 * Created by Omar10ibañez on 10/05/2018.
 */

public class ServicioAceptado {
    private Integer idServicio;
    private String nombreser;
    private String descripcionser;
    private String telefonoser;
    private String direccionser;
    private String nombreUser, apellidoUser;

    public ServicioAceptado() {
    }

    public ServicioAceptado(Integer idServicio, String nombreser, String descripcionser, String telefonoser, String direccionser, String nombreUser, String apellidoUser) {
        this.idServicio = idServicio;
        this.nombreser = nombreser;
        this.descripcionser = descripcionser;
        this.telefonoser = telefonoser;
        this.direccionser = direccionser;
        this.nombreUser = nombreUser;
        this.apellidoUser = apellidoUser;
    }

    //GETTERS
    public Integer getIdServicio() {
        return idServicio;
    }

    public String getNombreser() {
        return nombreser;
    }

    public String getDescripcionser() {
        return descripcionser;
    }

    public String getTelefonoser() {
        return telefonoser;
    }

    public String getDireccionser() {
        return direccionser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    //SETTERS


    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public void setNombreser(String nombreser) {
        this.nombreser = nombreser;
    }

    public void setDescripcionser(String descripcionser) {
        this.descripcionser = descripcionser;
    }

    public void setTelefonoser(String telefonoser) {
        this.telefonoser = telefonoser;
    }

    public void setDireccionser(String direccionser) {
        this.direccionser = direccionser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setApellidoUser(String apellidoUser) {
        this.apellidoUser = apellidoUser;
    }
}
