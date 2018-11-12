package com.example.omar10ibanez.serviciosenfermeria.usuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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
import com.example.omar10ibanez.serviciosenfermeria.enfermera.RegistroEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerUser;
import com.example.omar10ibanez.serviciosenfermeria.R;
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
import java.util.HashMap;
import java.util.Map;

public class LoginUser extends AppCompatActivity {
    Button registrar, login;
    EditText correo, contrasena;
    ProgressDialog progressDialog;
    JsonObjectRequest  jsonObjectRequest;
    UsuarioLogged usuarioLogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        registrar = (Button) findViewById(R.id.butonRegistrarLog);
        login = (Button) findViewById(R.id.buttonLoggeo);
        correo = (EditText) findViewById(R.id.editTextMailLoginUser);
        contrasena = (EditText) findViewById(R.id.editTextPasswordLoginUser);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUser.this, RegistroUser.class);
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
                    Toast.makeText(LoginUser.this, "Hay campos de texto vacíos", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void verificarLogin() {


        String ip = getString(R.string.ip);
        String url = ip + "/serviciosEnfermer%C3%ADa/php2/loginUser.php?correo=" +
                correo.getText().toString() + "&contrasena=" + contrasena.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        UsuarioLogged usuarioLogged = new UsuarioLogged();

                        try {


                            usuarioLogged = new UsuarioLogged();


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.optString("nombre").equals("no registra")) {
                                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            } else {



                                usuarioLogged.setNombreuserlogged(jsonObject.getJSONArray("usuario").getJSONObject(0).optString("nombre"));
                                usuarioLogged.setApellidouserlogged(jsonObject.getJSONArray("usuario").getJSONObject(0).optString("apellido"));
                                usuarioLogged.setIdUserlogged(jsonObject.getJSONArray("usuario").getJSONObject(0).optInt("idUsuario"));
                                usuarioLogged.setCorreouserlogged(jsonObject.getJSONArray("usuario").getJSONObject(0).optString("correo"));
                                usuarioLogged.setFotouserlogged(jsonObject.getJSONArray("usuario").getJSONObject(0).optString(    "foto"));



                                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerUser.class);
                                    intent.putExtra("usuariologged", usuarioLogged);
                                    startActivity(intent);

                            }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"No se puede conectar con el servidor " + error ,Toast.LENGTH_LONG).show();


            }
        });
        requestQueue.add(jsonObjectRequest);


    }



}




