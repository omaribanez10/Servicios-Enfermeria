package com.example.omar10ibanez.serviciosenfermeria.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.adapter.AdapterChat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar10ibañez on 17/05/2018.
 */

public class Chat extends AppCompatActivity {

    Button enviarMensaje;
    EditText mensaje;
    String msj;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Mensaje> mensajes;
    AdapterChat adapterChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);
        Bundle bundle=getIntent().getExtras();
        String idChat=bundle.getString("idChat");
        String nodo=bundle.getString("pertenecealNodo");
        databaseReference= FirebaseDatabase.getInstance().getReference().child(nodo+"/"+idChat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mensaje=(EditText)findViewById(R.id.mensaje);
        enviarMensaje=(Button)findViewById(R.id.enviarMensaje);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_chat);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mensajes=new ArrayList<>();
        actualizar();
        adapterChat=new AdapterChat(mensajes);
        recyclerView.setAdapter(adapterChat);
        linearLayoutManager.setStackFromEnd(true);
        setSupportActionBar(toolbar);
        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });

    }

    private void enviarMensaje(){
        msj=mensaje.getText().toString();
        databaseReference.push().setValue(new Mensaje("pedro",msj));
        mensaje.setText("");
    }

    private void actualizar(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mensajes.removeAll(mensajes);
                for(DataSnapshot mensaje:dataSnapshot.getChildren()){
                    Mensaje mensaje1=mensaje.getValue(Mensaje.class);
                    mensajes.add(mensaje1);
                }
                adapterChat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}

