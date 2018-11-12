package com.example.omar10ibanez.serviciosenfermeria.usuario;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Omar10ibañez on 13/05/2018.
 */

public class UsuarioLogged implements Parcelable {
    Integer idUserlogged;
    String nombreuserlogged ,apellidouserlogged ,correouserlogged , fotouserlogged;

    public UsuarioLogged() {
    }

    public UsuarioLogged(Integer idUserlogged, String nombreuserlogged, String apellidouserlogged, String correouserlogged, String fotouserlogged) {
        this.idUserlogged = idUserlogged;
        this.nombreuserlogged = nombreuserlogged;
        this.apellidouserlogged = apellidouserlogged;
        this.correouserlogged = correouserlogged;

        this.fotouserlogged = fotouserlogged;
    }

    public Integer getIdUserlogged() {
        return idUserlogged;
    }

    public void setIdUserlogged(Integer idUserlogged) {
        this.idUserlogged = idUserlogged;
    }

    public String getNombreuserlogged() {
        return nombreuserlogged;
    }

    public void setNombreuserlogged(String nombreuserlogged) {
        this.nombreuserlogged = nombreuserlogged;
    }

    public String getApellidouserlogged() {
        return apellidouserlogged;
    }

    public void setApellidouserlogged(String apellidouserlogged) {
        this.apellidouserlogged = apellidouserlogged;
    }

    public String getCorreouserlogged() {
        return correouserlogged;
    }

    public void setCorreouserlogged(String correouserlogged) {
        this.correouserlogged = correouserlogged;
    }


    public String getFotouserlogged() {
        return fotouserlogged;
    }

    public void setFotouserlogged(String fotouserlogged) {
        this.fotouserlogged = fotouserlogged;
    }


    protected UsuarioLogged(Parcel in) {
        idUserlogged = in.readInt();
        nombreuserlogged = in.readString();
        correouserlogged = in.readString();
        apellidouserlogged = in.readString();
        fotouserlogged = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(idUserlogged);
        dest.writeString(correouserlogged);
        dest.writeString(apellidouserlogged);
        dest.writeString(nombreuserlogged);
        dest.writeString(fotouserlogged);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UsuarioLogged> CREATOR = new Parcelable.Creator<UsuarioLogged>() {
        @Override
        public UsuarioLogged createFromParcel(Parcel in) {
            return new UsuarioLogged(in);
        }

        @Override
        public UsuarioLogged[] newArray(int size) {
            return new UsuarioLogged[size];
        }
    };
}
