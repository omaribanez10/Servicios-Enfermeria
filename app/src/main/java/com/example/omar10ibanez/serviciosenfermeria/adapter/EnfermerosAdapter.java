package com.example.omar10ibanez.serviciosenfermeria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.consulta.Enfermera;
import com.example.omar10ibanez.serviciosenfermeria.consulta.EnfermeraDialog;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Omar10ibañez on 16/11/2017.
 */

public class EnfermerosAdapter extends RecyclerView.Adapter<EnfermerosAdapter.EnfermerosHolder> {


   ArrayList<Enfermera> listaEnfermeros = new ArrayList<>();
    ArrayList<EnfermeraDialog> listaEnfermerosDialog;
    RecyclerView listaenfermerosRe;
     Dialog dialogenfermeras;
    Context ctx;

    public EnfermerosAdapter(ArrayList<Enfermera> listaEnfermeros, Context ctx){

        this.listaEnfermeros = listaEnfermeros;
        this.ctx =ctx;


    }




    @Override
    public EnfermerosHolder onCreateViewHolder(final ViewGroup parent, int viewType ){

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_enfermeros,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

         final EnfermerosHolder enfermerosHolder = new EnfermerosHolder(vista,ctx, listaEnfermeros);



        dialogenfermeras = new Dialog(parent.getContext());
        dialogenfermeras.setContentView(R.layout.dialog_ver_info_enfermeros);





       enfermerosHolder.enfermeraLayoutItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               TextView nombreDialog, apellidoDialog, telefonoDialog, calificacionDialog, experienciaDialog;
               ImageView fotoDialog;

               nombreDialog = (TextView)dialogenfermeras.findViewById(R.id.nombreEnfermeradialog);
               apellidoDialog = (TextView)dialogenfermeras.findViewById(R.id.apellidoEnfermeradialog);
               telefonoDialog = (TextView)dialogenfermeras.findViewById(R.id.telefonoEnfermeradialog);
               calificacionDialog = (TextView)dialogenfermeras.findViewById(R.id.calificacionEnfermeradialog);
               experienciaDialog = (TextView)dialogenfermeras.findViewById(R.id.descripciontrabajoEnfermeradialog);
               fotoDialog = (ImageView) dialogenfermeras.findViewById(R.id.fotoEnfermeradialog);

               nombreDialog.setText(listaEnfermeros.get(enfermerosHolder.getAdapterPosition()).getNombre());
               apellidoDialog.setText(listaEnfermeros.get(enfermerosHolder.getAdapterPosition()).getApellido());
               fotoDialog.setImageURI(Uri.parse(listaEnfermeros.get(enfermerosHolder.getAdapterPosition()).getFoto()));
               telefonoDialog.setText("37846789743");
               calificacionDialog.setText("3");
               experienciaDialog.setText("iAWDHYISADJKLJSALDJLJL KFHDSKFJH osdfuisd");


/**
               nombreDialog.setText(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getNombre());
               apellidoDialog.setText(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getApellido());
               fotoDialog.setImageURI(Uri.parse(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getFoto()));
               telefonoDialog.setText(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getTelefono());
               calificacionDialog.setText(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getCalificacion());
               experienciaDialog.setText(listaEnfermerosDialog.get(enfermerosHolder.getAdapterPosition()).getDesripcionTrabajo());
               **/

               dialogenfermeras.show();

           }
       });




       return enfermerosHolder;
    }

    @Override
    public void onBindViewHolder(EnfermerosAdapter.EnfermerosHolder holder, int position) {


        holder.nombre.setText(listaEnfermeros.get(position).getNombre().toString());
        holder.apellido.setText(listaEnfermeros.get(position).getApellido().toString());
        holder.foto.setImageURI(Uri.parse(listaEnfermeros.get(position).getFoto()));


    }

    @Override
    public int getItemCount() {
        return listaEnfermeros.size();



    }


    public class EnfermerosHolder extends  RecyclerView.ViewHolder  {

     TextView nombre,apellido;
     ImageView foto;
     ArrayList<Enfermera> listaEnfermeros = new ArrayList<>();
     Context ctx;
     LinearLayout enfermeraLayoutItem;

        public EnfermerosHolder(View itemView,Context ctx, ArrayList<Enfermera> listaenfermeros){

            super(itemView);
            this.listaEnfermeros = listaenfermeros;
            this.ctx = ctx;



            enfermeraLayoutItem = (LinearLayout) itemView.findViewById(R.id.enfermeraLayoutItem);
            listaenfermerosRe = (RecyclerView) itemView.findViewById(R.id.recyclerviewEnfermeraList);
            nombre = (TextView)itemView.findViewById(R.id.nombreEnfermeragetDB);
            apellido = (TextView)itemView.findViewById(R.id.apellidoEnfermeragetDB);
            foto = (ImageView)itemView.findViewById(R.id.fotoPerfilenfermeraDB);

        }


    }
}
