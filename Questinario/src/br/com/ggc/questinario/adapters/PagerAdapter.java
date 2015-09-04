package br.com.ggc.questinario.adapters;

import java.util.ArrayList;

import br.com.ggc.questinario.TabFragment;
import br.com.ggc.questinario.objs.ItemTab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    ArrayList<String> mItens;
 
    public PagerAdapter(FragmentManager fm, int numOfTabs,ArrayList<String> Itens) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.mItens = Itens;
    }
 
    @Override
    public Fragment getItem(int position) {
 
       return TabFragment.getInstance(position,mItens.get(position));
    }
 
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
