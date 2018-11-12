package com.example.omar10ibanez.serviciosenfermeria.consulta;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.omar10ibanez.serviciosenfermeria.adapter.ServiciosAceptadosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ConsultarServiciosAceptados extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    RecyclerView recyclerViewServiaceptados;
    ArrayList<ServicioAceptado> listaServiaceptados;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultar_servicios_aceptados, container, false);


        listaServiaceptados = new ArrayList<>();
        recyclerViewServiaceptados = (RecyclerView)view.findViewById(R.id.recyclerviewServiciosAceptadosList);
        recyclerViewServiaceptados.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewServiaceptados.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());



        cargarWebservice();


        recyclerViewServiaceptados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Has seleccionado" , Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void cargarWebservice() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultando... ");
        progressDialog.show();
        String ip = getString( R.string.ip);
        String url = ip+ "/serviciosEnfermer%C3%ADa/php1/obtener_servicio.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
        ServicioAceptado servicioAceptado = null;
        JSONArray jsonArray = response.optJSONArray("servicios");


        try {
            for (int i=0; i<jsonArray.length(); i++) {
                servicioAceptado = new ServicioAceptado();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);


                servicioAceptado.setIdServicio(jsonObject.optInt("idServicio"));
                servicioAceptado.setNombreser(jsonObject.optString("titulo"));
                servicioAceptado.setDescripcionser(jsonObject.optString("descripcion"));
                servicioAceptado.setTelefonoser(jsonObject.optString("telefono"));
                servicioAceptado.setDireccionser(jsonObject.optString("direccion"));
                servicioAceptado.setNombreUser(jsonObject.optString("nombre"));
                servicioAceptado.setApellidoUser(jsonObject.optString("apellido"));

                listaServiaceptados.add(servicioAceptado);
            }

            progressDialog.hide();
            ServiciosAceptadosAdapter adapter = new ServiciosAceptadosAdapter(listaServiaceptados,getContext());
            recyclerViewServiaceptados.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se puede conectar con el servidor " + response,Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }

    }
}
