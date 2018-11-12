package com.example.omar10ibanez.serviciosenfermeria.interfaz;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ConsultarEnfermeros;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ConsultarOfertas;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.PublicarServicioEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.usuario.Publicar_Servicio;
import com.example.omar10ibanez.serviciosenfermeria.usuario.UsuarioLogged;

public class NavigationDrawerUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int ident;
    private UsuarioLogged usuarioLogged;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/**

        View header = ((NavigationView)findViewById(R.id.nav_viewuser)).getHeaderView(0);
        ImageView photo = (ImageView)header.findViewById(R.id.UsuarioFotonavigaton);

        try{
            Bundle bundle = getIntent().getExtras();
            usuarioLogged = bundle.getParcelable("usuariologged");
            if(bundle!=null){
                ident = usuarioLogged.getIdUserlogged();

                ((TextView) header.findViewById(R.id.UsuarioNombrenavigation)).setText(usuarioLogged.getNombreuserlogged());
                ((TextView) header.findViewById(R.id.UsuarioApellidonavigation)).setText(usuarioLogged.getApellidouserlogged());
                ((TextView) header.findViewById(R.id.UsuarioCorreonavigation)).setText(usuarioLogged.getCorreouserlogged());


                  if(!usuarioLogged.getFotouserlogged().equals("null")){
                 String ip = getString(R.string.ip);
                 String url_image = ip+"serviciosEnfermer%C3%ADa/"+usuarioLogged.getFotouserlogged();
                 url_image = url_image.replace(" ","%20");

                 try {
                 Log.i("RESPUESTA IMAGE: ",""+url_image);
                 Glide.with(this).load(url_image).into(photo);
                 } catch (Exception e) {
                 e.printStackTrace();
                 }
                 }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
 **/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutuser);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewuser);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedorDrawer, new Publicar_Servicio ()).commit();
    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutuser);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_pubSer) {

            fragmentManager.beginTransaction().replace(R.id.contenedorDrawer, new Publicar_Servicio()).commit();


        } else if (id == R.id.nav_verEnf) {
            fragmentManager.beginTransaction().replace(R.id.contenedorDrawer, new ConsultarEnfermeros()).commit();

        } else if (id == R.id.nav_ofertasEnfer) {

            fragmentManager.beginTransaction().replace(R.id.contenedorDrawer, new ConsultarOfertas()).commit();



        }


        else if (id == R.id.salirUser) {

            Intent intent = new Intent(NavigationDrawerUser.this, Perfil.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutuser);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
