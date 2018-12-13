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

        DatabaseHelper dbh = new DatabaseHelper(this);

        if(!dbh.checkDataBase(dbh.getWritableDatabase().getPath())) {
            Context context = this;
            CharSequence text = "db_ya no existe";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            dbh.onCreate(dbh.getWritableDatabase());
        }else{
            Context context = this;
            CharSequence text = "db_ya existe";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

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
}
