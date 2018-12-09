package com.interwater.nigaca.interwater;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    //private final static String[] names = { "Enero", "Febrero", "Marzo",
      //      "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"  };

    //private Spinner meses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // parametros para snniper
       // ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);

      //  meses = (Spinner)findViewById(R.id.seleccionMeses);
       // meses.setAdapter(adapter);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        // ingresamos los fragmentos a los tab
        tabLayout.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);
    }

    // este metodo declara el objeto de la clase
    private void setUpViewPager(ViewPager viewPager){

        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        // enviamos el fragmento "home, agregar, comunidades y estadistica" a la activity main como tabs
        tabViewPagerAdapter.addFragment(new Home(), "Home");
        tabViewPagerAdapter.addFragment(new AgregarComunidades(), "Agregar");
        tabViewPagerAdapter.addFragment(new Comunidades(), "Comunidades");
        tabViewPagerAdapter.addFragment(new Estadistica(), "Estadistica");

        viewPager.setAdapter(tabViewPagerAdapter);
    }
}
