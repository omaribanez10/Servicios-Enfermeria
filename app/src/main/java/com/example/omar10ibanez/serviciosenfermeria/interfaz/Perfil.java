package com.example.omar10ibanez.serviciosenfermeria.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.LoginEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.usuario.LoginUser;
import com.example.omar10ibanez.serviciosenfermeria.usuario.RegistroUser;

public class Perfil extends AppCompatActivity {


    Button usuario, enfermera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        usuario = (Button)findViewById(R.id.buttonUser);
        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, LoginUser.class);
                startActivity(intent);
            }

        });
        enfermera = (Button)findViewById(R.id.buttonEnf);
        enfermera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, LoginEnfermera.class);
                startActivity(intent);
            }

        });
    }
}
