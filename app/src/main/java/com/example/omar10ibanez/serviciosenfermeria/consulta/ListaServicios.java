package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omar10ibanez.serviciosenfermeria.R;

public class ListaServicios extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_servicios, container, false);
        return view;
    }
}
