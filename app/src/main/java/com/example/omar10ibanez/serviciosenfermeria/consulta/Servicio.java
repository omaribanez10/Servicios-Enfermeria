package com.example.omar10ibanez.serviciosenfermeria.consulta;

/**
 * Created by Omar10ibañez on 25/11/2017.
 */

public class Servicio {
    private Integer idServicio;
    private String nombreser;
    private String descripcionser;
    private String telefonoser;
    private String direccionser;
    private String fechaser;
    private String horaser;
    private String nombreUser, apellidoUser;

    public Servicio() {
    }

    public Servicio(Integer idServicio, String nombreser, String descripcionser, String telefonoser, String direccionser, String fechaser, String horaser, String nombreUser, String apellidoUser) {
        this.idServicio = idServicio;
        this.nombreser = nombreser;
        this.descripcionser = descripcionser;
        this.telefonoser = telefonoser;
        this.direccionser = direccionser;
        this.fechaser = fechaser;
        this.horaser = horaser;
        this.nombreUser = nombreUser;
        this.apellidoUser = apellidoUser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public void setApellidoUser(String apellidoUser) {
        this.apellidoUser = apellidoUser;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreser() {
        return nombreser;
    }

    public void setNombreser(String nombreser) {
        this.nombreser = nombreser;
    }

    public String getDescripcionser() {
        return descripcionser;
    }

    public void setDescripcionser(String descripcionser) {
        this.descripcionser = descripcionser;
    }

    public String getTelefonoser() {
        return telefonoser;
    }

    public void setTelefonoser(String telefonoser) {
        this.telefonoser = telefonoser;
    }

    public String getDireccionser() {
        return direccionser;
    }

    public void setDireccionser(String direccionser) {
        this.direccionser = direccionser;
    }

    public String getFechaser() {
        return fechaser;
    }

    public void setFechaser(String fechaser) {
        this.fechaser = fechaser;
    }

    public String getHoraser() {
        return horaser;
    }

    public void setHoraser(String horaser) {
        this.horaser = horaser;
    }
}
