package com.interwater.nigaca.interwater.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Adapters.Comunidades_adapter;
import com.interwater.nigaca.interwater.Adapters.Estidistica_comunidad_adapter;
import com.interwater.nigaca.interwater.Controller.ComunidadController;
import com.interwater.nigaca.interwater.Controller.EstadisticaController;
import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class ReporteActivity extends AppCompatActivity {
    TextView year;
    TextView mes;
    TextView personas_abs;
    TextView total_abs;
    Button backButton;
    Button guardarInf;

    public RecyclerView comunidades;
    public Estidistica_comunidad_adapter eAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte);

        Bundle extras = getIntent().getExtras();
        String year = (String) extras.get("year");
        String mes = (String) extras.get("mes");

        this.year = findViewById(R.id.reporte_año);
        this.mes = findViewById(R.id.reporte_mes);
        this.personas_abs = findViewById(R.id.personas_abs);
        this.total_abs = findViewById(R.id.total_abs);

        this.year.setText(year);
        this.mes.setText(mes);

        EstadisticaController ec = new EstadisticaController(this);

        int mesIndex = ec.mesCorresponde(mes);
        int personasAbastecidas = ec.personasAbastecidas(mesIndex,Integer.parseInt(year));
        this.personas_abs.setText(String.valueOf(personasAbastecidas));

        int totalAbastecido = ec.totalAbastecido(mesIndex,Integer.parseInt(year));
        this.total_abs.setText(String.valueOf(totalAbastecido)+" Lts");


        ComunidadController cController  = new ComunidadController(this);
        ArrayList<Comunidad> com = cController.getAllComunidades();


        this.comunidades = (RecyclerView) findViewById(R.id.comunidad_list_total_reporte);
        this.comunidades.setLayoutManager( new LinearLayoutManager(this));

        this.comunidades.setHasFixedSize(true);
        this.eAdapter = new Estidistica_comunidad_adapter(com,mesIndex,Integer.parseInt(year));
        this.comunidades.setAdapter(eAdapter);

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        this.guardarInf = findViewById(R.id.guardar_reporte_button);
        this.guardarInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),RespaldoActivity.class);
                v.getContext().startActivity(i);
            }
        });

    }
}
