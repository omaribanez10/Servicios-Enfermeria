package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omar10ibanez.serviciosenfermeria.R;

/**
 * Created by Omar10ibañez on 23/04/2018.
 */

public class ListaEnfermeros extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView =inflater.inflate(R.layout.fragment_lista_enfermeros, container, false);
    return rootView;
}
}
