package com.interwater.nigaca.interwater.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Controller.SuministroController;
import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Fecha;
import com.interwater.nigaca.interwater.R;

public class HomeFragment extends Fragment {

    TextView home_fecha;
    TextView Cantidad_suministrada_diario;
    TextView agua_faltante_diario;
    TextView familia_abastecidas_diario;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.home_fecha = getActivity().findViewById(R.id.home_fecha);

        DatabaseHelper dbh = new DatabaseHelper(getActivity().getApplicationContext());

        Fecha f = dbh.getFechaActual();
        this.home_fecha.setText(f.toString());

        this.Cantidad_suministrada_diario = getActivity().findViewById(R.id.Cantidad_suministrada_diario);
        this.agua_faltante_diario = getActivity().findViewById(R.id.agua_faltante_diario);
        this.familia_abastecidas_diario = getActivity().findViewById(R.id.familia_abastecidas_diario);

        SuministroController sc = new SuministroController(getActivity().getApplicationContext());
        this.Cantidad_suministrada_diario.setText(String.valueOf(sc.suministroDiario())+" Lts");
        this.agua_faltante_diario.setText(String.valueOf(sc.suministroFaltanteDiario())+" Lts");
        this.familia_abastecidas_diario.setText(String.valueOf(sc.familiasSuministradasDiario()));
    }
}
