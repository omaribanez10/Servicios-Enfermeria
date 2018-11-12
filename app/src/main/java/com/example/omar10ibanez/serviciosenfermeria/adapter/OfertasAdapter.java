package com.example.omar10ibanez.serviciosenfermeria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.consulta.Oferta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar10ibañez on 3/03/2018.
 */

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.OfertasHolder> {

    Dialog dialogofertas;
    ArrayList<Oferta> listaOfertas;
    Context ctx;


    public OfertasAdapter(ArrayList<Oferta> listaOfertas, Context ctx){

        this.listaOfertas = listaOfertas;
        this.ctx =ctx;
    }
    @Override
    public OfertasAdapter.OfertasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_ofertas,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        final  OfertasHolder ofertasHolder = new OfertasHolder(vista);


        dialogofertas = new Dialog(parent.getContext());
        dialogofertas.setContentView(R.layout.dialog_ver_info_oferta);


        ofertasHolder.ofertaLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView tituloDialog, descripcionDialog, telefonoDialog, fechaDialog, horaDialog, nombre, apellido, calificacion;
                Button escribirusertonurse;


                tituloDialog = (TextView) dialogofertas.findViewById(R.id.tituloofertadialog);
                descripcionDialog = (TextView) dialogofertas.findViewById(R.id.descripcionofertadialog);
                telefonoDialog = (TextView) dialogofertas.findViewById(R.id.telefonoofertadialog);
                fechaDialog = (TextView) dialogofertas.findViewById(R.id.fechaofertadialog);
                horaDialog = (TextView) dialogofertas.findViewById(R.id.horaofertadialog);
                nombre = (TextView) dialogofertas.findViewById(R.id.nombrenurseofertadialog);
                apellido = (TextView) dialogofertas.findViewById(R.id.apellidonurseofertadialog);
                calificacion = (TextView) dialogofertas.findViewById(R.id.calificacionnurseofertadialog);

                escribirusertonurse = (Button) dialogofertas.findViewById(R.id.buttonescribirofertadialog);


                tituloDialog.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getTitulo());
                descripcionDialog.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getDescripcion());
                telefonoDialog.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getTelefono());
                fechaDialog.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getFecha());
                horaDialog.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getHora());
                nombre.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getNombreNurse());
                apellido.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getApellido());
                calificacion.setText(listaOfertas.get(ofertasHolder.getAdapterPosition()).getCalificacionNurse());


                dialogofertas.show();

                escribirusertonurse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });



            }
        });



        return  ofertasHolder;
    }

    @Override
    public void onBindViewHolder(OfertasAdapter.OfertasHolder holder, int position) {
        holder.titulo.setText(listaOfertas.get(position).getTitulo().toString());
        holder.descripcion.setText(listaOfertas.get(position).getDescripcion().toString());
        holder.telefono.setText(listaOfertas.get(position).getTelefono().toString());
        holder.fecha.setText(listaOfertas.get(position).getFecha().toString());
        holder.hora.setText(listaOfertas.get(position).getHora().toString());
        holder.nombrenurse.setText(listaOfertas.get(position).getNombreNurse().toString());
        holder.apellidonurse.setText(listaOfertas.get(position).getApellido().toString());
        holder.califiacionnurse.setText(listaOfertas.get(position).getCalificacionNurse().toString());


    }

    @Override
    public int getItemCount() {
        return listaOfertas.size();
    }

    public class OfertasHolder extends  RecyclerView.ViewHolder{

        TextView titulo,descripcion,telefono,fecha,hora, nombrenurse, apellidonurse, califiacionnurse;
        LinearLayout ofertaLayoutItem;

        public OfertasHolder(View vista) {
            super(vista);
            ofertaLayoutItem = (LinearLayout)itemView.findViewById(R.id.ofertaLayoutItem);

            titulo = (TextView)itemView.findViewById(R.id.tituloOfertaDB);
            descripcion = (TextView)itemView.findViewById(R.id.DescripcionOfertaDB);
            fecha = (TextView)itemView.findViewById(R.id.FechaOfertaDB);
            telefono = (TextView)itemView.findViewById(R.id.telefonoOfertaDB);
            hora = (TextView)itemView.findViewById(R.id.HoraOfertaDB);
            nombrenurse =(TextView)itemView.findViewById(R.id.nombreNurseOfertaDB);
            apellidonurse =(TextView)itemView.findViewById(R.id.ApelldoNurseOfertaDB);
            califiacionnurse  =(TextView)itemView.findViewById(R.id.calificacionNurseOfertaDB);



        }
    }
}
