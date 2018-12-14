package com.interwater.nigaca.interwater.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.interwater.nigaca.interwater.Adapters.Comunidad_select_adapter;
import com.interwater.nigaca.interwater.Controller.PersonaController;
import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class ComunidadActivity extends AppCompatActivity {

    public RecyclerView usuarios;
    public Comunidad_select_adapter cAdapter;
    public TextView nombreComunidad;
    public Button backButton;
    public android.support.v7.widget.SearchView search_action;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comunidad_formulario);

        Bundle extras = getIntent().getExtras();
        String nombreCom = (String) extras.get("nombre_comunidad");
        String idComStr = (String) extras.get("id_comunidad");
        Long idCom = Long.parseLong(idComStr);

        this.nombreComunidad = findViewById(R.id.nombre_comunidad_seleccionada);

        this.nombreComunidad.setText(nombreCom);

        PersonaController pController = new PersonaController(this);

        ArrayList<Persona> users = pController.getAllPersonasOfComunidad(idCom);

        this.usuarios = findViewById(R.id.comunidad_list);
        this.usuarios.setLayoutManager( new LinearLayoutManager(this));

        this.usuarios.setHasFixedSize(true);
        this.cAdapter = new Comunidad_select_adapter(users);
        this.usuarios.setAdapter(cAdapter);


        this.search_action = findViewById(R.id.search_action);
        this.search_action.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cAdapter.getFilter().filter(newText);
                return false;
            }
        });


        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
