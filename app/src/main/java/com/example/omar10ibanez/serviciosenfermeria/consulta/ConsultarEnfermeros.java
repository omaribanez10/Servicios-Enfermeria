package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.app.ProgressDialog;
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
import com.example.omar10ibanez.serviciosenfermeria.adapter.EnfermerosAdapter;
import com.example.omar10ibanez.serviciosenfermeria.consulta.Enfermera;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ConsultarEnfermeros extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{


    RecyclerView recyclerViewEnfermeros;
    ArrayList<Enfermera> listaEnfermeras = new ArrayList<>();
    ArrayList<EnfermeraDialog> listaEnfermerasDialog;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_consultar_enfermeros, container, false);

        listaEnfermeras = new ArrayList<>();
        listaEnfermerasDialog = new ArrayList<>();
        recyclerViewEnfermeros = (RecyclerView) rootView.findViewById(R.id.recyclerviewEnfermeraList);
        recyclerViewEnfermeros.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewEnfermeros.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());


        cargarWebservice();


        recyclerViewEnfermeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarWebserviceDialog();
            }
        });



        return rootView;
    }

    private void cargarWebservice() {


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando Enfermeros ");
        progressDialog.show();
        String ip = getString(R.string.ip);
        String url = ip + "/serviciosEnfermer%C3%ADa/php1/obtener_enfermera.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
        progressDialog.hide();

    }



   private void cargarWebserviceDialog() {


       String ip = getString(R.string.ip);
       String url = ip + "/serviciosEnfermer%C3%ADa/php1/obtener_enfermera_dialog.php";

       jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
       requestQueue.add(jsonObjectRequest);




    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        progressDialog.hide();

    }



    @Override
    public void onResponse(JSONObject response ) {



        Enfermera enfermera = null;
        JSONArray jsonArray = response.optJSONArray("enfermeras");

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                enfermera = new Enfermera();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);


                enfermera.setNombre(jsonObject.optString("nombre"));
                enfermera.setApellido(jsonObject.optString("apellido"));
                enfermera.setFoto(jsonObject.optString("foto"));
                listaEnfermeras.add(enfermera);

            }

            EnfermerosAdapter adapter = new EnfermerosAdapter(listaEnfermeras, getContext());
            recyclerViewEnfermeros.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se puede conectar con el servidor " + response, Toast.LENGTH_LONG).show();

            progressDialog.hide();
        }



        /**

            EnfermeraDialog enfermeradialog = null;
            JSONArray jsonArraydialog = response.optJSONArray("enfermerasdialog");

            try {
                for (int i = 0; i < jsonArraydialog.length(); i++) {
                    enfermeradialog = new EnfermeraDialog();
                    JSONObject jsonObjectdialog = null;
                    jsonObjectdialog = jsonArraydialog.getJSONObject(i);


                    enfermeradialog.setNombre(jsonObjectdialog.optString("nombre"));
                    enfermeradialog.setApellido(jsonObjectdialog.optString("apellido"));
                    enfermeradialog.setFoto(jsonObjectdialog.optString("foto"));
                    enfermeradialog.setTelefono(jsonObjectdialog.optString("telefono"));
                    enfermeradialog.setDesripcionTrabajo(jsonObjectdialog.optString("DesripcionTrabajo"));
                    enfermeradialog.setCalificacion(jsonObjectdialog.optString("Calificaci\\u00f3n"));


                    listaEnfermerasDialog.add(enfermeradialog);

                }



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se puede conectar con el servidor " + response, Toast.LENGTH_LONG).show();


            progressDialog.hide();
        } **/

    }





}


