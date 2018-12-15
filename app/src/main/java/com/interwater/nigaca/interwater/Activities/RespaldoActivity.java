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

        this.rb_guardar_local = (RadioButton)findViewById(R.id.rb_guardar_local);
        this.rb_enviar_correo = (RadioButton)findViewById(R.id.rb_enviar_correo);

        String msg="";

        if(this.rb_guardar_local.isChecked()){
            msg="guardado localmente";
        }else if(this.rb_enviar_correo.isChecked()){
            msg="enviado correo";
        }

        this.guardar_reporte_button = findViewById(R.id.guardar_reporte_button);

        final String finalMsg = msg;
        this.guardar_reporte_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                CharSequence text = finalMsg;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}
