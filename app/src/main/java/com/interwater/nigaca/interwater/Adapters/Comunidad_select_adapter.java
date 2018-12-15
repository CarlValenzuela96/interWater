package com.interwater.nigaca.interwater.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.interwater.nigaca.interwater.Activities.ComunidadActivity;
import com.interwater.nigaca.interwater.Controller.SuministroController;
import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Fecha;
import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.Models.Suministro;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class Comunidad_select_adapter extends RecyclerView.Adapter<Comunidad_select_adapter.RepositoryViewHolder> implements Filterable {

    ArrayList<Persona> arr;
    ArrayList<Persona> arrFull;

    public Comunidad_select_adapter(ArrayList<Persona> arr) {
    this.arr = arr;
    this.arrFull = new ArrayList<>(arr);
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

    @Override
    public Filter getFilter() {
        return personaFilter;
    }

    private Filter personaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Persona> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(arrFull);

            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Persona persona : arrFull){
                    if(persona.toString().trim().toLowerCase().contains(filterPattern)){
                        filteredList.add(persona);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arr.clear();
            arr.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView corresponde_agua;
        TextView fecha_entrega;
        DatabaseHelper databaseHelper;
        Button guardar_suministro_button;
        TextInputEditText cant_agua_entregada;
        CheckBox check_recepcion;

        Persona p ;
        Fecha f;

        public RepositoryViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.nombre_persona);
            this.corresponde_agua = itemView.findViewById(R.id.corresponde_agua);
            this.fecha_entrega = itemView.findViewById(R.id.fecha_entrega);
            this.databaseHelper = new DatabaseHelper(itemView.getContext());
            this.cant_agua_entregada = itemView.findViewById(R.id.cant_agua_entregada);
            this.check_recepcion = itemView.findViewById(R.id.check_recepcion);
            this.guardar_suministro_button = itemView.findViewById(R.id.guardar_suministro_button);

            this.check_recepcion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        guardar_suministro_button.setEnabled(true);
                    }else{
                        guardar_suministro_button.setEnabled(false);
                    }
                }
            });

            this.guardar_suministro_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!cant_agua_entregada.getText().toString().isEmpty()) {
                        int cantAgua = Integer.valueOf(String.valueOf(cant_agua_entregada.getText()));
                        Suministro s = new Suministro(cantAgua, f, p);
                        SuministroController suministroController = new SuministroController(itemView.getContext());
                        suministroController.addSuministro(s);

                        Context context = itemView.getContext();
                        CharSequence text = "Suministro agregado correctamente";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }else{
                        Context context = itemView.getContext();
                        CharSequence text = "Ingresar cantidad de agua correspondiente";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            });


        }

        public void asignarDatos(Persona a){
            this.p = a;

            nombre.setText(a.getNombre_persona()+" "+a.getApellido_paterno());
            corresponde_agua.setText(String.valueOf(a.getAgua_corresponde())+" Lts");

            this.f = databaseHelper.getFechaActual();
            this.fecha_entrega.setText(f.toString());
        }



    }
}
