package com.example.omar10ibanez.serviciosenfermeria.enfermera;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.chat.Mensaje;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerUser;
import com.example.omar10ibanez.serviciosenfermeria.usuario.Publicar_Servicio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class PublicarServicioEnfermera extends Fragment {

    Button publicar;
    EditText titulo, descripcion, idEnfermera;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_publicar_servicio__enfermera, container, false);

        titulo = (EditText) rootView.findViewById(R.id.editText01serEnf);
        descripcion = (EditText) rootView.findViewById(R.id.editText02serEnf);
        idEnfermera = (EditText) rootView.findViewById(R.id.editText03serEnf);



        publicar = (Button) rootView.findViewById(R.id.buttonRegistroserEnf);

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (!titulo.getText().toString().trim().equalsIgnoreCase("") ||
                        !descripcion.getText().toString().trim().equalsIgnoreCase("") ||
                        !idEnfermera.getText().toString().trim().equalsIgnoreCase("")){

                    publicarServicio();
                }
                else {

                    Toast.makeText(getActivity(), "Hay campos de texto vacíos, verifique la informacion", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }

    private void publicarServicio() {
        String idChat=generarIdChat();
        DatabaseReference chat = FirebaseDatabase.getInstance().getReference().child("oferta/"+idChat);
        chat.push().setValue(new Mensaje("Enfermera","Bienvenido"));

        OkHttpClient objeto = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("titulo", titulo.getText().toString())
                .add("descripcion", descripcion.getText().toString())
                .add("idEnfermera", idEnfermera.getText().toString())
                .add("idChat", idChat)
                .build();
        //PASS OVER THE BUNDLE TO OUR FRAGMENT


        //THEN NOW SHOW OUR FRAGMENT
        String ip = getString(R.string.ip);
        Request request = new Request.Builder().url(ip + "/serviciosEnfermer%C3%ADa/php2/pubOfertaEnfer.php").post(formBody).build();
        Call call = objeto.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("error", "error en " + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {

                Toast.makeText(getContext(), "Su oferta ha sido publicada", Toast.LENGTH_SHORT).show();



                Intent llevar = new Intent(getActivity(), NavigationDrawerEnfermera.class);
                startActivity(llevar);


            }
        });
    }
        public String generarIdChat() {
            String nombrefoto = "";
            //este poco de codigo es para generar letramayus y minus y numero y asignarselas a un string nombrefoto
            for (int i = 1; i < 10; i++) {
                //Se crea un elecion con aleatorios entre 1 y 3 (ya sea para mayus,minus,o numeros)
                int eleccion = ((int) Math.floor(Math.random() * 3 + 1));
                //Si eleccion=1 se creara un char aleatorio bajo la instruccion despues del if
                if (eleccion == 1.0) {
                    char minusculas = (char) ((int) Math.floor(Math.random() * (123 - 97) + 97));
                    //Aqui se va concatenando el string password con el char en minucual generado
                    nombrefoto += (char) minusculas;

                }
                if (eleccion == 2.0) {
                    char mayusculas = (char) ((int) Math.floor(Math.random() * (90 - 65) + 65));
                    nombrefoto += mayusculas;
                }
                if (eleccion == 3.0) {
                    char numeros = (char) ((int) Math.floor(Math.random() * (48 - 57) + 57));
                    nombrefoto += (char) numeros;
                }

            }
            return nombrefoto;
        }

    }



