package com.interwater.nigaca.interwater.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Adapters.Comunidad_select_adapter;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class ComunidadActivity extends AppCompatActivity {

    public RecyclerView usuarios;
    public Comunidad_select_adapter cAdapter;
    public TextView nombreComunidad;
    public Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comunidad_formulario);

        Bundle extras = getIntent().getExtras();
        String nombreCom = (String) extras.get("nombre_comunidad");
        this.nombreComunidad = findViewById(R.id.nombre_comunidad_seleccionada);

        this.nombreComunidad.setText(nombreCom);

        ArrayList<String> users = new ArrayList<>();
        users.add("gabriel");
        users.add("carlos");
        users.add("miguel");
        users.add("nicolas");

        this.usuarios = findViewById(R.id.comunidad_list);
        this.usuarios.setLayoutManager( new LinearLayoutManager(this));

        this.usuarios.setHasFixedSize(true);
        this.cAdapter = new Comunidad_select_adapter(users);
        this.usuarios.setAdapter(cAdapter);

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
