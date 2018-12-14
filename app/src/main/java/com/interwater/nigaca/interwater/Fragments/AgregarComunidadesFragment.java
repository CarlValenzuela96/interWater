package com.interwater.nigaca.interwater.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.interwater.nigaca.interwater.Controller.ComunidadController;
import com.interwater.nigaca.interwater.Controller.PersonaController;
import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.R;

import java.util.ArrayList;

public class AgregarComunidadesFragment extends Fragment {
    static final int CHOOSE_FILE = 1;

    Button infoButton;
    Button examinar;
    Button guardar;
    TextInputEditText nombre_comunidad;

    String path_file_to_add="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.agregar_comunidades, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.infoButton = getActivity().findViewById(R.id.info_search_button);
        this.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Seleccione archivo con planilla de comunidad";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        this.examinar = getActivity().findViewById(R.id.examinar_button);
        this.examinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFolder();
            }
        });

        this.nombre_comunidad = getActivity().findViewById(R.id.nombre_comunidad_agregar);

        this.guardar = getActivity().findViewById(R.id.guardar_comunidad_button);
        this.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(nombre_comunidad.getText());

                if(!path_file_to_add.isEmpty() && !nombre_comunidad.getText().toString().isEmpty()){
                    Comunidad c = new Comunidad(10,name);
                    ComunidadController cController = new ComunidadController(getActivity().getApplicationContext());
                    long idCom = cController.agregarComunidad(c);

                    ArrayList<Persona> personas = cController.leerArchivo(cController.getComunidad(idCom),R.raw.datos_comunidad);

                    PersonaController pController = new PersonaController(getActivity().getApplicationContext());
                    pController.addPersonasToComunidad(personas);

                    path_file_to_add ="";
                    nombre_comunidad.setText("");

                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Comunidad guardada exitosamente";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }else{
                    String msg="";
                    if(nombre_comunidad.getText().toString().isEmpty()){
                        msg = "Ingrese nombre de la comunidad";
                    }

                    if(path_file_to_add.isEmpty()){
                        msg += "\nPor favor cargue planilla";
                    }

                    Context context = getActivity().getApplicationContext();
                    CharSequence text = msg;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }

    public void openFolder(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "/myFolder/");
        intent.setDataAndType(uri, "application/pdf");

        startActivityForResult(Intent.createChooser(intent, "Open folder"),CHOOSE_FILE);

        Context context = getActivity().getApplicationContext();
        CharSequence text = intent.getDataString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_FILE) {


            Uri fileUri = data.getData();
            Context context = getActivity().getApplicationContext();
            CharSequence text = fileUri.toString();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            this.path_file_to_add = fileUri.getPath();
        }

    }
}
