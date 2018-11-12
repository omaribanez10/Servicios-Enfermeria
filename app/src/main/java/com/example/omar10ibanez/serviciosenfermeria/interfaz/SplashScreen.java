package com.example.omar10ibanez.serviciosenfermeria.interfaz;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.omar10ibanez.serviciosenfermeria.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Perfil.class);
                startActivity(intent);
            }
        }, 3000 );
    }
}
