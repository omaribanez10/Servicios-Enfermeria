package com.example.omar10ibanez.serviciosenfermeria.usuario;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omar10ibanez.serviciosenfermeria.R;

/**
 * Created by Omar10ibañez on 7/05/2018.
 */

public class ConsultarOfertaDetalles extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.dialog_ver_info_oferta, container, false);
        return view;

    }
}
