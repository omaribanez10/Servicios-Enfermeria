package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.adapter.OfertasAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultarOfertas extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {


    RecyclerView recyclerViewOfertas;
    ArrayList<Oferta> listaOfertas;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_consultar_ofertas, container, false);

        listaOfertas = new ArrayList<>();

        recyclerViewOfertas = (RecyclerView) rootView.findViewById(R.id.recyclerviewOfertas);
        recyclerViewOfertas.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewOfertas.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());
        cargarWebservice();
        return rootView;

    }

    private void cargarWebservice() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando... ");
        progressDialog.show();
        String ip = getString( R.string.ip);
        String url = ip+"/serviciosEnfermer%C3%ADa/php1/obtener_oferta2.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        progressDialog.hide();

    }

    @Override
    public void onResponse(JSONObject response) {
        Oferta oferta = null;
        JSONArray jsonArray = response.optJSONArray("ofertas2");

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                oferta = new Oferta();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);


                oferta.setTitulo(jsonObject.optString("titulo"));
                oferta.setDescripcion(jsonObject.optString("descripcion"));
                oferta.setTelefono(jsonObject.optString("telefono"));
                oferta.setFecha(jsonObject.optString("fecha"));
                oferta.setHora(jsonObject.optString("hora"));
                oferta.setNombreNurse(jsonObject.optString("nombre"));
                oferta.setApellido(jsonObject.optString("apellido"));
                oferta.setCalificacionNurse(jsonObject.optString("Calificaci\\u00f3n"));
                listaOfertas.add(oferta);

            }


            progressDialog.hide();
            OfertasAdapter adapter = new OfertasAdapter(listaOfertas,getContext());
            recyclerViewOfertas.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se puede conectar con el servidor " + response, Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }

    }
}

