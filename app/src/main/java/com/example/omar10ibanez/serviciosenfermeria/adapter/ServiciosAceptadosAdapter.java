package com.example.omar10ibanez.serviciosenfermeria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.consulta.ServicioAceptado;
import com.example.omar10ibanez.serviciosenfermeria.enfermera.EnfermeraLogged;
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

/**
 * Created by Omar10ibañez on 10/05/2018.
 */

public class ServiciosAceptadosAdapter extends RecyclerView.Adapter<ServiciosAceptadosAdapter.ServiciosAceptadosHolder> {


    ArrayList<ServicioAceptado> listaServiciosAceptados ;
    Dialog dialogServiciosaceptados;
    RecyclerView listaserviciosaceptadosRecycler;
    Context ctx;
    View vista;


    //final String ip = getString( R.string.ip);



    public ServiciosAceptadosAdapter(ArrayList<ServicioAceptado> listaServiciosAceptados, Context ctx){


        this.listaServiciosAceptados = listaServiciosAceptados;
        this.ctx =ctx;

    }


    @Override
    public ServiciosAceptadosHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_servicios_aceptados,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        final ServiciosAceptadosHolder serviciosAceptadosHolder = new ServiciosAceptadosHolder(vista,ctx,listaServiciosAceptados);



        dialogServiciosaceptados = new Dialog(parent.getContext());
        dialogServiciosaceptados.setContentView(R.layout.dialog_servicios_aceptados_enfermera);

        serviciosAceptadosHolder.serviciosAceptadosLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tituloDialogacept, descripcionDialogacept, telefonoDialogacept, direccionDialogacept ,
                        nombreUserDialogacept, apellidoUserDialogacept;

                final TextView idServidialogaceptado;

                Button cancelar;
                Button escribir;

                tituloDialogacept = (TextView)dialogServiciosaceptados.findViewById(R.id.tituloservicioaceptadodialog);
                descripcionDialogacept = (TextView)dialogServiciosaceptados.findViewById(R.id.descripciondervicioaceptadodialog);
                telefonoDialogacept = (TextView)dialogServiciosaceptados.findViewById(R.id.telefonoserivicioaceptadodialog);
                direccionDialogacept = (TextView) dialogServiciosaceptados.findViewById(R.id.direccionservicioaceptadodialog);
                nombreUserDialogacept= (TextView) dialogServiciosaceptados.findViewById(R.id.nombreUserservicioaceptadodialog);
                apellidoUserDialogacept = (TextView) dialogServiciosaceptados.findViewById(R.id.apellidoUserservicioaceptadodialog);
                idServidialogaceptado = (TextView) dialogServiciosaceptados.findViewById(R.id.idservicioaceptadodialog);


                cancelar = (Button) dialogServiciosaceptados.findViewById(R.id.buttoncancelarservicioaceptadodialog);
                escribir = (Button) dialogServiciosaceptados.findViewById(R.id.buttonescribiraceptadodialog);


                tituloDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getNombreser().toString());
                descripcionDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getDescripcionser().toString());
                telefonoDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getTelefonoser().toString());
                direccionDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getDireccionser().toString());
                nombreUserDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getNombreUser().toString());
                apellidoUserDialogacept.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getApellidoUser().toString());
                idServidialogaceptado.setText(listaServiciosAceptados.get(serviciosAceptadosHolder.getAdapterPosition()).getIdServicio().toString());

                dialogServiciosaceptados.show();

               cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            OkHttpClient objeto = new OkHttpClient();
                            RequestBody formBody = new FormEncodingBuilder()
                                    .add("idServicio", idServidialogaceptado.getText().toString())
                                    .build();


                            Request request = new Request.Builder().url("http://172.17.11.225/serviciosEnfermer%C3%ADa/php2/cancelar_servicio.php").post(formBody).build();

                            Call call = objeto.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Request request, IOException e) {
                                    Log.i("error", "error en " + e);
                                }

                                @Override
                                public void onResponse(Response response) throws IOException {

                                    Toast.makeText(ctx, "Transaccion hecha ", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                });


            }
        });

        return serviciosAceptadosHolder;
    }

    @Override
    public void onBindViewHolder(ServiciosAceptadosAdapter.ServiciosAceptadosHolder holder, int position) {

        holder.idServicio.setText(listaServiciosAceptados.get(position).getIdServicio().toString());
        holder.nombre.setText(listaServiciosAceptados.get(position).getNombreser().toString());
        holder.descripcion.setText(listaServiciosAceptados.get(position).getDescripcionser().toString());
        holder.telefono.setText(listaServiciosAceptados.get(position).getTelefonoser().toString());
        holder.direccion.setText(listaServiciosAceptados.get(position).getDireccionser().toString());
        holder.nombreUser.setText(listaServiciosAceptados.get(position).getNombreUser().toString());
        holder.apellidoUser.setText(listaServiciosAceptados.get(position).getApellidoUser().toString());

    }

    @Override
    public int getItemCount() {
        return listaServiciosAceptados.size();
    }

    public class ServiciosAceptadosHolder extends RecyclerView.ViewHolder {
        TextView idServicio,nombre,descripcion,telefono,direccion, nombreUser, apellidoUser;
        LinearLayout serviciosAceptadosLayoutItem;
        ArrayList<ServicioAceptado> listaServiciosAceptados = new ArrayList<ServicioAceptado>();
        Context ctx;



        public ServiciosAceptadosHolder(View itemView, Context ctx, ArrayList<ServicioAceptado> listaServiciosAceptados) {

            super(itemView);
            this.listaServiciosAceptados = listaServiciosAceptados;
            this.ctx = ctx;


            serviciosAceptadosLayoutItem = (LinearLayout) itemView.findViewById(R.id.serviciosAceptadosLayoutItem);
            listaserviciosaceptadosRecycler = (RecyclerView)itemView.findViewById(R.id.recyclerviewServiciosAceptadosList);


            idServicio = (TextView)itemView.findViewById(R.id.IdServicioAceptado);
            nombre = (TextView)itemView.findViewById(R.id.TituloServicioAceptado);
            descripcion = (TextView)itemView.findViewById(R.id.descripcionServicioAceptado);
            telefono = (TextView)itemView.findViewById(R.id.telefonoServicioAceptado);
            direccion = (TextView)itemView.findViewById(R.id.direccionServicioAceptado);
            nombreUser =(TextView)itemView.findViewById(R.id.NombreuserServicioAceptado);
            apellidoUser =(TextView)itemView.findViewById(R.id.ApellidoUserServicioAceptado);




        }
    }



}
