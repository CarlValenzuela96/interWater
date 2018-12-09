package com.interwater.nigaca.interwater.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class Comunidades_adapter extends RecyclerView.Adapter<Comunidades_adapter.RepositoryViewHolder> {

    int globalPosition;
    ArrayList<String> a;

    public Comunidades_adapter(ArrayList<String> a) {

        this.a=a;

    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comunidad_item,null,false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int i) {
        holder.asignarDatos(this.a.get(i),i);
    }

    @Override
    public int getItemCount() {
        return this.a.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_comunidad;
        TextView id_comunidad;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            nombre_comunidad = itemView.findViewById(R.id.nombre_comunidad);
            id_comunidad = itemView.findViewById(R.id.id_comunidad);
        }


        public void asignarDatos(String a,int i){
            nombre_comunidad.setText(a);
            id_comunidad.setText(String.valueOf(i+1));
        }

    }
}
