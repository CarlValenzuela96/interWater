package com.interwater.nigaca.interwater.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Controller.EstadisticaController;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class Estidistica_comunidad_adapter extends RecyclerView.Adapter<Estidistica_comunidad_adapter.RepositoryViewHolder> {

    ArrayList<Comunidad> comunidades;
    int year;
    int mes;

    public Estidistica_comunidad_adapter(ArrayList<Comunidad> comunidades, int mes, int year) {
        this.comunidades = comunidades;
        this.mes = mes;
        this.year = year;
    }

    @NonNull
    @Override
    public Estidistica_comunidad_adapter.RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estadistica_comunidad__item,null,false);
        return new Estidistica_comunidad_adapter.RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Estidistica_comunidad_adapter.RepositoryViewHolder holder, int i) {
        holder.asignarDatos(this.comunidades.get(i),this.mes,this.year);
    }

    @Override
    public int getItemCount() {
        return this.comunidades.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView reporte_comunidad_nombre;
        TextView reporte_comunidad_personas;
        TextView reporte_comunidad_total;
        Context context;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.reporte_comunidad_nombre = itemView.findViewById(R.id.reporte_comunidad_nombre);
            this.reporte_comunidad_personas = itemView.findViewById(R.id.reporte_comunidad_personas);
            this.reporte_comunidad_total = itemView.findViewById(R.id.reporte_comunidad_total);
        }

        public void asignarDatos(Comunidad c, int mes, int year){
            EstadisticaController ec = new EstadisticaController(this.context);
            int personasAbastecidas = ec.personasAbastecidasComunidad(c,mes,year);
            int totalAbastecido = ec.totalAbastecidoComunidad(c,mes,year);

            this.reporte_comunidad_nombre.setText(c.getNombre_comunidad());
            this.reporte_comunidad_personas.setText(String.valueOf(personasAbastecidas));
            this.reporte_comunidad_total.setText(String.valueOf(totalAbastecido)+" Lts");
        }
    }
}
