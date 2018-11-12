package com.example.omar10ibanez.serviciosenfermeria.usuario;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.chat.Mensaje;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerUser;
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


public class Publicar_Servicio extends Fragment {

    Button publicar;
    EditText nombre, descripcion, idUsuario, direccion;

    public static  final int ip = R.string.ip;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_publicar__servicio, container, false);

        idUsuario = (EditText) rootView.findViewById(R.id.editText03ser);
        nombre = (EditText) rootView.findViewById(R.id.editText01ser);
        descripcion = (EditText) rootView.findViewById(R.id.editText02ser);
        direccion = (EditText) rootView.findViewById(R.id.editText04ser);

        publicar = (Button) rootView.findViewById(R.id.buttonRegistroser);

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().trim().equalsIgnoreCase("") ||
                        !descripcion.getText().toString().trim().equalsIgnoreCase("") ||
                        !direccion.getText().toString().trim().equalsIgnoreCase("") ||
                        !idUsuario.getText().toString().trim().equalsIgnoreCase("")) {

                    publicarServicio();
                }
                else {

                    Toast.makeText(getActivity(), "Hay campos de texto vacíos", Toast.LENGTH_SHORT).show();
                }

        }
        });
        return rootView;
    }

   private void publicarServicio() {


       String idChat=generarIdChat();

       DatabaseReference chat= FirebaseDatabase.getInstance().getReference().child("servicio/"+idChat);
       chat.push().setValue(new Mensaje("Usuario","Bienvenido"));

       OkHttpClient objeto = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("titulo", nombre.getText().toString())
                .add("descripcion", descripcion.getText().toString())
                .add("idUsuario", idUsuario.getText().toString())
                .add("direccion", direccion.getText().toString())
                .add("idChat", idChat)
                .build();
        //PASS OVER THE BUNDLE TO OUR FRAGMENT


        //THEN NOW SHOW OUR FRAGMENT
       String ip = getString( R.string.ip);
        Request request = new Request.Builder().url(ip+"/serviciosEnfermer%C3%ADa/php2/pubServicioUser.php").post(formBody).build();
        Call call = objeto.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("error", "error en " + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {

                Intent llevar = new Intent(getActivity(), NavigationDrawerUser.class);
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

