package com.example.omar10ibanez.serviciosenfermeria.interfaz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.chat.Chat;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ConsultarServicioEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ConsultarServiciosAceptados;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.EnfermeraLogged;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.PublicarServicioEnfermera;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.ServiciosAceptados;
import com.example.omar10ibanez.serviciosenfermeria.mapa.Mapa;

public class NavigationDrawerEnfermera extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nombrenurse, apellidonurse, correonurse;
    ImageView fotonurse;
    private EnfermeraLogged enfermeraLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_enfermera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombrenurse = (TextView)findViewById(R.id.Enfermeranombrenavigation);
        apellidonurse = (TextView)findViewById(R.id.Enfermeraapellidonavigation);
        correonurse = (TextView)findViewById(R.id.Enfermeracorreonavigation);
        fotonurse = (ImageView)findViewById(R.id.EnfermeraFotonavigation);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.ConstraintDrawerEnfermera, new PublicarServicioEnfermera()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_enfermera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_listaServicios) {

             fragmentManager.beginTransaction().replace(R.id.ConstraintDrawerEnfermera, new ConsultarServicioEnfermera()).commit();


        } else if (id == R.id.nav_serviciosAceptados) {
            fragmentManager.beginTransaction().replace(R.id.ConstraintDrawerEnfermera, new ConsultarServiciosAceptados()).commit();
        } else if (id == R.id.nav_publicService) {
            fragmentManager.beginTransaction().replace(R.id.ConstraintDrawerEnfermera, new PublicarServicioEnfermera()).commit();

        }else if (id==R.id.nav_sCloseEnfer) {

            Intent intent = new Intent(NavigationDrawerEnfermera.this,  Perfil.class);
            startActivity(intent);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
