package com.example.omar10ibanez.serviciosenfermeria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.chat.Chat;
import com.example.omar10ibanez.serviciosenfermeria.consulta.Servicio;
import com.example.omar10ibanez.serviciosenfermeria.interfaz.NavigationDrawerUser;
import com.example.omar10ibanez.serviciosenfermeria.usuario.UsuarioLogged;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar10ibañez on 25/11/2017.
 */

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServiciosHolder> {

    ArrayList<Servicio> listaServi;
    Dialog dialogServicios;
    RecyclerView listaserviciosRecycler;
    Context ctx;
    View vista;

    UsuarioLogged usuarioLogged;




    public ServiciosAdapter(ArrayList<Servicio> listaServi, Context ctx){


        this.listaServi = listaServi;
        this.ctx =ctx;

    }

    @Override
    public ServiciosHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

       vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_servicios,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        final ServiciosHolder serviciosHolder = new ServiciosHolder(vista,ctx,listaServi);




        dialogServicios = new Dialog(parent.getContext());
        dialogServicios.setContentView(R.layout.dialog_ver_info_servicios);





         serviciosHolder.servicioLayoutItem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {



                 TextView tituloDialog, descripcionDialog, telefonoDialog, fechaDialog, horaDialog, direccionDialaog ,
                         nombreUserDialog, apellidoUserDialog;

                 final EditText enfermeraIDservicio;
                 final TextView idServidialog;
                 Button aceptar, escribir;





                 tituloDialog = (TextView)dialogServicios.findViewById(R.id.tituloservicioinfodialog);
                 descripcionDialog = (TextView)dialogServicios.findViewById(R.id.descripciondervicioinfodialog);
                 telefonoDialog = (TextView)dialogServicios.findViewById(R.id.telefonoserivicioinfodialog);
                 fechaDialog = (TextView)dialogServicios.findViewById(R.id.fechaservicioinfodialog);
                 horaDialog = (TextView)dialogServicios.findViewById(R.id.horaservicioinfodialog);
                 direccionDialaog = (TextView) dialogServicios.findViewById(R.id.direccionservicioinfodialog);
                 nombreUserDialog= (TextView) dialogServicios.findViewById(R.id.nombreUserservicioinfodialog);
                 apellidoUserDialog = (TextView) dialogServicios.findViewById(R.id.apellidoUserservicioinfodialog);
                 idServidialog = (TextView) dialogServicios.findViewById(R.id.idservicioinfodialog);
                // enfermeraIDservicio = (EditText) dialogServicios.findViewById(R.id.enfermeraIDservicio);



                 aceptar = (Button) dialogServicios.findViewById(R.id.buttonaceptarservicioinfodialog);
                 escribir = (Button) dialogServicios.findViewById(R.id.buttonescribirinfodialog);



                 tituloDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getNombreser().toString());
                 descripcionDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getDescripcionser().toString());
                 telefonoDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getTelefonoser().toString());
                 fechaDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getFechaser().toString());
                 horaDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getHoraser().toString());
                 direccionDialaog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getDireccionser().toString());
                 nombreUserDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getNombreUser().toString());
                 apellidoUserDialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getApellidoUser().toString());
                 idServidialog.setText(listaServi.get(serviciosHolder.getAdapterPosition()).getIdServicio().toString());


                 dialogServicios.show();


             //    if(!enfermeraIDservicio.getText().toString().trim().equalsIgnoreCase("")) {
                     aceptar.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {


                             OkHttpClient objeto = new OkHttpClient();
                             RequestBody formBody = new FormEncodingBuilder()
                                     .add("idEnfermera", "14")
                                     .add("idServicio", idServidialog.getText().toString())
                                     .build();

                             //  String ip = String.valueOf(( R.string.ip));
                             Request request = new Request.Builder().url("http://172.17.11.225/serviciosEnfermer%C3%ADa/php2/aceptar_servicio.php").post(formBody).build();

                             Call call = objeto.newCall(request);
                             call.enqueue(new Callback() {
                                 @Override
                                 public void onFailure(Request request, IOException e) {
                                     Log.i("error", "error en " + e);
                                 }

                                 @Override
                                 public void onResponse(Response response) throws IOException {

                                     dialogServicios.dismiss();

                                 }
                             });
                         }

                     });



                escribir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent llevar = new Intent(vista.getContext(), Chat.class);
                        ctx.startActivity(llevar);
                    }

                });

             }
         });





        return serviciosHolder;




    }
    @Override
    public void onBindViewHolder(ServiciosAdapter.ServiciosHolder holder, int position) {

        holder.idServicio.setText(listaServi.get(position).getIdServicio().toString());
        holder.nombre.setText(listaServi.get(position).getNombreser().toString());
        holder.descripcion.setText(listaServi.get(position).getDescripcionser().toString());
        holder.telefono.setText(listaServi.get(position).getTelefonoser().toString());
        holder.direccion.setText(listaServi.get(position).getDireccionser().toString());
        holder.fecha.setText(listaServi.get(position).getFechaser().toString());
        holder.hora.setText(listaServi.get(position).getHoraser().toString());
        holder.nombreUser.setText(listaServi.get(position).getNombreUser().toString());
        holder.apellidoUser.setText(listaServi.get(position).getApellidoUser().toString());


    }

    @Override
    public int getItemCount() {
            return listaServi.size();
    }



    public class ServiciosHolder extends RecyclerView.ViewHolder {

        TextView idServicio,nombre,descripcion,telefono,direccion,fecha,hora, nombreUser, apellidoUser;
        LinearLayout servicioLayoutItem;
        List<Servicio> listaServi;
        Context ctx;


        public ServiciosHolder(View itemView, final Context ctx,List<Servicio> listaServi) {

            super(itemView);
            this.listaServi = listaServi;
            this.ctx = ctx;



            servicioLayoutItem = (LinearLayout) itemView.findViewById(R.id.servicioLayoutItem);
            listaserviciosRecycler = (RecyclerView)itemView.findViewById(R.id.recyclerviewServiList);


            idServicio = (TextView)itemView.findViewById(R.id.IdServicioDB);
            nombre = (TextView)itemView.findViewById(R.id.TituloServiDB);
            descripcion = (TextView)itemView.findViewById(R.id.descripcionServiBD);
            telefono = (TextView)itemView.findViewById(R.id.telefonoServiDB);
            fecha = (TextView)itemView.findViewById(R.id.FechaServiDB);
            hora = (TextView)itemView.findViewById(R.id.HoraServiDB);
            direccion = (TextView)itemView.findViewById(R.id.direccionServiBD);
            nombreUser =(TextView)itemView.findViewById(R.id.NombreuserServiDB);
            apellidoUser =(TextView)itemView.findViewById(R.id.ApellidoUserServiDB);




        }
    }
}
