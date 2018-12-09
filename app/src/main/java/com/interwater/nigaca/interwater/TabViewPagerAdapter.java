package com.interwater.nigaca.interwater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewPagerAdapter extends FragmentPagerAdapter {

    // Listas
    //uno contiene los fragmentos que se van añadir al contenedor
    // y el otro el texto o identificador que aparece en las pestañas
    private final List<Fragment>mFragmentList = new ArrayList<>();
    private final List<String>mFragmentTileList = new ArrayList<>();


    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //agregamos el fragmento y el texto
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTileList.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTileList.get(position);
    }

}
