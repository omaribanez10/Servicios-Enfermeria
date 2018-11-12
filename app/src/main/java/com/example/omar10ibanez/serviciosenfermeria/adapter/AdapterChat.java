package com.example.omar10ibanez.serviciosenfermeria.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.example.omar10ibanez.serviciosenfermeria.chat.Mensaje;

import java.util.List;

/**
 * Created by Omar10ibañez on 12/11/2017.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolder> {

    List<Mensaje>mensajes;

    public AdapterChat(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflada= LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_chat,parent,false);
        return new ViewHolder(inflada);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.enviador.setText(mensajes.get(position).getNombreEnviador());
        holder.mensaje.setText(mensajes.get(position).getMensaje());

    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView enviador,mensaje;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardViewchat);
            enviador=(TextView)itemView.findViewById(R.id.enviador);
            mensaje=(TextView)itemView.findViewById(R.id.mensaje);
        }
    }
}
