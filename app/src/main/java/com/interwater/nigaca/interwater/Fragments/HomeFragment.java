package com.interwater.nigaca.interwater.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Fecha;
import com.interwater.nigaca.interwater.R;

public class HomeFragment extends Fragment {

    TextView home_fecha;

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


    }
}
