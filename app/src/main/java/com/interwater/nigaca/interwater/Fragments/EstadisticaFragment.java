package com.interwater.nigaca.interwater.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.interwater.nigaca.interwater.Activities.ReporteActivity;
import com.interwater.nigaca.interwater.Controller.EstadisticaController;
import com.interwater.nigaca.interwater.Controller.SuministroController;
import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.R;

public class EstadisticaFragment extends Fragment {

    Spinner seleccionAños;
    Spinner seleccionMeses;
    Button reporte_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.estadistica, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.seleccionAños = getActivity().findViewById(R.id.seleccionAños);
        this.seleccionMeses = getActivity().findViewById(R.id.seleccionMeses);
        this.reporte_button = getActivity().findViewById(R.id.reporte_button);

        this.reporte_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstadisticaController ec = new EstadisticaController(getActivity().getApplicationContext());
                int mesIndex = ec.mesCorresponde(seleccionMeses.getSelectedItem().toString());
                DatabaseHelper dbh = new DatabaseHelper(getActivity().getApplicationContext());

                if(dbh.isExistReport(mesIndex,Integer.parseInt(seleccionAños.getSelectedItem().toString()))) {

                    Intent i = new Intent(getActivity().getApplicationContext(), ReporteActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("year", seleccionAños.getSelectedItem().toString());
                    i.putExtra("mes", seleccionMeses.getSelectedItem().toString());
                    getActivity().getApplicationContext().startActivity(i);
                }else{
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "No existen registros asociados a ese periodo";
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
