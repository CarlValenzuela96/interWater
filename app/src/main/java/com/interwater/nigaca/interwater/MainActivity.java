package com.interwater.nigaca.interwater;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Fragments.AgregarComunidadesFragment;
import com.interwater.nigaca.interwater.Fragments.ComunidadesFragment;
import com.interwater.nigaca.interwater.Fragments.EstadisticaFragment;
import com.interwater.nigaca.interwater.Fragments.HomeFragment;
import com.interwater.nigaca.interwater.Models.Fecha;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearDB();
        crearFecha();

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
        tabViewPagerAdapter.addFragment(new HomeFragment(), "Home");
        tabViewPagerAdapter.addFragment(new AgregarComunidadesFragment(), "Agregar");
        tabViewPagerAdapter.addFragment(new ComunidadesFragment(), "Comunidades");
        tabViewPagerAdapter.addFragment(new EstadisticaFragment(), "Estadistica");

        viewPager.setAdapter(tabViewPagerAdapter);
    }

   public void crearDB(){
       DatabaseHelper dbh = new DatabaseHelper(this);

       if(!dbh.checkDataBase(dbh.getWritableDatabase().getPath())) {
           Context context = this;
           CharSequence text = "db no existe";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();

           dbh.onCreate(dbh.getWritableDatabase());
       }else{
           Context context = this;
           CharSequence text = "db existe";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       }
   }

   public void crearFecha(){

       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
       Date date = new Date();
       int year = Integer.valueOf(dateFormat.format(date).split("-")[0]);
       int mes =  Integer.valueOf(dateFormat.format(date).split("-")[1]);
       int dia = Integer.valueOf(dateFormat.format(date).split("-")[2]);

       DatabaseHelper dbh = new DatabaseHelper(this);

       if(!dbh.isDateExist(dia,mes,year)){
           dbh.insertFecha(new Fecha(dia,mes,year));
       }
    }
}
