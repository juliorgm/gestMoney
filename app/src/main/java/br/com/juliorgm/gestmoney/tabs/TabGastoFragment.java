package br.com.juliorgm.gestmoney.tabs;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.fragments.FormularioGastoFragment;
import br.com.juliorgm.gestmoney.fragments.VisualizarGastoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabGastoFragment extends Fragment {


    public TabGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_gasto, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager_gasto);
        viewPager.setAdapter(new TabGastoFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private class TabGastoFragmentPageAdapter extends FragmentPagerAdapter {

        public TabGastoFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FormularioGastoFragment();
                default:
                    return new VisualizarGastoFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Novo";
                default:
                    return "Visualizar";
            }
        }
    }

}
