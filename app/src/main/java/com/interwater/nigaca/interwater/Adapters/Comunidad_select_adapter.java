package com.interwater.nigaca.interwater.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class Comunidad_select_adapter extends RecyclerView.Adapter<Comunidad_select_adapter.RepositoryViewHolder> {

    ArrayList<Persona> arr;

    public Comunidad_select_adapter(ArrayList<Persona> arr) {

    this.arr = arr;
    }

    @Override
    public Comunidad_select_adapter.RepositoryViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comunidad_formulario_item,null,false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Comunidad_select_adapter.RepositoryViewHolder holder, int i) {
        holder.asignarDatos(this.arr.get(i));
    }

    @Override
    public int getItemCount() {
        return this.arr.size();
    }


    public class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView corresponde_agua;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.nombre_persona);
            this.corresponde_agua = itemView.findViewById(R.id.corresponde_agua);

        }

        public void asignarDatos(Persona a){

            nombre.setText(a.getNombre_persona()+" "+a.getApellido_paterno());
            corresponde_agua.setText(String.valueOf(a.getAgua_corresponde())+" Lts");
        }
    }
}
