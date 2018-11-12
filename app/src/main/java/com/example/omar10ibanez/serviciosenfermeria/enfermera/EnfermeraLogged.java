package com.example.omar10ibanez.serviciosenfermeria.enfermera;

import java.io.Serializable;

/**
 * Created by Omar10ibañez on 13/05/2018.
 */

public class EnfermeraLogged implements Serializable {

   Integer idEnfermeralogged;
   String nombrelogged ,apellidologged ,correologged ,telefonologged, fotologged;

    public EnfermeraLogged() {
    }

    public EnfermeraLogged(Integer idEnfermeralogged, String nombrelogged, String apellidologged, String correologged, String telefonologged, String fotologged) {
        this.idEnfermeralogged = idEnfermeralogged;
        this.nombrelogged = nombrelogged;
        this.apellidologged = apellidologged;
        this.correologged = correologged;
        this.telefonologged = telefonologged;
        this.fotologged = fotologged;
    }

    public Integer getIdEnfermeralogged() {
        return idEnfermeralogged;
    }

    public void setIdEnfermeralogged(Integer idEnfermeralogged) {
        this.idEnfermeralogged = idEnfermeralogged;
    }

    public String getNombrelogged() {
        return nombrelogged;
    }

    public String setNombrelogged(String nombrelogged) {
        this.nombrelogged = nombrelogged;
        return nombrelogged;
    }

    public String getApellidologged() {
        return apellidologged;
    }

    public void setApellidologged(String apellidologged) {
        this.apellidologged = apellidologged;
    }

    public String getCorreologged() {
        return correologged;
    }

    public void setCorreologged(String correologged) {
        this.correologged = correologged;
    }

    public String getTelefonologged() {
        return telefonologged;
    }

    public void setTelefonologged(String telefonologged) {
        this.telefonologged = telefonologged;
    }

    public String getFotologged() {
        return fotologged;
    }

    public void setFotologged(String fotologged) {
        this.fotologged = fotologged;
    }
}
