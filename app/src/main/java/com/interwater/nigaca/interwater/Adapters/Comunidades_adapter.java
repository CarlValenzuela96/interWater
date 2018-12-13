package com.interwater.nigaca.interwater.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Activities.ComunidadActivity;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class Comunidades_adapter extends RecyclerView.Adapter<Comunidades_adapter.RepositoryViewHolder> {

    Context context;
    ArrayList<Comunidad> a;

    public Comunidades_adapter(ArrayList<Comunidad> a, Context context ) {

        this.a=a;
        this.context = context;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comunidad_item,null,false);
        return new RepositoryViewHolder(view, this.context);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int i) {
        holder.asignarDatos(this.a.get(i));
    }

    @Override
    public int getItemCount() {
        return this.a.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_comunidad;
        TextView id_comunidad;

        public RepositoryViewHolder(final View itemView, final Context mContext) {
            super(itemView);
            nombre_comunidad = itemView.findViewById(R.id.nombre_comunidad);
            id_comunidad = itemView.findViewById(R.id.id_comunidad);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(),ComunidadActivity.class);

                    i.putExtra("nombre_comunidad", nombre_comunidad.getText());
                    i.putExtra("id_comunidad",id_comunidad.getText());
                    itemView.getContext().startActivity(i);
                }
            });

        }


        public void asignarDatos(Comunidad a){
            nombre_comunidad.setText(a.getNombre_comunidad());
            id_comunidad.setText(String.valueOf(a.getId_comunidad()));
        }

    }
}
