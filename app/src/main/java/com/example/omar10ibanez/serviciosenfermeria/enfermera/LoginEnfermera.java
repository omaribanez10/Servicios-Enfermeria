package com.example.omar10ibanez.serviciosenfermeria.enfermera;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.consulta.Enfermera;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ServicioAceptado;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerUser;
import com.example.omar10ibanez.serviciosenfermeria.usuario.LoginUser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public  class LoginEnfermera extends AppCompatActivity  {
    Button registrar, login;
    EditText correo, contrasena;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progressDialog;
    EnfermeraLogged enfermeraLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_enfermera);
        registrar = (Button) findViewById(R.id.butonRegistrarlogEnf);
        login = (Button) findViewById(R.id.buttonLoggeologEnf);

        correo = (EditText) findViewById(R.id.editText01MailLogEnf);
        contrasena = (EditText) findViewById(R.id.editText02logPasswordEnf);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginEnfermera.this, RegistroEnfermera.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!correo.getText().toString().trim().equalsIgnoreCase("") ||
                        !contrasena.getText().toString().trim().equalsIgnoreCase("")) {

                    verificarLogin();


                } else {
                    Toast.makeText(LoginEnfermera.this, "Hay campos de texto vacíos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void verificarLogin() {

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage(" Verificando Usuario ");
        progressDialog.show();
        String ip = getString(R.string.ip);
        String url = ip + "/serviciosEnfermer%C3%ADa/php2/loginEnfer.php?correo=" +
                correo.getText().toString() + "&contrasena=" + contrasena.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.hide();
                enfermeraLogged = new EnfermeraLogged();

                EnfermeraLogged enfermeraLogged = null;
                JSONArray jsonArray = response.optJSONArray("enfermera");
                try {


                    enfermeraLogged = new EnfermeraLogged();
                    JSONObject jsonObject = null;
                    jsonObject = jsonArray.getJSONObject(0);

                    if (jsonObject.optString("nombre").equals("no registra")) {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña icorrectos", Toast.LENGTH_SHORT).show();
                    } else {
                        enfermeraLogged.setNombrelogged(jsonObject.optString("nombre"));
                        enfermeraLogged.setApellidologged(jsonObject.optString("apellido"));
                        enfermeraLogged.setIdEnfermeralogged(jsonObject.optInt("idEnfermera"));
                        enfermeraLogged.setTelefonologged(jsonObject.optString("telefono"));
                        enfermeraLogged.setFotologged(jsonObject.optString("foto"));
                        enfermeraLogged.setCorreologged(jsonObject.optString("correo"));

                        progressDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerEnfermera.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("enfermeralogged", enfermeraLogged);

                        intent.putExtras(bundle);

                        startActivity(intent);
                        finish();
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"No se puede conectar con el servidor " + error ,Toast.LENGTH_LONG).show();


            }
        });
        requestQueue.add(jsonObjectRequest);


    }


}

