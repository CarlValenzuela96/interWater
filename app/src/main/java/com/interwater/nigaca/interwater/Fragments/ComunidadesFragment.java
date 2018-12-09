package com.interwater.nigaca.interwater.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interwater.nigaca.interwater.Adapters.Comunidades_adapter;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class ComunidadesFragment extends Fragment {

    public RecyclerView comunidades;
    public Comunidades_adapter cAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comunidades, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> com = new ArrayList<>();
        com.add("comunidad 1");
        com.add("comunidad 2");
        com.add("comunidad 3");
        com.add("comunidad 3");
        com.add("comunidad 3");
        com.add("comunidad 3");
        com.add("comunidad 3");

        this.comunidades = (RecyclerView) getActivity().findViewById(R.id.comunidades_list);
        this.comunidades.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        this.comunidades.setHasFixedSize(true);
        this.cAdapter = new Comunidades_adapter(com);
        this.comunidades.setAdapter(cAdapter);
    }
}

