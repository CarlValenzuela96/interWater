package com.interwater.nigaca.interwater.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.interwater.nigaca.interwater.R;

public class RespaldoActivity extends AppCompatActivity {
    Button backButton;
    RadioButton rb_guardar_local;
    RadioButton rb_enviar_correo;
    Button guardar_reporte_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_estado);


        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });






    }
}
