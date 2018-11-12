package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.app.Dialog;
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
import com.example.omar10ibanez.serviciosenfermeria.adapter.ServiciosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultarServicioEnfermera extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{


    RecyclerView recyclerViewServi;
   ArrayList<Servicio> listaServi;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View rootView = inflater.inflate(R.layout.fragment_consultar_servicio_enfermera, container, false);



        listaServi = new ArrayList<>();
        recyclerViewServi = (RecyclerView)rootView.findViewById(R.id.recyclerviewServiList);
        recyclerViewServi.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewServi.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());



        cargarWebservice();



        return rootView;
    }
    private void cargarWebservice() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando... ");
        progressDialog.show();
        String ip = getString( R.string.ip);
        String url = ip+ "/serviciosEnfermer%C3%ADa/php1/obtener_servicio2.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se puede conectar" + error.toString(),Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        progressDialog.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Servicio servicio = null;
        JSONArray jsonArray = response.optJSONArray("servicios2");


                try {
                    for (int i=0; i<jsonArray.length(); i++) {
                        servicio = new Servicio();
                        JSONObject jsonObject = null;
                        jsonObject = jsonArray.getJSONObject(i);


                        servicio.setIdServicio(jsonObject.optInt("idServicio"));
                        servicio.setNombreser(jsonObject.optString("titulo"));
                        servicio.setDescripcionser(jsonObject.optString("descripcion"));
                        servicio.setTelefonoser(jsonObject.optString("telefono"));
                        servicio.setDireccionser(jsonObject.optString("direccion"));
                        servicio.setFechaser(jsonObject.optString("fecha"));
                        servicio.setHoraser(jsonObject.optString("hora"));
                        servicio.setNombreUser(jsonObject.optString("nombre"));
                        servicio.setApellidoUser(jsonObject.optString("apellido"));

                        listaServi.add(servicio);
                    }

            progressDialog.hide();
            ServiciosAdapter adapter = new ServiciosAdapter(listaServi,getContext());
            recyclerViewServi.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se puede conectar con el servidor " + response,Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }

    }
}
